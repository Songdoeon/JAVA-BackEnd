package com.nhnacademy.board.domain;

import org.springframework.stereotype.Component;

@Component
public class Visitor {
    long count;
    public Visitor() {
        this.count = 0;
    }
    public Long getCount() {
        return count;
    }
    public void setCount(Long count) {
        this.count = count;
    }
    public void increaseViewCount(){
        count++;
    }
}
