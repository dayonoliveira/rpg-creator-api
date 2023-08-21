package com.rpgcreator.rpgcreatorapi.repositories.rpg;

import com.rpgcreator.rpgcreatorapi.entities.rpg.Rpg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RpgRepository extends JpaRepository<Rpg, Long> {}
