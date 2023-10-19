package com.example.todo.domain;

import com.example.todo.repository.TaskRepository;
import com.example.todo.service.TaskService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TaskTest {

    @Autowired TaskRepository taskRepository;
    @Autowired TaskService taskService;
    @Autowired EntityManager em;

    @Test
    public void 할일_등록() throws Exception {
        //given
        Task task = Task.createTask("task1", Priority.HIGH);
        taskRepository.save(task);
        Long taskId = task.getId();

        //then
        em.flush();
        Assertions.assertEquals(task, taskRepository.findOne(taskId));
    }

    @Test
    public void 중복_예외() throws Exception {
        //given
        Task task1 = Task.createTask("task1", Priority.HIGH);
        taskRepository.save(task1);

        //when
        try {
            taskService.add("task1", Priority.HIGH);
        } catch (IllegalStateException e) {
            return;
        }

        //then
        fail("예외가 발생해야 한다.");
    }

}