package com.team.service;

import com.team.dto.TaskDTO;
import com.team.dto.UserDTO;
import com.team.exception.TaskManagementException;
import com.team.model.Status;
import com.team.model.Task;
import com.team.model.User;

import java.time.LocalDate;
import java.util.List;

public interface TaskManagementService {
    public List<UserDTO> getUsers();
    public List<TaskDTO> getTasks();
    public User getUserById(Integer userId);
    public Task getTaskById(Integer taskId);
    public List<UserDTO> getUsersWithNameOrSurnameOrEmailLike(String snippet);
    public List<TaskDTO> getTasksWithTitleOrDescriptionLike(String snippet);
    public List<TaskDTO> getTasksWithGivenStatus(Status status);
    public List<TaskDTO> getTasksWithDateAfterGivenDate(LocalDate date);
    public List<TaskDTO> getTasksWithDateBeforeGivenDate(LocalDate date);
    public List<TaskDTO> getTasksWithGivenNumberOfAssignedUsers(Integer numberOfUsers);
    public void addUser(UserDTO userDTO);
    public void addTask(TaskDTO taskDTO);
    public void deleteUser(Integer id);
    public void deleteTask(Integer id);
    public void updateUserName(Integer userId, String userName);
    public void updateUserSurname(Integer userId, String userSurname);
    public void updateUserEmail(Integer userId, String userEmail);
    public void updateTaskDescription(Integer taskId, String description);
    public void autoUpdateTaskStatus(Integer taskId);
    public void manuallyUpdateTaskStatus(Integer taskId, Status status);
    public void updateTaskExpectedCompletionDate(Integer taskId, LocalDate date);
    public void updateTaskExpectedUsersNumber(Integer taskId, Integer number);
    public void assignUsersToTask(Integer taskId, List<Integer> listOfUsersIds);
    public void unassignUsersFromTask(Integer taskId, List<Integer> listOfUsersIdsDTO);
}
