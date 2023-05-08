package com.nhnacademy.board;

import java.time.LocalDateTime;
import java.util.Date;

public interface IPost {
    Long getId(); // 게시물을 등록하면 id 값을 반환
    void setId(Long id);

    String getTitle();
    void setTitle(String title);

    String getContent();
    void setContent(String content);

    String getWriterUserId();
    void setWriterUserId(String writerUserId);

    Date getWriteTime();
    void setWriteTime(Date writeTime);

    int getViewCount();
    void increaseViewCount();
}