package com.sugonedu.model;

public class NoteGroup {
    private String  id;
    private String name;
    private String userId;
    private Boolean selected;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public NoteGroup() {

    }

    public NoteGroup(String id, String name, String userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }
}
