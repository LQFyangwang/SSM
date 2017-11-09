package com.gs.bean;

public class Permission {  //权限
    private Long id;
    private String permission;  //角色能够使用的权限
    private String desZh;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDesZh() {
        return desZh;
    }

    public void setDesZh(String desZh) {
        this.desZh = desZh;
    }
}
