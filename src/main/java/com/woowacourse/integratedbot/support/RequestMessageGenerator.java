package com.woowacourse.integratedbot.support;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.web.util.ContentCachingRequestWrapper;

public class RequestMessageGenerator {

    private static final String MESSAGE_FORMAT = "Request Information\n%s %s\n%s\nParams: %s\nBody : %s\n";
    private static final String KEY_VALUE_DELIMITER = ":";

    private RequestMessageGenerator() {
    }

    public static String generate(final ContentCachingRequestWrapper request) {
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        String headers = getHeaders(request);
        String params = getParams(request);
        String body = getBody(request);

        return String.format(MESSAGE_FORMAT, method, requestURI, headers, params, body);
    }

    private static String getHeaders(final ContentCachingRequestWrapper request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        return extractHeaders(request, headerNames);
    }

    private static String extractHeaders(final ContentCachingRequestWrapper request,
                                         final Enumeration<String> parameterNames) {
        Map<String, String> headers = new HashMap<>();

        while (parameterNames.hasMoreElements()) {
            String headerName = parameterNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }

        return convertMapToString(headers);
    }

    private static String getParams(final ContentCachingRequestWrapper request) {
        Enumeration<String> parameterNames = request.getParameterNames();
        return extractParameters(request, parameterNames);
    }

    private static String extractParameters(final ContentCachingRequestWrapper request,
                                            final Enumeration<String> parameterNames) {
        Map<String, String> parameters = new HashMap<>();

        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            parameters.put(parameterName, request.getParameter(parameterName));
        }

        return convertMapToString(parameters);
    }

    private static String getBody(final ContentCachingRequestWrapper request) {
        return new String(request.getContentAsByteArray());
    }

    private static String convertMapToString(final Map<String, String> requestInfo) {
        return requestInfo.entrySet().stream()
                .map(i -> i.getKey() + KEY_VALUE_DELIMITER+ i.getValue())
                .collect(Collectors.joining("\n"));
    }
}
