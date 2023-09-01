package com.rpgcreator.rpgcreatorapi.entities.user;

import com.rpgcreator.rpgcreatorapi.dtos.input.user.CreateUserInputDTO;
import com.rpgcreator.rpgcreatorapi.dtos.input.user.UpdatePasswordInputDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "User")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "userId")
public class User implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getUsername() {
        return this.nickname;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
