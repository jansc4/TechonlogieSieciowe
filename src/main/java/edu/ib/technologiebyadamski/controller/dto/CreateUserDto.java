package edu.ib.technologiebyadamski.controller.dto;

import edu.ib.technologiebyadamski.infrastructure.entity.Role;

public class CreateUserDto {
    private String userName;
    private String password;
    private Role role;

    private String email;

    private String name;

    public CreateUserDto(String userName, String password, Role role, String email, String name) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.email = email;
        this.name = name;
    }

    public CreateUserDto() {
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
