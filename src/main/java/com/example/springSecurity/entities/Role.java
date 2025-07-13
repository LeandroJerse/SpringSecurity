package com.example.springSecurity.entities;


import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_role")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "role_id")
    private Long roleID;
    private String authority;

    public enum values {
        ADMIN(2L), USER(1L);

        Long roleId;
        values(long roleId){
            this.roleId = roleId;
        }
        public long getRoleId(){
            return roleId;
        }

    }
}
