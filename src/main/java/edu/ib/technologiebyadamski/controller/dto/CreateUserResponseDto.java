package edu.ib.technologiebyadamski.controller.dto;

import edu.ib.technologiebyadamski.infrastructure.entity.Role;

public class CreateUserResponseDto {
    private long id;
    private String userName;
    private String password;
    private Role role;

    private String email;

    private String name;

    public CreateUserResponseDto(long id, String userName, String password, Role role, String email, String name) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.email = email;
        this.name = name;
    }

    public CreateUserResponseDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
