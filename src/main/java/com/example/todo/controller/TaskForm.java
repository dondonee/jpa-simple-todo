package com.example.todo.controller;

import com.example.todo.domain.Priority;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
public class TaskForm {

//    @NotEmpty(message = "제목을 입력하세요.")
//    @Size(min= 1 , max = 24, message = "")
    private String name;
//    @NotNull(message = "우선순위를 체크하세요.")
    private Priority priority;
}
