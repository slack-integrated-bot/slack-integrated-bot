package com.woowacourse.integratedbot.application;

import com.slack.api.Slack;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.woowacourse.integratedbot.application.request.SlackPostMessageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SlackService {

    private final String slackToken;

    public SlackService(@Value("${slack.token}") final String slackToken) {
        this.slackToken = slackToken;
    }

    @Async
    public void sendSlackMessage(final SlackPostMessageRequest request) {
        try (Slack slack = Slack.getInstance()) {
            ChatPostMessageRequest chatRequest = request.toChatPostMessageRequest();
            ChatPostMessageResponse response = slack.methods(slackToken)
                    .chatPostMessage(chatRequest);

            if (!response.isOk()) {
                String message = response.getError(); // "invalid_auth", "channel_not_found"
                System.out.println(message);
                throw new RuntimeException("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
