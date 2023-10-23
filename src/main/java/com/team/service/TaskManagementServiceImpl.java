package com.team.service;

import com.team.exception.TaskManagementException;
import com.team.model.Status;
import com.team.model.Task;
import com.team.model.User;
import com.team.repository.TaskRepository;
import com.team.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        List<User> listOfUsers = userRepository.findAll();
        if(listOfUsers == null || listOfUsers.isEmpty()) throw new TaskManagementException("Service.NO_USERS");
        return listOfUsers;
    }
    @Override
    public List<Task> getTasks() throws TaskManagementException {
        List<Task> listOfTasks = taskRepository.findAll();
        if(listOfTasks == null || listOfTasks.isEmpty()) throw new TaskManagementException("Service.NO_TASKS");
        return listOfTasks;
    }
    @Override
    public User getUserById(Integer userId) throws TaskManagementException {
        if(userId == null || userId <= 0) throw new TaskManagementException("Service.ID_NEGATIVE_OR_NULL");
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(() -> new TaskManagementException("Service.NO_SUCH_USER_ID"));
        return user;
    }
    @Override
    public Task getTaskById(Integer taskId) throws TaskManagementException {
        if(taskId == null || taskId <= 0) throw new TaskManagementException("Service.ID_NEGATIVE_OR_NULL");
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        Task task = taskOptional.orElseThrow(() -> new TaskManagementException("Service.NO_SUCH_TASK_ID"));
        return task;
    }
    @Override
    public void addUser(User user) throws TaskManagementException {
        if(user != null) userRepository.save(user);
        else throw new TaskManagementException("Service.USER_IS_NULL");
    }
    @Override
    public void addTask(Task task) throws TaskManagementException {
        if(task != null) taskRepository.save(task);
        else throw new TaskManagementException("Service.TASK_IS_NULL");
    }
    @Override
    public void deleteUser(Integer id) throws TaskManagementException {
        User user = getUserById(id);
        userRepository.delete(user);
    }
    @Override
    public void deleteTask(Integer id) throws TaskManagementException {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }

    @Override
    public void updateUserEmail(Integer userId, String userEmail) throws TaskManagementException {
        User user = getUserById(userId);
        user.setEmail(userEmail);
    }
    @Override
    public void updateTaskStatus(Integer taskId) throws TaskManagementException {

    }
    @Override
    public void assignUsersToTask(Integer taskId, List<Integer> listOfUsersIds) throws TaskManagementException {
        if(listOfUsersIds == null || listOfUsersIds.isEmpty()) throw new TaskManagementException("Service.NO_USERS_IDS_GIVEN");
        else {
            Task task = getTaskById(taskId);
            List<User> listOfUsers = task.getUsers();
            listOfUsersIds.forEach(userId -> {
                User user = getUserById(userId);
                listOfUsers.add(user);
            });
            task.setUsers(listOfUsers);
        }
    }
    //-------------------------------------------------------------
    @Override
    public List<User> getUsersWithNameOrSurnameOrEmailLike(String snippet) throws TaskManagementException {
        return null;
    }
    @Override
    public List<Task> getTasksWithTitleLike(String snippet) throws TaskManagementException {
        return null;
    }
    @Override
    public List<Task> getTasksWithGivenStatus(Status status) throws TaskManagementException {
        return null;
    }
    @Override
    public List<Task> getTasksWithDateAfterGivenDate(LocalDate date) throws TaskManagementException {
        return null;
    }
    @Override
    public List<Task> getTasksWithDateBeforeGivenDate(LocalDate date) throws TaskManagementException {
        return null;
    }
    @Override
    public List<Task> getTasksWithGivenNumberOfAssignedUsers(Integer numberOfUsers) throws TaskManagementException {
        return null;
    }
}
