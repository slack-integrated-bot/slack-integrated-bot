package com.woowacourse.integratedbot.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfiguration {

    private static final String WOOWACOURSE_TEAM_CACHE_NAME = "woowacourseTeam";

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(WOOWACOURSE_TEAM_CACHE_NAME);
    }
}
