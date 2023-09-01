package com.rpgcreator.rpgcreatorapi.services.auth;

import com.rpgcreator.rpgcreatorapi.dtos.input.auth.AuthUserInputDTO;
import com.rpgcreator.rpgcreatorapi.dtos.output.auth.LoginOutputDTO;
import com.rpgcreator.rpgcreatorapi.entities.user.User;
import com.rpgcreator.rpgcreatorapi.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {

        return userRepository.findByNickname(nickname);
    }

    public LoginOutputDTO validateUserLogin(AuthUserInputDTO userLoginData, AuthenticationManager authenticationManager) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userLoginData.getNickname(), userLoginData.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);

        return new LoginOutputDTO(tokenService.generate((User) authentication.getPrincipal()));
    }
}
