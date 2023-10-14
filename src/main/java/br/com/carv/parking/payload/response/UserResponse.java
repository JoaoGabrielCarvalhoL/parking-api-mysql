package br.com.carv.parking.payload.response;

import br.com.carv.parking.enumerations.Role;

import java.util.UUID;

public class UserResponse {
    private UUID id;
    private String name;
    private String email;
    private String username;
    private Role role;

    public UserResponse() {}

    public UserResponse(UUID id, String name, String email, String username, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
