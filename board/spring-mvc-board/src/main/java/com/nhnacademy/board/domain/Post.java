package com.nhnacademy.board.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import java.util.Date;


public class Post implements IPost{
    private Long id;
    private String title;
    private String content;
    private String writerUserId;
    private int viewCount;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date writeTime;

    public Post(Long id, String title, String content,String writerUserId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerUserId = writerUserId;
        writeTime = new Date();
        this.viewCount = 0;
    }
    public Post(){

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
        this.writeTime = writeTime;
    }

    @Override
    public int getViewCount() {
        return viewCount;
    }
    @Override
    public void increaseViewCount() {
        viewCount++;
    }
}
