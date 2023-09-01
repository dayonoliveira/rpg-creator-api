package com.rpgcreator.rpgcreatorapi.dtos.input.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public final class AuthUserInputDTO {
    @NotBlank
    private String nickname;
    @NotBlank
    private String password;
}
