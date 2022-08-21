package com.woowacourse.integratedbot.presentation;

import com.woowacourse.integratedbot.application.SlackService;
import com.woowacourse.integratedbot.application.request.SlackPostMessageRequest;
import com.woowacourse.integratedbot.application.response.SlackUsersResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SlackController {

    private final SlackService slackService;

    public SlackController(final SlackService slackService) {
        this.slackService = slackService;
    }

    @PostMapping("/send")
    public ResponseEntity<Void> receive(@RequestBody final SlackPostMessageRequest request) throws Exception {
        slackService.sendSlackMessage(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    public ResponseEntity<SlackUsersResponse> getUsersList() throws Exception {
        SlackUsersResponse response = slackService.getUsers();
        return ResponseEntity.ok(response);
    }
}
