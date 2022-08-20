package com.woowacourse.integratedbot.domain;

public class WoowacourseTeam {

    private final String code;
    private final String teamName;

    public WoowacourseTeam(final String code, final String teamName) {
        this.code = code;
        this.teamName = teamName;
    }

    public boolean isEqualsCode(final String code) {
        return this.code.equals(code);
    }
}
