package com.example.demo.Entities;

import jakarta.persistence.*;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "AUTHORTIES_AMIR", schema = "HR")
@Setter
public class Authority implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="Authority")
    String Authority;

    @Override
    public String getAuthority() {
        return Authority;
    }
}
