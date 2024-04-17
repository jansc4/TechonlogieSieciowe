package edu.ib.technologiebyadamski.controller.dto;

import edu.ib.technologiebyadamski.commonTypes.Role;

public class RegisterDto {
    private String password;
    private String userName;
    private Role role;
    private String email;

    public RegisterDto(String password, String userName, Role role, String email) {
        this.password = password;
        this.userName = userName;
        this.role = role;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
