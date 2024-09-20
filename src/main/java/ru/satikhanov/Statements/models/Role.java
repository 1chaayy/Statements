package ru.satikhanov.Statements.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idrole;

    @Column(name = "role")
    private String role;

    public String getShortRole() {
        return role.substring(5);
    }

    @Override
    public String getAuthority() {
        return role;
    }
}