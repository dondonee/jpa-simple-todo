package com.example.todo.controller;

import com.example.todo.domain.Priority;
import com.example.todo.domain.Status;
import com.example.todo.domain.Task;
import com.example.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    /**
     * 테스트 데이터 삽입용 - 추후 삭제 요망
     */
    @GetMapping("/")
    public String setUp() {

        Long taskId1 = taskService.add("task1", Priority.HIGH);
        taskService.add("task2", Priority.MEDIUM);
        taskService.add("task3", Priority.LOW);
        Long taskId4 = taskService.add("task4", Priority.HIGH);

        taskService.updateTaskStatus(taskId1);
        taskService.updateTaskStatus(taskId4);
        taskService.updateTaskStatus(taskId4);

        return "redirect:/tasks";
    }

    @GetMapping("/tasks")
    public String taskList(Model model) {

        List<Task> todos = taskService.findTasksByStatus(Status.TODO);
        List<Task> doings = taskService.findTasksByStatus(Status.DOING);
        List<Task> dones = taskService.findTasksByStatus(Status.DONE);

        model.addAttribute("todos", todos);
        model.addAttribute("doings", doings);
        model.addAttribute("dones", dones);

        return "task/taskList";
    }

    @GetMapping("/tasks/new")
    public String addTaskForm(Model model) {
        model.addAttribute("taskForm", new TaskForm());
        return "task/addTaskForm";
    }

    @PostMapping("/tasks/new")
    public String addTask(@Valid TaskForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "task/addTaskForm";
        }

        System.out.println(form.getPriority());

        taskService.add(form.getName(), form.getPriority());
        return "redirect:/tasks";

    }
}
