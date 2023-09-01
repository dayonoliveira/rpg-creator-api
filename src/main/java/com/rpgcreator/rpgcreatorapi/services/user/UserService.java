package com.rpgcreator.rpgcreatorapi.services.user;

import com.rpgcreator.rpgcreatorapi.dtos.input.user.CreateUserInputDTO;
import com.rpgcreator.rpgcreatorapi.dtos.input.user.UpdatePasswordInputDTO;
import com.rpgcreator.rpgcreatorapi.dtos.output.user.GetUsersOutputDTO;
import com.rpgcreator.rpgcreatorapi.dtos.output.user.UserStatisticsOutputDTO;
import com.rpgcreator.rpgcreatorapi.dtos.output.user.UserDetailsOutputDTO;
import com.rpgcreator.rpgcreatorapi.dtos.output.user.types.RpgCreated;
import com.rpgcreator.rpgcreatorapi.entities.user.User;
import com.rpgcreator.rpgcreatorapi.helpers.PatchMappingHelper;
import com.rpgcreator.rpgcreatorapi.repositories.rpg.RpgRepository;
import com.rpgcreator.rpgcreatorapi.repositories.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RpgRepository rpgRepository;

    public List<GetUsersOutputDTO> getAllUsers(String nickname) {
        if (nickname != null) {
            return userRepository.findAllByNicknameStartsWith(nickname).stream().map(GetUsersOutputDTO::new).toList();
        }

        return userRepository.findAll().stream().map(GetUsersOutputDTO::new).toList();
    }

    public UserDetailsOutputDTO getUserById(Long userId) {

        return new UserDetailsOutputDTO(userRepository.findById(userId).get());
    }

    @Transactional
    public User createUser(CreateUserInputDTO userData) {
        userData.setPassword(BCrypt.hashpw(userData.getPassword(), BCrypt.gensalt()));

        return userRepository.save(new User(userData));
    }

    @Transactional
    public User updateUser(Long userId, Map<String, Object> userData) {
        return new PatchMappingHelper<User>().updateSmartly(userId, "userId", userRepository, new User(), userData).get();
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).get();

        rpgRepository.deleteAllByCreatedBy(user);

        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUserPassword(Long userId, UpdatePasswordInputDTO newPassword) {

        User user = userRepository.findById(userId).get();

        userRepository.save(user.updateUserPassword(newPassword));
    }

    public UserStatisticsOutputDTO getUserStatistics(Long userId) {
        User user = userRepository.findById(userId).get();
        List<RpgCreated> userRpgList = rpgRepository.findAllByCreatedBy(user).stream().map(value -> new RpgCreated(value.getRpgId(), value.getIsPublic())).toList();

        return new UserStatisticsOutputDTO(userRpgList.size(), userRpgList);
    }
}
