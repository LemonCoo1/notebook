package com.sugonedu.model;

/**
 * @author
 * @date 2019/4/23
 */
public class Note {
    private String id;

    private String title;

    private String content;

    private Integer favor;

    private String groupId;

    private String userId;

    public Note() {
    }

    public Note(String id, String title, String content, Integer favor) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.favor = favor;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFavor() {
        return favor;
    }

    public void setFavor(Integer favor) {
        this.favor = favor;
    }

    public Note(String id, String title, String content, Integer favor, String groupId, String userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.favor = favor;
        this.groupId = groupId;
        this.userId = userId;
    }
}
