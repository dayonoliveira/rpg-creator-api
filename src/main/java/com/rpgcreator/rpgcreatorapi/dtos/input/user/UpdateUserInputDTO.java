package com.rpgcreator.rpgcreatorapi.dtos.input.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public final class UpdateUserInputDTO {
    private String nickname;
    private String email;
    private Date birthdate;
    private String bio;
}