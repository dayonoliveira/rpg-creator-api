package com.rpgcreator.rpgcreatorapi.enums.rpg;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RpgTypeEnum {
    DD_CLASSIC("D&D Clássico"),
    VAMPIRE_MASK("Vampiro: A Máscara"),
    WEREWOLF_APOCALYPSE("Lobisomem: O Apocalipse"),
    CUSTOM("RPG customizado");

    private final String description;

    public static String getRpgTypeEnumDescription(RpgTypeEnum type) {
        if (type == null) return null;

        return type.getDescription();
    }
}
