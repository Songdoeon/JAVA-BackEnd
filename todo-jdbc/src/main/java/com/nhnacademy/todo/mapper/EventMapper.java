package com.nhnacademy.todo.mapper;

import com.nhnacademy.todo.domain.Event;
import org.apache.ibatis.annotations.Mapper;

import javax.validation.constraints.Max;

@Mapper
public interface EventMapper {

    void save(Event event);
}
