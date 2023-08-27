package com.rpgcreator.rpgcreatorapi.entities.user;

import com.rpgcreator.rpgcreatorapi.dtos.input.user.CreateUserInputDTO;
import com.rpgcreator.rpgcreatorapi.dtos.input.user.UpdatePasswordInputDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "users")
@Entity(name = "User")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "userId")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true)
    private String nickname;
    @Column(unique = true)
    private String email;
    private String password;
    private LocalDate birthdate;
    private String bio;

    public User(CreateUserInputDTO userData) {
        this.nickname = userData.getNickname();
        this.email = userData.getEmail();
        this.password = userData.getPassword();
        this.birthdate = userData.getBirthdate();
        this.bio = userData.getBio();
    }

    public User updateUserPassword(UpdatePasswordInputDTO newPasswordData) {
        this.password = newPasswordData.getPassword();

        return this;
    }
}
