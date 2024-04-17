package edu.ib.technologiebyadamski.controller.dto;

import edu.ib.technologiebyadamski.commonTypes.Role;


public class GetUserDto {
    private long userId;

    private String userName;
    private Role role;

    private String email;

    private String name;

    public GetUserDto(long userId, String userName, Role role, String email, String name) {
        this.userId = userId;
        this.userName = userName;
        this.role = role;
        this.email = email;
        this.name = name;
    }

    public GetUserDto() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
