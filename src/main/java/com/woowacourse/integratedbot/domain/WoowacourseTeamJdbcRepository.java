package com.woowacourse.integratedbot.domain;

import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class WoowacourseTeamJdbcRepository implements WoowacourseTeamRepository {

    private final JdbcTemplate jdbcTemplate;

    public WoowacourseTeamJdbcRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean existByCode(final String code) {
        String sql = "SELECT EXISTS (SELECT * FROM woowacourse_team WHERE code = ?)";

        return jdbcTemplate.queryForObject(sql, Boolean.class, code);
    }

    @Override
    public Optional<WoowacourseTeam> findByCode(final String code) {
        String sql = "SELECT * FROM woowacourse_team where code = ?";
        return jdbcTemplate.query(sql, woowcourseTeamRowMapper(), code)
                .stream()
                .findFirst();
    }

    private RowMapper<WoowacourseTeam> woowcourseTeamRowMapper() {
        return (rs, rowNum) -> new WoowacourseTeam(
                rs.getString("code"),
                rs.getString("team_name")
        );
    }
}
