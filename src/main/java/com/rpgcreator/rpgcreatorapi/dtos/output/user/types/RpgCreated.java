package com.rpgcreator.rpgcreatorapi.dtos.output.user.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class RpgCreated {
    private Long rpgId;
    private Boolean isPublic;
}
