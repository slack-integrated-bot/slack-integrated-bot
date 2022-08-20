package com.woowacourse.integratedbot.domain;

public class WoowacourseTeamAlwaysOpenRepository implements WoowacourseTeamRespository {

    @Override
    public boolean exisitByCode(final String code) {
        return true;
    }
}
