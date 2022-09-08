package com.woowacourse.integratedbot.domain;

import java.util.Optional;

public class WoowacourseTeamAlwaysOpenRepository implements WoowacourseTeamRepository {

    public boolean existByCode(final String code) {
        return true;
    }

    public Optional<WoowacourseTeam> findByCode(final String code) {
        return Optional.of(new WoowacourseTeam("code1234", "gong-check"));
    }
}
