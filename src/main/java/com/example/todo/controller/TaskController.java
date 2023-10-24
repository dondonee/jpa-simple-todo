package com.example.todo.controller;

import com.example.todo.domain.Priority;
import com.example.todo.domain.Status;
import com.example.todo.domain.Task;
import com.example.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

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

    @GetMapping("/tasks/{taskId}/edit")
    public String editTaskForm(@PathVariable Long taskId, Model model) {

        Task task = taskService.findOne(taskId);

        TaskForm form = new TaskForm();
        form.setName(task.getName());
        form.setPriority(task.getPriority());

        model.addAttribute("taskForm", form);
        return "task/editTaskForm";
    }

    @PostMapping("/tasks/{taskId}/edit")
    public String editTask(@PathVariable Long taskId, @Valid TaskForm form,
                           BindingResult result) {

        if (result.hasErrors()) {
            Task findTask = taskService.findOne(taskId);
            form.setName(findTask.getName());
            form.setPriority(findTask.getPriority());

            return "task/editTaskForm";
        }

        taskService.editTask(taskId, form.getName(), form.getPriority());
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/{taskId}/delete")
    public String deleteTask(@PathVariable Long taskId) {

        taskService.deleteOne(taskId);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/{taskId}/update")
    public String updateTask(@PathVariable Long taskId) {

        taskService.updateTaskStatus(taskId);
        return "redirect:/tasks";
    }

}
