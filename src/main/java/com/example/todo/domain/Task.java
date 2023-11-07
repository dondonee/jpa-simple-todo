package com.example.todo.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Task {

    @Id @GeneratedValue
    @Column(name = "task_id")
    private Long id;

    private LocalDateTime regDate;

    @Column(unique = true, length = 24)
    private String name;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    //== 생성 메서드 ==//
    public static Task createTask(String name, Priority priority) {
        Task task = new Task();
        task.setRegDate(LocalDateTime.now());
        task.setName(name);
        task.setPriority(priority);
        task.setStatus(Status.TODO);

        return task;
    }

    //== Setter ==//

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
