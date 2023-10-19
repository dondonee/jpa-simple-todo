package com.example.todo.service;

import com.example.todo.domain.Priority;
import com.example.todo.domain.Task;
import com.example.todo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Long add(String name, Priority priority) {
        checkDuplicateTaskName(name);
        Task task = Task.createTask(name, priority);
        taskRepository.save(task);
        return task.getId();
    }

    public Task findOne(Long id) {
        return taskRepository.findOne(id);
    }

    private void checkDuplicateTaskName(String name) {
        List<Task> findTasks = taskRepository.findByName(name);

        if (!findTasks.isEmpty()) {
            throw new IllegalStateException("동일한 이름의 할 일이 이미 존재합니다.");
        }
    }


}
