package com.rpgcreator.rpgcreatorapi.services.rpg;

import com.rpgcreator.rpgcreatorapi.dtos.input.rpg.CreateRpgInputDTO;
import com.rpgcreator.rpgcreatorapi.dtos.output.RpgOutputDTO;
import com.rpgcreator.rpgcreatorapi.entities.rpg.Rpg;
import com.rpgcreator.rpgcreatorapi.repositories.rpg.RpgRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RpgService {
    @Autowired
    private final RpgRepository rpgRepository;

    public List<RpgOutputDTO> getAllRpgs() {
        return rpgRepository.findAll().stream().map(RpgOutputDTO::new).toList();
    }

    public List<RpgOutputDTO> getRpgById(Long rpgId) {
        return rpgRepository.findById(rpgId).stream().map(RpgOutputDTO::new).toList();
    }

    @Transactional
    public Rpg createRpg(CreateRpgInputDTO rpgData) {

        if (rpgData.getMaxPlayers() == null || rpgData.getMaxPlayers() == 0) rpgData.setMaxPlayers(99);

        return rpgRepository.save(new Rpg(rpgData));
    }

    @Transactional
    public Rpg updateRpg(Long rpgId, Map<String, Object> rpgData) {

        Optional<Rpg> rpg = rpgRepository.findById(rpgId);

        rpgData.forEach((propertyName, propertyValue) -> {
            Field field = ReflectionUtils.findField(Rpg.class, propertyName);

            if (!field.getName().equals("rpgId")) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, rpg.get(), propertyValue);
                field.setAccessible(false);
            }
        });

        return rpg.get();
    }

    @Transactional
    public void deleteRpg(Long rpgId) {
        rpgRepository.deleteById(rpgId);
    }
}
