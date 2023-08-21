package com.rpgcreator.rpgcreatorapi.resources.rpg;

import com.rpgcreator.rpgcreatorapi.dtos.input.rpg.CreateRpgInputDTO;
import com.rpgcreator.rpgcreatorapi.dtos.output.RpgOutputDTO;

import com.rpgcreator.rpgcreatorapi.services.rpg.RpgService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/rpg")
public class RpgResource {

    @Autowired
    private RpgService rpgService;

    @GetMapping
    public ResponseEntity<List<RpgOutputDTO>> getRpgs(
            @RequestParam(required = false) Long rpgId
    ) {
        List<RpgOutputDTO> rpgs;
        if (rpgId == null) {
            rpgs = this.rpgService.getAllRpgs();
        } else {
            rpgs = this.rpgService.getRpgById(rpgId);
        }

        return new ResponseEntity<>(rpgs, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createRpg(
            @RequestBody
            @Valid
            CreateRpgInputDTO rpgData
    ) {
        this.rpgService.createRpg(rpgData);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/update")
    public ResponseEntity<Void> updateRpg(
            @RequestBody
            @Valid
            Map<String, Object> rpgData,
            @RequestParam Long rpgId
    ) {
        this.rpgService.updateRpg(rpgId, rpgData);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteRpg(
            @RequestParam Long rpgId
    ) {
        this.rpgService.deleteRpg(rpgId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
