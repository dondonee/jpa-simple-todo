package com.example.todo.domain;

import ch.qos.logback.core.encoder.EchoEncoder;
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
import javax.persistence.EntityNotFoundException;

import java.util.List;

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

    @Test
    public void 할일_삭제() throws Exception {
        //given
        Long taskId = taskService.add("task1", Priority.HIGH);

        //when
        taskService.deleteOne(taskId);
        em.flush();
        List<Task> findTasks = em.createQuery("select t from Task t", Task.class)
                .getResultList();

        //then
        assertEquals(0, findTasks.size());
    }

    @Test
    public void 할일_상태_업데이트() throws Exception {
        //given
        Long taskId1 = taskService.add("task1", Priority.HIGH);
        Long taskId2 = taskService.add("task2", Priority.HIGH);

        //when
        taskService.updateTaskStatus(taskId1);  //task1: TODO -> DOING
        taskService.updateTaskStatus(taskId2);  //task2: TODO -> DOING -> DONE
        taskService.updateTaskStatus(taskId2);

        Task task1 = taskService.findOne(taskId1);
        Task task2 = taskService.findOne(taskId2);

        //then
        assertEquals(task1.getStatus(), Status.DOING);
        assertEquals(task2.getStatus(), Status.DONE);
    }

    @Test
    public void 할일_수정() throws Exception {
        //given
        Long taskId = taskService.add("task", Priority.HIGH);

        //when
        taskService.editTask(taskId, "editTask", Priority.LOW);
        Task findTask = taskService.findOne(taskId);

        //then
        assertEquals("editTask", findTask.getName());
        assertEquals(Priority.LOW, findTask.getPriority());
        assertEquals(Status.TODO, findTask.getStatus());
    }

    @Test
    public void 할일_시간_내림차순_조회() throws Exception {
        //given
        Long taskId1 = taskService.add("task1", Priority.HIGH);
        Long taskId2 = taskService.add("task2", Priority.HIGH);

        //when
        List<Task> tasks = taskService.findTasks();

        Task task1 = taskService.findOne(taskId1);
        Task task2 = taskService.findOne(taskId2);

        //then
        assertEquals(task2, tasks.get(0));
        assertEquals(task1, tasks.get(1));
    }

}