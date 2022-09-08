package com.woowacourse.integratedbot.domain;

import java.util.Optional;

public interface WoowacourseTeamRepository {

    boolean existByCode(String code);

    Optional<WoowacourseTeam> findByCode(String code);
}
