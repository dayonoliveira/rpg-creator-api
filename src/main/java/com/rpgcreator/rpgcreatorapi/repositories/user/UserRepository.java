package com.rpgcreator.rpgcreatorapi.repositories.user;

import com.rpgcreator.rpgcreatorapi.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByNicknameStartsWith(String nickname);

    UserDetails findByNickname(String nickname);
}
