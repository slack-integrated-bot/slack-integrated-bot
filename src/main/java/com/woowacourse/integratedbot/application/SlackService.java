package com.woowacourse.integratedbot.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.slack.api.methods.response.users.UsersListResponse;
import com.woowacourse.integratedbot.application.response.SlackResponse;
import com.woowacourse.integratedbot.application.response.SlackUsersResponse;
import com.woowacourse.integratedbot.exception.ChannelNotFoundException;
import com.woowacourse.integratedbot.exception.SlackException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SlackService {

    private static final String CHAT_POST_MESSAGE = "/api/chat.postMessage";
    private static final String USERS_LIST = "/api/users.list";

    private static final Entry<String, String> CHANNEL_NOT_FOUND
            = new SimpleEntry<>("channel_not_found", "존재하지 않는 채널입니다. channel:%s");

    private final String slackToken;
    private final ObjectMapper objectMapper;
    private final WebClient webClient;

    public SlackService(@Value("${slack.token}") final String slackToken,
                        final ObjectMapper objectMapper) {
        this.slackToken = slackToken;
        this.objectMapper = objectMapper;
        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(configurer -> {
                    configurer
                            .defaultCodecs()
                            .maxInMemorySize(-1);       // webflux response unlimited buffer size (default: 256k)
                }).build();

        this.webClient = WebClient.builder()
                .baseUrl("https://slack.com")
                .exchangeStrategies(strategies)
                .build();
    }

    public void sendSlackMessage(final Map<String, Object> request) throws JsonProcessingException {
        String sendMessage = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);

        SlackResponse response = webClient.post()
                .uri(CHAT_POST_MESSAGE)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + slackToken)
                .bodyValue(sendMessage)
                .retrieve()
                .bodyToMono(SlackResponse.class)
                .block();

        checkResponseNotOk(response);
    }

    public SlackUsersResponse getUsers() throws Exception {
        UsersListResponse response = webClient.get()
                .uri(USERS_LIST)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + slackToken)
                .retrieve()
                .bodyToMono(UsersListResponse.class)
                .block();

        checkResponseNotOk(response);
        return SlackUsersResponse.from(response);
    }

    private void checkResponseNotOk(final SlackResponse response) {
        if (response == null) {
            throw new SlackException("통신 장애가 발생하였습니다.");
        }
        if (response.isOk()) {
            return;
        }
        if (response.getError().equals(CHANNEL_NOT_FOUND.getKey())) {
            throw new ChannelNotFoundException(String.format(CHANNEL_NOT_FOUND.getValue(), response.getChannel()));
        }
        throw new SlackException(response.getError());
    }

    private void checkResponseNotOk(final UsersListResponse response) {
        if (response == null) {
            throw new SlackException("통신 장애가 발생하였습니다.");
        }
        if (response.isOk()) {
            return;
        }
        throw new SlackException(response.getError());
    }
}
