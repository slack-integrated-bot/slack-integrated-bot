package com.woowacourse.integratedbot.application;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.methods.response.users.UsersListResponse;
import com.woowacourse.integratedbot.application.request.SlackPostMessageRequest;
import com.woowacourse.integratedbot.application.response.SlackUsersResponse;
import com.woowacourse.integratedbot.exception.ChannelNotFoundException;
import com.woowacourse.integratedbot.exception.SlackException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SlackService {

    private static final Entry<String, String> CHANNEL_NOT_FOUND
            = new SimpleEntry<>("channel_not_found", "존재하지 않는 채널입니다. channel:%s");

    private final String slackToken;

    public SlackService(@Value("${slack.token}") final String slackToken) {
        this.slackToken = slackToken;
    }

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

    public SlackUsersResponse getUsers() throws Exception {
        try (Slack slack = Slack.getInstance()) {
            UsersListResponse usersListResponse = slack.methods(slackToken)
                    .usersList(r -> r.token(slackToken));

            checkResponseNotOk(usersListResponse);
            return SlackUsersResponse.from(usersListResponse);
        } catch (SlackApiException e) {
            throw new SlackException(e.getMessage());
        }
    }

    private void checkResponseNotOk(final ChatPostMessageResponse response) {
        if (response.isOk()) {
            return;
        }
        if (response.getError().equals(CHANNEL_NOT_FOUND.getKey())) {
            throw new ChannelNotFoundException(String.format(CHANNEL_NOT_FOUND.getValue(), response.getChannel()));
        }
        throw new SlackException(response.getError());
    }

    private void checkResponseNotOk(final UsersListResponse response) {
        if (response.isOk()) {
            return;
        }
        throw new SlackException(response.getError());
    }
}
