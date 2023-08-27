package com.rpgcreator.rpgcreatorapi.dtos.input.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public final class UpdatePasswordInputDTO {
    @NotBlank
    private String password;
}
