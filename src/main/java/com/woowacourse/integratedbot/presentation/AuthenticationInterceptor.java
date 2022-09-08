package com.woowacourse.integratedbot.presentation;

import static com.woowacourse.integratedbot.support.RequestMessageGenerator.generate;

import com.woowacourse.integratedbot.domain.WoowacourseTeam;
import com.woowacourse.integratedbot.domain.WoowacourseTeamRepository;
import com.woowacourse.integratedbot.exception.UnauthorizedException;
import com.woowacourse.integratedbot.support.AuthorizationExtractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;

@Component
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final WoowacourseTeamRepository woowacourseTeamRepository;

    public AuthenticationInterceptor(WoowacourseTeamRepository woowacourseTeamRepository) {
        this.woowacourseTeamRepository = woowacourseTeamRepository;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) throws Exception {
        if (CorsUtils.isPreFlightRequest(request)) {
            return true;
        }

        String key = AuthorizationExtractor.extractKey(request)
                .orElseThrow(() -> new UnauthorizedException("헤더에 키값이 정상적으로 존재하지 않습니다."));

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        WoowacourseTeam woowacourseTeam = woowacourseTeamRepository.findByCode(key)
                .orElseThrow(() -> new UnauthorizedException("존재하지 않는 팀의 키값입니다."));
        log.info("slack api call team : {} \n {}", woowacourseTeam.getTeamName(), generate(requestWrapper));

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
