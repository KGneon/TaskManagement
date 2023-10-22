package com.team.service;

import com.team.exception.TaskManagementException;
import com.team.model.Task;
import com.team.model.User;
import com.team.repository.TaskRepository;
import com.team.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskManagementServiceImpl implements TaskManagementService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    //QUALIFIER?
    @Autowired
    public TaskManagementServiceImpl(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<User> getUsers() throws TaskManagementException {
        return userRepository.findAll();
    }

    @Override
    public List<Task> getTasks() throws TaskManagementException {
        return taskRepository.findAll();
    }

    @Override
    public User getUserById() throws TaskManagementException {
        return null;
    }

    @Override
    public Task getTaskById() throws TaskManagementException {
        return null;
    }

    @Override
    public void addUser(User user) throws TaskManagementException {

    }

    @Override
    public void addTask(Task task) throws TaskManagementException {

    }

    @Override
    public void deleteUser(Integer id) throws TaskManagementException {

    }

    @Override
    public void deleteTask(Integer id) throws TaskManagementException {

    }

    @Override
    public void assignUsersToTask(List<Integer> listOfUsersIds) throws TaskManagementException {
        Task task = taskRepository.
    }
}
