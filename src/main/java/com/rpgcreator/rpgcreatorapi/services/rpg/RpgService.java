package com.rpgcreator.rpgcreatorapi.services.rpg;

import com.rpgcreator.rpgcreatorapi.dtos.input.rpg.CreateRpgInputDTO;
import com.rpgcreator.rpgcreatorapi.dtos.output.rpg.RpgsOutputDTO;
import com.rpgcreator.rpgcreatorapi.entities.rpg.Rpg;
import com.rpgcreator.rpgcreatorapi.entities.user.User;
import com.rpgcreator.rpgcreatorapi.helpers.PatchMappingHelper;
import com.rpgcreator.rpgcreatorapi.repositories.rpg.RpgRepository;
import com.rpgcreator.rpgcreatorapi.repositories.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RpgService {
    @Autowired
    private final RpgRepository rpgRepository;
    @Autowired
    private final UserRepository userRepository;

    public List<RpgsOutputDTO> getAllRpgs() {

        return this.rpgRepository.findAll().stream().map(RpgsOutputDTO::new).toList();
    }

    public RpgsOutputDTO getRpgById(Long rpgId) {

        return new RpgsOutputDTO(this.rpgRepository.findById(rpgId).get());
    }

    @Transactional
    public Rpg createRpg(CreateRpgInputDTO rpgData) {
        User userCreator = this.userRepository.findById(rpgData.getCreatedBy()).get();

        if (rpgData.getMaxPlayers() == null || rpgData.getMaxPlayers() == 0) rpgData.setMaxPlayers(99);

        return this.rpgRepository.save(new Rpg(rpgData, userCreator));
    }

    @Transactional
    public Rpg updateRpg(Long rpgId, Map<String, Object> rpgData) {

        return new PatchMappingHelper<Rpg>().updateSmartly(rpgId, "rpgId", this.rpgRepository, new Rpg(), rpgData).get();
    }

    @Transactional
    public void deleteRpg(Long rpgId) {
        this.rpgRepository.deleteById(rpgId);
    }
}
