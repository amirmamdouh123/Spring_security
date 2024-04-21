package com.example.demo.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Table(name = "ROLES_AMIR", schema = "HR")
@Setter
@Getter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="Role")
    String Role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Roles_Authorties_Amir",
               schema = "HR",
               joinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name="Authority_id",referencedColumnName = "id"))
    List<Authority> authorities;

}
