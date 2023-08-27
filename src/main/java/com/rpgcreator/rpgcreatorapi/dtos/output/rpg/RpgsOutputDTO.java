package com.rpgcreator.rpgcreatorapi.dtos.output.rpg;

import com.rpgcreator.rpgcreatorapi.dtos.output.rpg.types.CreatedBy;
import com.rpgcreator.rpgcreatorapi.entities.rpg.Rpg;
import com.rpgcreator.rpgcreatorapi.enums.rpg.ActionsDecisionMethodEnum;
import com.rpgcreator.rpgcreatorapi.enums.rpg.RpgTypeEnum;
import lombok.Getter;

@Getter
public final class RpgsOutputDTO {
    private final Long rpgId;
    private final String name;
    private final RpgTypeEnum basedAt;
    private final String description;
    private final ActionsDecisionMethodEnum decisionMethod;
    private final Integer minPlayers;
    private final Integer maxPlayers;
    private final CreatedBy createdBy;
    private final Boolean isPublic;

    public RpgsOutputDTO(Rpg rpg) {
        CreatedBy userCreator = new CreatedBy(rpg.getCreatedBy().getUserId(), rpg.getCreatedBy().getNickname());

        this.rpgId = rpg.getRpgId();
        this.name = rpg.getName();
        this.basedAt = rpg.getBasedAt();
        this.description = rpg.getDescription();
        this.decisionMethod = rpg.getDecisionMethod();
        this.minPlayers = rpg.getMinPlayers();
        this.maxPlayers = rpg.getMaxPlayers();
        this.createdBy = userCreator;
        this.isPublic = rpg.getIsPublic();
    }
}
