package com.nhnacademy.board.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;


@Table(name = "Posts")
@Entity
@Data
@NoArgsConstructor
public class Post{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    private String title;
    private String content;
    @Transient
    private String writerUserId;
    @Column(name = "view_count")
    private int viewCount;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "write_time")
    private Date writeTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post(Long id, String title, String content,String writerUserId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerUserId = writerUserId;
        writeTime = new Date();
        this.viewCount = 0;
    }
    public void increaseViewCount() {
        viewCount++;
    }
}
