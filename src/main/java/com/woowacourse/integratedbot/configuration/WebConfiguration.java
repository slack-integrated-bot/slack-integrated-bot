package com.woowacourse.integratedbot.configuration;

import com.woowacourse.integratedbot.domain.WoowacourseTeamAlwaysOpenRepository;
import com.woowacourse.integratedbot.domain.WoowacourseTeamRespository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

    @Bean
    public WoowacourseTeamRespository woowacourseTeamRespository() {
        return new WoowacourseTeamAlwaysOpenRepository();
    }
}
