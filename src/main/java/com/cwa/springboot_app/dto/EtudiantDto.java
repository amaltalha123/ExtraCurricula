package com.cwa.springboot_app.dto;

import com.cwa.springboot_app.entity.RoleBureau;

public class EtudiantDto {
    private String cne;
    private String username;
    private String fullname;
    private RoleBureau roleBureau;

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public RoleBureau getRoleBureau() {
        return roleBureau;
    }

    public void  setRoleBureau(RoleBureau roleBureau) {
        this.roleBureau = roleBureau;
    }

   
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
