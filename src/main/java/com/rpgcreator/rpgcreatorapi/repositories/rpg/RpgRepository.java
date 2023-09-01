package com.rpgcreator.rpgcreatorapi.repositories.rpg;

import com.rpgcreator.rpgcreatorapi.entities.rpg.Rpg;
import com.rpgcreator.rpgcreatorapi.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RpgRepository extends JpaRepository<Rpg, Long> {
    List<Rpg> findAllByCreatedBy(User user);

    void deleteAllByCreatedBy(User user);
}
