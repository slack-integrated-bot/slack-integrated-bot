package com.woowacourse.integratedbot.support;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.util.Strings;

public class AuthorizationExtractor {

    private static final String AUTHORIZATION = "Key";

    public static Optional<String> extractKey(final HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION);

        if (Strings.isEmpty(header)) {
            return Optional.empty();
        }

        return Optional.of(header);
    }
}
