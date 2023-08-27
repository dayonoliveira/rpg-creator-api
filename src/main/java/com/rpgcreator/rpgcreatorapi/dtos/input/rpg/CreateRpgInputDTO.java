package com.rpgcreator.rpgcreatorapi.dtos.input.rpg;

import com.rpgcreator.rpgcreatorapi.entities.user.User;
import com.rpgcreator.rpgcreatorapi.enums.rpg.ActionsDecisionMethodEnum;
import com.rpgcreator.rpgcreatorapi.enums.rpg.RpgTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class CreateRpgInputDTO {
    @NotBlank
    private String name;
    @NotNull
    private RpgTypeEnum basedAt;
    @NotBlank
    private String description;
    @NotNull
    private ActionsDecisionMethodEnum decisionMethod;
    @NotNull
    private Integer minPlayers;
    private Integer maxPlayers;
    @NotNull
    private Long createdBy;
    @NotNull
    private Boolean isPublic;
}
