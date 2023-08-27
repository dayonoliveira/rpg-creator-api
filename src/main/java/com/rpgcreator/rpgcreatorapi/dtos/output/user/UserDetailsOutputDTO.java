package com.rpgcreator.rpgcreatorapi.dtos.output.user;

import com.rpgcreator.rpgcreatorapi.entities.user.User;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public final class UserDetailsOutputDTO {
    private final Long userId;
    private final String nickname;
    private final String email;
    private final LocalDate birthdate;
    private final String bio;

    public UserDetailsOutputDTO(User user) {
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.birthdate = user.getBirthdate();
        this.bio = user.getBio();
    }
}
