package com.nhnacademy.familycertification.domain;

import lombok.Data;

@Data
public class PageDTO {

    private int pageStart;
    private int pageEnd;
    private boolean next;
    private boolean prev;
    private int size;
    private int pageNum;
    private long total;

    public PageDTO(int pageNum, int size, long total) {
        this.pageNum = pageNum;
        this.size = size;
        this.total = total;

        this.pageEnd = (int) (Math.ceil(this.pageNum / 10.0)) * 10;
        int realEnd = (int) (Math.ceil(total * 1.0 / this.size));

        if (this.pageEnd >= realEnd) {
            this.pageEnd = realEnd;
        }

        if (this.pageEnd == 0) {
            this.pageEnd = realEnd;
        }

        this.pageStart = this.pageEnd - 9;
        if (this.pageStart < 0) {
            this.pageStart = 0;
        }


        this.prev = this.pageStart > 1;
        this.next = this.pageEnd < realEnd;
    }

}