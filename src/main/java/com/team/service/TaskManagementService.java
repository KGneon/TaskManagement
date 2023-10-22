package com.team.service;

import com.team.exception.TaskManagementException;
import com.team.model.Task;
import com.team.model.User;

import java.util.List;

public interface TaskManagementService {
    public List<User> getUsers() throws TaskManagementException;
    public List<Task> getTasks() throws TaskManagementException;
    public User getUserById() throws TaskManagementException;
    public Task getTaskById() throws TaskManagementException;
    //lepiej void czy object
    public void addUser(User user) throws TaskManagementException;
    public void addTask(Task task) throws TaskManagementException;
    public void deleteUser(Integer id) throws TaskManagementException;
    public void deleteTask(Integer id) throws TaskManagementException;

    public void assignUsersToTask(List<Integer> listOfUsersIds) throws TaskManagementException;
}
