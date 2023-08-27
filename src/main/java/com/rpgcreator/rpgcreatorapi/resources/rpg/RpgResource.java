package com.rpgcreator.rpgcreatorapi.resources.rpg;

import com.rpgcreator.rpgcreatorapi.dtos.input.rpg.CreateRpgInputDTO;
import com.rpgcreator.rpgcreatorapi.dtos.output.rpg.RpgsOutputDTO;

import com.rpgcreator.rpgcreatorapi.entities.rpg.Rpg;
import com.rpgcreator.rpgcreatorapi.services.rpg.RpgService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/rpg")
public class RpgResource {

    @Autowired
    private RpgService rpgService;

    @GetMapping
    public ResponseEntity<List<RpgsOutputDTO>> getRpgs() {
        return ResponseEntity.ok(this.rpgService.getAllRpgs());
    }

    @GetMapping("/detail")
    public ResponseEntity<RpgsOutputDTO> getRpgDetails(
        @RequestParam(name = "rpgId")
        Long rpgId
    ) {
        return ResponseEntity.ok(this.rpgService.getRpgById(rpgId));
    }

    @PostMapping("/create")
    public ResponseEntity<RpgsOutputDTO> createRpg(
            @RequestBody
            @Valid
            CreateRpgInputDTO rpgData,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Rpg rpgCreated = this.rpgService.createRpg(rpgData);

        URI uri = uriComponentsBuilder
                .path("/rpg")
                .queryParam("rpgId", rpgCreated.getRpgId())
                .buildAndExpand(rpgCreated.getRpgId())
                .toUri();

        return ResponseEntity.created(uri).body(new RpgsOutputDTO(rpgCreated));
    }

    @PatchMapping("/update")
    public ResponseEntity<RpgsOutputDTO> updateRpg(
            @RequestBody
            @Valid
            Map<String, Object> rpgData,
            @RequestParam Long rpgId
    ) {
        Rpg rpgUpdated = this.rpgService.updateRpg(rpgId, rpgData);

        return ResponseEntity.ok(new RpgsOutputDTO(rpgUpdated));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteRpg(
            @RequestParam Long rpgId
    ) {
        this.rpgService.deleteRpg(rpgId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
