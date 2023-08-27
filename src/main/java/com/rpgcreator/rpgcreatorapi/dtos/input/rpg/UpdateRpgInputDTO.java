package com.rpgcreator.rpgcreatorapi.dtos.input.rpg;

import com.rpgcreator.rpgcreatorapi.entities.user.User;
import com.rpgcreator.rpgcreatorapi.enums.rpg.ActionsDecisionMethodEnum;
import com.rpgcreator.rpgcreatorapi.enums.rpg.RpgTypeEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class UpdateRpgInputDTO {
    private String name;
    private RpgTypeEnum basedAt;
    private String description;
    private ActionsDecisionMethodEnum decisionMethod;
    private Integer minPlayers;
    private Integer maxPlayers;
    private Boolean isPublic;
}
