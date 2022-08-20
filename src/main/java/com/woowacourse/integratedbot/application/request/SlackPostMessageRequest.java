package com.woowacourse.integratedbot.application.request;

import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.model.Attachment;
import com.slack.api.model.Message;
import com.slack.api.model.block.LayoutBlock;
import java.util.List;
import lombok.Getter;

@Getter
public class SlackPostMessageRequest {

    private String token;

    private String username;

    private String threadTs;

    private String channel;

    private String text;

    private String parse;

    private boolean linkNames;

    private Message.Metadata metadata;

    private String metadataAsString;

    private List<LayoutBlock> blocks;

    private String blocksAsString;

    private List<Attachment> attachments;

    private String attachmentsAsString;

    private boolean unfurlLinks;

    private boolean unfurlMedia;

    private boolean mrkdwn = true;

    private String iconUrl;

    private String iconEmoji;

    private boolean replyBroadcast;

    private SlackPostMessageRequest() {
    }

    public ChatPostMessageRequest toChatPostMessageRequest() {
        return ChatPostMessageRequest.builder()
                .token(token)
                .username(username)
                .threadTs(threadTs)
                .channel(channel)
                .text(text)
                .parse(parse)
                .linkNames(linkNames)
                .metadata(metadata)
                .metadataAsString(metadataAsString)
                .blocks(blocks)
                .blocksAsString(blocksAsString)
                .attachments(attachments)
                .attachmentsAsString(attachmentsAsString)
                .unfurlLinks(unfurlLinks)
                .unfurlMedia(unfurlMedia)
                .mrkdwn(mrkdwn)
                .iconUrl(iconUrl)
                .iconEmoji(iconEmoji)
                .replyBroadcast(replyBroadcast)
                .build();
    }
}
