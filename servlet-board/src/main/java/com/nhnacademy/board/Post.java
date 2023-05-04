package com.nhnacademy.board;

import java.time.LocalDateTime;
import java.util.Date;

public class Post implements IPost{
    private Long id;

    private String title;

    private String content;
    private String writerUserId;
    private Date writeTime;

    public Post(Long id, String title, String content,String writerUserId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerUserId = writerUserId;
        writeTime = new Date();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getWriterUserId() {
        return writerUserId;
    }

    @Override
    public void setWriterUserId(String writerUserId) {
        this.writerUserId = writerUserId;
    }

    @Override
    public Date getWriteTime() {
        return writeTime;
    }

    @Override
    public void setWriteTime(Date writeTime) {
    }

    @Override
    public int getViewCount() {
        return 0;
    }

    @Override
    public void increaseViewCount() {

    }
}
