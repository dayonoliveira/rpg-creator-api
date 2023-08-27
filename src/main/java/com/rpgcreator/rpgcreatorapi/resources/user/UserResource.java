package com.rpgcreator.rpgcreatorapi.resources.user;

import com.rpgcreator.rpgcreatorapi.dtos.input.user.CreateUserInputDTO;
import com.rpgcreator.rpgcreatorapi.dtos.input.user.UpdatePasswordInputDTO;
import com.rpgcreator.rpgcreatorapi.dtos.output.user.GetUsersOutputDTO;
import com.rpgcreator.rpgcreatorapi.dtos.output.user.UserStatisticsOutputDTO;
import com.rpgcreator.rpgcreatorapi.dtos.output.user.UserDetailsOutputDTO;
import com.rpgcreator.rpgcreatorapi.entities.user.User;
import com.rpgcreator.rpgcreatorapi.services.user.UserService;
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
@RequestMapping("/user")
public class UserResource {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<GetUsersOutputDTO>> getUsers(
        @RequestParam(name = "nickname", required = false)
        String nickname
    ) {
        return ResponseEntity.ok(this.userService.getAllUsers(nickname));
    }

    @GetMapping("/detail")
    public ResponseEntity<UserDetailsOutputDTO> getUserDetails(
        @RequestParam(name = "userId") Long userId
    ) {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<UserDetailsOutputDTO> createUser(
        @RequestBody
        @Valid
        CreateUserInputDTO userData,
        UriComponentsBuilder uriComponentsBuilder
    ) {
        User userCreated = this.userService.createUser(userData);

        URI uri = uriComponentsBuilder
                .path("/user/detail")
                .queryParam("userId", userCreated.getUserId())
                .buildAndExpand(userCreated.getUserId())
                .toUri();

        return ResponseEntity.created(uri).body(new UserDetailsOutputDTO(userCreated));
    }

    @PatchMapping("/update")
    public ResponseEntity<UserDetailsOutputDTO> updateUser(
        @RequestBody
        @Valid
        Map<String, Object> userData,
        @RequestParam(name = "userId")
        Long userId
    ) {
        User userUpdated = this.userService.updateUser(userId, userData);

        return ResponseEntity.ok(new UserDetailsOutputDTO(userUpdated));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(
        @RequestParam(name = "userId")
        Long userId
    ) {
        this.userService.deleteUser(userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/updatePassword")
    public ResponseEntity<Void> updateUserPassword(
        @RequestBody
        @Valid
        UpdatePasswordInputDTO newPassword,
        @RequestParam(name = "userId")
        Long userId
    ) {
        this.userService.updateUserPassword(userId, newPassword);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/statistics")
    public ResponseEntity<UserStatisticsOutputDTO> getUserStatistics(
        @RequestParam(name = "userId")
        Long userId
    ) {
        return ResponseEntity.ok(this.userService.getUserStatistics(userId));
    }
}
