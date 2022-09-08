package com.woowacourse.integratedbot.domain;

import java.util.List;
import java.util.Optional;

public class WoowacourseTeamInmemoryRepository implements WoowacourseTeamRepository {

    private final List<WoowacourseTeam> woowacourseTeams = List.of(new WoowacourseTeam("ori1234", "테스트팀"));

    @Override
    public boolean existByCode(final String code) {
        return woowacourseTeams.stream()
                .anyMatch(team -> team.isEqualsCode(code));
    }

    @Override
    public Optional<WoowacourseTeam> findByCode(final String code) {
        return woowacourseTeams.stream()
                .filter(woowacourseTeam -> woowacourseTeam.getCode().equals(code))
                .findFirst();
    }
}
