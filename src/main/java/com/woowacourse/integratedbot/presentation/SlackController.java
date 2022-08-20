package com.woowacourse.integratedbot.presentation;

import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.woowacourse.integratedbot.application.SlackService;
import com.woowacourse.integratedbot.application.request.SlackPostMessageRequest;
import com.woowacourse.integratedbot.domain.WoowacourseTeamRespository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SlackController {

    private final SlackService slackService;
    private final WoowacourseTeamRespository woowacourseTeamRespository;

    public SlackController(final SlackService slackService,
                           final WoowacourseTeamRespository woowacourseTeamRespository) {
        this.slackService = slackService;
        this.woowacourseTeamRespository = woowacourseTeamRespository;
    }

    @PostMapping("/send")
    public ResponseEntity<Void> receive(@RequestBody final SlackPostMessageRequest request,
                                        @RequestParam(required = false) final String code) {
        if (woowacourseTeamRespository.exisitByCode(code)) {
            slackService.sendSlackMessage(request);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
