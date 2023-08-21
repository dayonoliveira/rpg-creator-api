package com.rpgcreator.rpgcreatorapi.entities.rpg;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.rpgcreator.rpgcreatorapi.dtos.input.rpg.CreateRpgInputDTO;
import com.rpgcreator.rpgcreatorapi.dtos.input.rpg.UpdateRpgInputDTO;
import com.rpgcreator.rpgcreatorapi.enums.rpg.ActionsDecisionMethodEnum;
import com.rpgcreator.rpgcreatorapi.enums.rpg.RpgTypeEnum;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "rpgs")
@Entity(name = "Rpg")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "rpgId")
public class Rpg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rpgId;

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private RpgTypeEnum basedAt;
    private String description;

    @Enumerated(EnumType.STRING)
    private ActionsDecisionMethodEnum decisionMethod;
    private Integer minPlayers;
    private Integer maxPlayers;
    private Long createdBy;
    private Boolean isPublic;


    public Rpg(CreateRpgInputDTO rpgData) {
        this.name = rpgData.getName();
        this.basedAt = rpgData.getBasedAt();
        this.description = rpgData.getDescription();
        this.decisionMethod = rpgData.getDecisionMethod();
        this.minPlayers = rpgData.getMinPlayers();
        this.maxPlayers = rpgData.getMaxPlayers();
        this.createdBy = rpgData.getCreatedBy();
        this.isPublic = rpgData.getIsPublic();
    }

    public Rpg updateRpgById(UpdateRpgInputDTO rpgData) {
        this.name = rpgData.getName();
        this.basedAt = rpgData.getBasedAt();
        this.description = rpgData.getDescription();
        this.decisionMethod = rpgData.getDecisionMethod();
        this.minPlayers = rpgData.getMinPlayers();
        this.maxPlayers = rpgData.getMaxPlayers();
        this.createdBy = rpgData.getCreatedBy();
        this.isPublic = rpgData.getIsPublic();

        return this;
    }
}

