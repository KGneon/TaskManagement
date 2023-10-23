package com.team.dto;

import com.team.model.Status;
import com.team.model.Task;
import com.team.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.List;

public class TaskDTO {
    private Integer id;
    @NotNull(message = "{}")
    private String title;
    @NotNull(message = "{}")
    private String description;
    @NotNull(message = "{}")
    @Pattern(regexp = "^(FINISHED|IN_PROGRESS|LATE_IN_PROGRESS|ASSIGNED|UNASSIGNED|CANCELLED)$")
    private Status status;
    @NotNull(message = "{}")
    @Future(message = "{}")
    private LocalDate expectedCompletionDate;
    private List<User> users;
    @NotNull(message = "{}")
    @Positive(message = "{}")
    private Integer expectedUsersNumber;

    public TaskDTO() {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public LocalDate getExpectedCompletionDate() {
        return expectedCompletionDate;
    }
    public void setExpectedCompletionDate(LocalDate expectedCompletionDate) {
        this.expectedCompletionDate = expectedCompletionDate;
    }
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Integer getExpectedUsersNumber() {
        return expectedUsersNumber;
    }

    public void setExpectedUsersNumber(Integer expectedUsersNumber) {
        this.expectedUsersNumber = expectedUsersNumber;
    }

    public static TaskDTO createDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setExpectedCompletionDate(task.getExpectedCompletionDate());
        taskDTO.setUsers(task.getUsers());
        taskDTO.setExpectedUsersNumber(task.getExpectedUsersNumber());
        return taskDTO;
    }
    public static Task createEntity(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setExpectedCompletionDate(taskDTO.getExpectedCompletionDate());
        task.setUsers(taskDTO.getUsers());
        task.setExpectedUsersNumber(taskDTO.getExpectedUsersNumber());
        return task;
    }
}
