package com.woowacourse.integratedbot.domain;

import java.util.ArrayList;
import java.util.List;

public class WoowacourseTeamInmemoryRespository implements WoowacourseTeamRespository {

    private final List<WoowacourseTeam> woowacourseTeams = new ArrayList<>();

    @Override
    public boolean exisitByCode(final String code) {
        return woowacourseTeams.stream()
                .anyMatch(team -> team.isEqualsCode(code));
    }
}