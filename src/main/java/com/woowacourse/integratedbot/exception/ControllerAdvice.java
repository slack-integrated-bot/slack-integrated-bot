package com.woowacourse.integratedbot.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerAdvice {

    private static final String EXCEPTION_MESSAGE = "서버에서 문제가 발생하였습니다.";

    @ExceptionHandler(ChannelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleChannelNotFoundException(final ChannelNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.from(e));
    }

    @ExceptionHandler(SlackException.class)
    public ResponseEntity<ErrorResponse> handleSlackException(final SlackException e) {
        String message = extractStackTrace(e);
        return ResponseEntity.internalServerError().body(ErrorResponse.from(EXCEPTION_MESSAGE));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(final Exception e) {
        String message = extractStackTrace(e);
        return ResponseEntity.internalServerError().body(ErrorResponse.from(EXCEPTION_MESSAGE));
    }

    private String extractStackTrace(final Exception e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        e.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}
