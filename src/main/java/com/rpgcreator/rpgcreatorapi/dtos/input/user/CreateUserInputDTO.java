package com.rpgcreator.rpgcreatorapi.dtos.input.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public final class CreateUserInputDTO {
    @NotBlank
    private String nickname;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private LocalDate birthdate;
    private String bio;
}
