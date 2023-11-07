package com.example.todo.controller;

import com.example.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class TaskValidator implements Validator {

    private final TaskService taskService;

    @Override
    public boolean supports(Class<?> clazz) {
        return TaskForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        TaskForm form = (TaskForm) target;

        if(!StringUtils.hasText(form.getName())) {
            errors.rejectValue("name", "required");
        }
        if (form.getName().length() > 24) {
            errors.rejectValue("name", "range");
        }
        if (taskService.checkDuplicateTaskName(form.getName())) {
            errors.rejectValue("name", "unique");
        }
        if (form.getPriority() == null) {
            errors.rejectValue("priority", "required");
        }
    }
}
