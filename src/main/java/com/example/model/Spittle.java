package com.example.model;

public class Spittle {
    long id;
    long authorId;
    String content;

    public Spittle(long id, long authorId, String content) {
        this.id = id;
        this.authorId = authorId;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
