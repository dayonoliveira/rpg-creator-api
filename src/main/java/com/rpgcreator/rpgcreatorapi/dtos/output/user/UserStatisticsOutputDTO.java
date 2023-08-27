package com.rpgcreator.rpgcreatorapi.dtos.output.user;

import com.rpgcreator.rpgcreatorapi.dtos.output.user.types.RpgCreated;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public final class UserStatisticsOutputDTO {
    private Integer rpgsCreatedQtt;
    private List<RpgCreated> rpgsCreated;
}
