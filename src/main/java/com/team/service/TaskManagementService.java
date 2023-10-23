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
    public List<UserDTO> getUsers() throws TaskManagementException;
    public List<TaskDTO> getTasks() throws TaskManagementException;
    public User getUserById(Integer userId) throws TaskManagementException;
    public Task getTaskById(Integer taskId) throws TaskManagementException;
    public void addUser(UserDTO userDTO) throws TaskManagementException;
    public void addTask(TaskDTO taskDTO) throws TaskManagementException;
    public void deleteUser(Integer id) throws TaskManagementException;
    public void deleteTask(Integer id) throws TaskManagementException;
    public void updateUserEmail(Integer userId, String userEmail) throws TaskManagementException;
    public void autoUpdateTaskStatus(Integer taskId) throws TaskManagementException;
    public void manuallyUpdateTaskStatus(Integer taskId, Status status) throws TaskManagementException;
    public void assignUsersToTask(Integer taskId, List<Integer> listOfUsersIds) throws TaskManagementException;
    //GET WITH FILTERS
    public List<UserDTO> getUsersWithNameOrSurnameOrEmailLike(String snippet) throws TaskManagementException;
    public List<TaskDTO> getTasksWithTitleLike(String snippet) throws TaskManagementException;
    public List<TaskDTO> getTasksWithGivenStatus(Status status) throws TaskManagementException;
    public List<TaskDTO> getTasksWithDateAfterGivenDate(LocalDate date) throws TaskManagementException;
    public List<TaskDTO> getTasksWithDateBeforeGivenDate(LocalDate date) throws TaskManagementException;
    public List<TaskDTO> getTasksWithGivenNumberOfAssignedUsers(Integer numberOfUsers) throws TaskManagementException;
}
