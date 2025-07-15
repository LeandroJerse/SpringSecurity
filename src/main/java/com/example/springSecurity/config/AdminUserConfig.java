package com.example.springSecurity.config;

import com.example.springSecurity.entities.Role;
import com.example.springSecurity.entities.User;
import com.example.springSecurity.repositories.RoleRepository;
import com.example.springSecurity.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Configuration
public class AdminUserConfig implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception{

        var rolesAdmin = roleRepository.findByAuthority(Role.values.ADMIN.name());

        var userAdmin = userRepository.findByUserName("admin");

        userAdmin.ifPresentOrElse((user) -> {
            System.out.println("Admin já existe");
            },
                () -> {
            var user = new User();
            user.setPassword(passwordEncoder.encode("123"));
            user.setUserName("admin");
            user.setRoles(Set.of(rolesAdmin));
            userRepository.save(user);
                });

    }
}
