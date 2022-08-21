package com.woowacourse.integratedbot.exception;

public class ChannelNotFoundException extends RuntimeException {

    public ChannelNotFoundException(final String message) {
        super(message);
    }
}
