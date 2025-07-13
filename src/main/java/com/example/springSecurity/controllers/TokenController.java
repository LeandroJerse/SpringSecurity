package com.example.springSecurity.controllers;

import com.example.springSecurity.dto.loginDTO.LoginRequest;
import com.example.springSecurity.dto.loginDTO.LoginResponse;
import com.example.springSecurity.entities.User;
import com.example.springSecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class TokenController {

    @Autowired
    private final JwtEncoder jwtEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public TokenController(JwtEncoder jwtEncoder, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Confirma login e retorna token
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

         var user =userRepository.findByUserName(loginRequest.userName());

         if(user.isEmpty() || !user.get().isLoginCorrect(loginRequest, passwordEncoder)){
             throw new BadCredentialsException("Invalid username or password");
         }
        var expiresIn = 300L;

         var claims = JwtClaimsSet.builder()
                 .issuer(loginRequest.userName())
                 .subject(user.get().getId().toString())
                 .expiresAt(Instant.now().plusSeconds(expiresIn))
                 .issuedAt(Instant.now())
                 .build();

         //Pega o token utilizando os claims
         var jwtvalue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
         return ResponseEntity.ok(new LoginResponse(jwtvalue,expiresIn));

    }
}
