package com.rpgcreator.rpgcreatorapi.enums.rpg;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActionsDecisionMethodEnum {
    DICE("Dados"),
    JOKENPO("Jokenpô"),
    ZERO_ONE("Zerinho ou um"),
    AMERICAN_ZERO_ONE("Zerinho ou um americano");

    private final String description;

    public String getActionsDecisionMethodEnumDescription(ActionsDecisionMethodEnum type) {
        if (type == null) return null;

        return type.getDescription();
    }
}
