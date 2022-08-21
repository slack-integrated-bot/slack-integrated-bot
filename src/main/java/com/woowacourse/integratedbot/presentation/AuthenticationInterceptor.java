package com.woowacourse.integratedbot.presentation;

import com.woowacourse.integratedbot.domain.WoowacourseTeamRespository;
import com.woowacourse.integratedbot.exception.TeamNotFoundException;
import com.woowacourse.integratedbot.exception.UnauthorizedException;
import com.woowacourse.integratedbot.support.TeamKeyExtractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private WoowacourseTeamRespository woowacourseTeamRespository;

    public AuthenticationInterceptor(WoowacourseTeamRespository woowacourseTeamRespository) {
        this.woowacourseTeamRespository = woowacourseTeamRespository;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) throws Exception {
        if (CorsUtils.isPreFlightRequest(request)) {
            return true;
        }

        String teamKey = TeamKeyExtractor.extractKey(request)
            .orElseThrow(() -> new UnauthorizedException("헤더에 키 값이 정상적으로 존재하지 않습니다."));

        if (!woowacourseTeamRespository.exisitByCode(teamKey)) {
            throw new TeamNotFoundException("존재하지 않는 팀입니다.");
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
