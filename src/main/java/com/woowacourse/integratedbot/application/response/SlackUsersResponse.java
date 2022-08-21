package com.woowacourse.integratedbot.application.response;

import com.slack.api.methods.response.users.UsersListResponse;
import com.slack.api.model.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class SlackUsersResponse {

    private List<Member> members;

    private SlackUsersResponse() {
    }

    public SlackUsersResponse(final List<Member> members) {
        this.members = members;
    }

    public static SlackUsersResponse from(final UsersListResponse response) {
        return new SlackUsersResponse(
                response.getMembers()
                        .stream()
                        .map(Member::from)
                        .collect(Collectors.toList())
        );
    }

    @Getter
    private static class Member {

        private String id;
        private Profile profile;

        private Member() {
        }

        public Member(final String id, final Profile profile) {
            this.id = id;
            this.profile = profile;
        }

        public static Member from(final User user) {
            return new Member(user.getId(), new Profile(user.getProfile().getEmail()));
        }

        @Getter
        private static class Profile {

            private String email;

            private Profile() {
            }

            public Profile(final String email) {
                this.email = email;
            }
        }
    }
}
