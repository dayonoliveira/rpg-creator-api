package com.rpgcreator.rpgcreatorapi.dtos.output.user;

import com.rpgcreator.rpgcreatorapi.entities.user.User;
import lombok.Getter;

@Getter
public final class GetUsersOutputDTO {
    private final Long userId;
    private final String nickname;

    public GetUsersOutputDTO(User user) {
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
    }
}
