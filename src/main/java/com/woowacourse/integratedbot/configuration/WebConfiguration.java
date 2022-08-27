package com.woowacourse.integratedbot.configuration;

import com.woowacourse.integratedbot.domain.WoowacourseTeamJdbcRepository;
import com.woowacourse.integratedbot.domain.WoowacourseTeamRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class WebConfiguration {

    private final JdbcTemplate jdbcTemplate;

    public WebConfiguration(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // TODO: 각 팀마다의 Key 값 발급해야 함
    @Bean
    public WoowacourseTeamRepository woowacourseTeamRepository() {
        return new WoowacourseTeamJdbcRepository(jdbcTemplate);
    }
}
