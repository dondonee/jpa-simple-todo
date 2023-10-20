package com.example.todo.controller;

import com.example.todo.domain.Priority;
import com.example.todo.domain.Status;
import com.example.todo.domain.Task;
import com.example.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    /**
     * 테스트 데이터 삽입용 - 추후 삭제 요망
     * */
    @GetMapping("set")
    public String setUp() {

        Long taskId1 = taskService.add("task1", Priority.HIGH);
        taskService.add("task2", Priority.MEDIUM);
        taskService.add("task3", Priority.LOW);
        Long taskId4 = taskService.add("task4", Priority.HIGH);

        taskService.updateTaskStatus(taskId1);
        taskService.updateTaskStatus(taskId4);
        taskService.updateTaskStatus(taskId4);

        return "redirect:/";
    }

    @GetMapping("/")
    public String taskList(Model model) {

        List<Task> todos = taskService.findTasksByStats(Status.TODO);
        List<Task> doings = taskService.findTasksByStats(Status.DOING);
        List<Task> dones = taskService.findTasksByStats(Status.DONE);

        model.addAttribute("todos", todos);
        model.addAttribute("doings", doings);
        model.addAttribute("dones", dones);

        return "task/taskList";
    }
}
