package com.sugonedu.model;

/**
 * @author xucong
 * @date 2019/4/23
 */
public class Note {
    private String id;

    private String title;

    private String content;

    private Integer favor;

    public Note() {
    }

    public Note(String id, String title, String content, Integer favor) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.favor = favor;
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
}
