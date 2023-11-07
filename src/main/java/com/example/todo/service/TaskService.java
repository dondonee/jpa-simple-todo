package com.example.todo.service;

import com.example.todo.domain.Priority;
import com.example.todo.domain.Status;
import com.example.todo.domain.Task;
import com.example.todo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    @Transactional
    public Long add(String name, Priority priority) {

        if (checkDuplicateTaskName(name)) {
            throw new IllegalStateException("동일한 이름의 할 일이 이미 존재합니다.");
        }

        Task task = Task.createTask(name, priority);
        taskRepository.save(task);
        return task.getId();
    }

    public Task findOne(Long id) {
        return taskRepository.findOne(id);
    }

    public List<Task> findTasksByStatus(Status status) {
        List<Task> findTasks = taskRepository.findAllOrderedByRegDate();
        List<Task> result = new ArrayList<>();

        for (Task task : findTasks) {
            if (task.getStatus() == status) {
                result.add(task);
            }
        }

        return result;
    }

    @Transactional
    public void deleteOne(Long id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public void updateTaskStatus(Long id) {
        Task task = taskRepository.findOne(id);
        Status taskStatus = task.getStatus();

        if (taskStatus == Status.TODO) {
            task.setStatus(Status.DOING);
        } else if (taskStatus == Status.DOING) {
            task.setStatus(Status.DONE);
        } else {
            throw new IllegalStateException("할 일의 상태를 변경할 수 없습니다.");
        }
    }

    @Transactional
    public void editTask(Long id, String name, Priority priority) {
        Task task = taskRepository.findOne(id);

        task.setName(name);
        task.setPriority(priority);
    }

    public boolean checkDuplicateTaskName(String name) {

        if (taskRepository.findByName(name).isEmpty()) {
            return false;
        }
        return true;
    }

}
