package com.woowacourse.integratedbot.support;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;

public class TeamKeyExtractor {

    public static Optional<String> extractKey(final HttpServletRequest request) {
        String header = request.getHeader("Team-Key");

        if (Strings.isEmpty(header)) {
            return Optional.empty();
        }

        return Optional.of(header);
    }
}
