package com.woowacourse.integratedbot.domain;

public class WoowacourseTeamAlwaysOpenRepository implements WoowacourseTeamRepository {

    public boolean existByCode(final String code) {
        return true;
    }
}
