package com.rpgcreator.rpgcreatorapi.dtos.output.rpg.types;

import com.rpgcreator.rpgcreatorapi.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class CreatedBy {
    private Long userId;
    private String userNickname;

    public CreatedBy(User user) {
        this.userId = user.getUserId();
        this.userNickname = user.getNickname();
    }
}
