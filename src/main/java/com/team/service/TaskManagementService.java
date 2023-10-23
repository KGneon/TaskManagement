package com.team.service;

import com.team.exception.TaskManagementException;
import com.team.model.Status;
import com.team.model.Task;
import com.team.model.User;

import java.time.LocalDate;
import java.util.List;

public interface TaskManagementService {
    public List<User> getUsers() throws TaskManagementException;
    public List<Task> getTasks() throws TaskManagementException;
    public User getUserById(Integer userId) throws TaskManagementException;
    public Task getTaskById(Integer taskId) throws TaskManagementException;
    public void addUser(User user) throws TaskManagementException;
    public void addTask(Task task) throws TaskManagementException;
    public void deleteUser(Integer id) throws TaskManagementException;
    public void deleteTask(Integer id) throws TaskManagementException;
    public void updateUserEmail(Integer userId, String userEmail) throws TaskManagementException;
    public void updateTaskStatus(Integer taskId) throws TaskManagementException;
    public void assignUsersToTask(Integer taskId, List<Integer> listOfUsersIds) throws TaskManagementException;
    //GET WITH FILTERS
    public List<User> getUsersWithNameOrSurnameOrEmailLike(String snippet) throws TaskManagementException;
    public List<Task> getTasksWithTitleLike(String snippet) throws TaskManagementException;
    public List<Task> getTasksWithGivenStatus(Status status) throws TaskManagementException;
    public List<Task> getTasksWithDateAfterGivenDate(LocalDate date) throws TaskManagementException;
    public List<Task> getTasksWithDateBeforeGivenDate(LocalDate date) throws TaskManagementException;
    public List<Task> getTasksWithGivenNumberOfAssignedUsers(Integer numberOfUsers) throws TaskManagementException;

}
