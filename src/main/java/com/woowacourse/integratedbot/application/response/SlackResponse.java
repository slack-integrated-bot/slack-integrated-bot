package com.woowacourse.integratedbot.application.response;

import lombok.Data;

@Data
public class SlackResponse {

    private boolean ok;
    private String error;
    private String channel;
}
