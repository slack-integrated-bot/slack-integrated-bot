package com.woowacourse.integratedbot.presentation;

import com.woowacourse.integratedbot.domain.WoowacourseTeamRepository;
import com.woowacourse.integratedbot.exception.UnauthorizedException;
import com.woowacourse.integratedbot.support.AuthorizationExtractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private WoowacourseTeamRepository woowacourseTeamRespository;

    public AuthenticationInterceptor(WoowacourseTeamRepository woowacourseTeamRespository) {
        this.woowacourseTeamRespository = woowacourseTeamRespository;
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

        if (!woowacourseTeamRespository.existByCode(key)) {
            throw new UnauthorizedException("존재하지 않는 팀의 키값입니다.");
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
