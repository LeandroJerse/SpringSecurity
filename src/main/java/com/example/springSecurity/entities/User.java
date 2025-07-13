package com.example.springSecurity.entities;


import com.example.springSecurity.dto.loginDTO.LoginRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    UUID id;

    @Column(unique = true)
    private String userName;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles =  new HashSet<>();

    public boolean hasRole(String roleName){
        for (Role role : roles){
            if(role.getAuthority().equalsIgnoreCase(roleName)) {
                return true;
            }
        }
        return false;
    }
    public void addRole(Role role){
        roles.add(role);
    }

    public boolean isLoginCorrect(LoginRequest loginRequest, PasswordEncoder  passwordEncoder) {
        return passwordEncoder.matches(loginRequest.password(), this.password);

    }



}
