package edu.ib.technologiebyadamski.controller.dto;

import edu.ib.technologiebyadamski.commonTypes.Role;

public class RegisterResponseDto {
    private String userName;
    private Role role;

    public RegisterResponseDto(String userName, Role role) {
        this.userName = userName;
        this.role = role;
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
}
