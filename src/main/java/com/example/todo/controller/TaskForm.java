package com.example.todo.controller;

import com.example.todo.domain.Priority;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
public class TaskForm {

    @NotBlank
    @Length(max = 24)
    private String name;

    @NotNull
    private Priority priority;
}
