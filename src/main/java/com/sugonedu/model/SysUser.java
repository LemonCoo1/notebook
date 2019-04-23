package com.sugonedu.model;

public class SysUser {
    private String id;
    private String username;
    private String password;
    private String email;
    private String image;
    private String createTime;
    private Integer deleteStutus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getDeleteStutus() {
        return deleteStutus;
    }

    public void setDeleteStutus(Integer deleteStutus) {
        this.deleteStutus = deleteStutus;
    }

    public SysUser() {
    }
}
