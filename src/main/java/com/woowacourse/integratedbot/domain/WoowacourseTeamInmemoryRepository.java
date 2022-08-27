package com.woowacourse.integratedbot.domain;

import java.util.ArrayList;
import java.util.List;

public class WoowacourseTeamInmemoryRepository implements WoowacourseTeamRepository {

    private final List<WoowacourseTeam> woowacourseTeams = new ArrayList<>();

    @Override
    public boolean existByCode(final String code) {
        return woowacourseTeams.stream()
                .anyMatch(team -> team.isEqualsCode(code));
    }
}
