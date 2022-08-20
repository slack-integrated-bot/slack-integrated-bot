package com.woowacourse.integratedbot.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private String message;

    private ErrorResponse() {
    }

    private ErrorResponse(final String message) {
        this.message = message;
    }

    public static ErrorResponse from(final String message) {
        return new ErrorResponse(message);
    }

    public static ErrorResponse from(final RuntimeException e) {
        return new ErrorResponse(e.getMessage());
    }
}
