package com.woowacourse.integratedbot.application;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.woowacourse.integratedbot.application.request.SlackPostMessageRequest;
import com.woowacourse.integratedbot.exception.ChannelNotFoundException;
import com.woowacourse.integratedbot.exception.SlackException;
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
    public void sendSlackMessage(final SlackPostMessageRequest request) throws Exception {
        try (Slack slack = Slack.getInstance()) {
            ChatPostMessageRequest chatRequest = request.toChatPostMessageRequest();
            ChatPostMessageResponse response = slack.methods(slackToken)
                    .chatPostMessage(chatRequest);

            checkResponseNotOk(response);
        } catch (SlackApiException e) {
            throw new SlackException(e.getMessage());
        }
    }

    private void checkResponseNotOk(final ChatPostMessageResponse response) {
        if (response.isOk()) {
            return;
        }
        if (response.getError().equals("channel_not_found")) {
            throw new ChannelNotFoundException("존재하지 않는 채널입니다.");
        }
        throw new SlackException(response.getError());
    }
}
