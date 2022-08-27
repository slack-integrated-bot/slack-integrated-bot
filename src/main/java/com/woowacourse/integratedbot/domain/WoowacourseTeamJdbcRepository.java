package com.woowacourse.integratedbot.domain;

import org.springframework.jdbc.core.JdbcTemplate;

public class WoowacourseTeamJdbcRepository implements WoowacourseTeamRepository {

    private final JdbcTemplate jdbcTemplate;

    public WoowacourseTeamJdbcRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean existByCode(final String code) {
        String sql = "SELECT EXISTS (SELECT * FROM woowacourseTeam WHERE code = ?)";

        return jdbcTemplate.queryForObject(sql, Boolean.class, code);
    }
}
