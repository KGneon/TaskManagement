package com.team.service;

import com.team.dto.TaskDTO;
import com.team.dto.UserDTO;
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
import java.util.ArrayList;
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
    public List<UserDTO> getUsers() throws TaskManagementException {
        List<User> listOfUsers = userRepository.findAll();
        if (listOfUsers != null && !listOfUsers.isEmpty()) {
            List<UserDTO> listOfUsersDTO = new ArrayList<>();
            listOfUsers.forEach(u -> {
                UserDTO userDTO = UserDTO.createDTO(u);
                listOfUsersDTO.add(userDTO);
            });
            return listOfUsersDTO;
        } else {
            throw new TaskManagementException("Service.NO_USERS");
        }
    }
    @Override
    public List<TaskDTO> getTasks() throws TaskManagementException {
        List<Task> listOfTasks = taskRepository.findAll();
        if (listOfTasks != null && !listOfTasks.isEmpty()) {
            List<TaskDTO> listOfTasksDTO = new ArrayList<>();
            listOfTasks.forEach(t -> {
                TaskDTO taskDTO = TaskDTO.createDTO(t);
                //verifying status:
                taskDTO.setStatus(TaskDTO.autoCheckAndUpdateStatus(taskDTO));
                listOfTasksDTO.add(taskDTO);
            });
            return listOfTasksDTO;
        } else {
            throw new TaskManagementException("Service.NO_TASKS");
        }
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
    public void addUser(UserDTO userDTO) throws TaskManagementException {
        if(userDTO != null) {
            User user = UserDTO.createEntity(userDTO);
            userRepository.save(user);
        }
        else throw new TaskManagementException("Service.USER_IS_NULL");
    }
    @Override
    public void addTask(TaskDTO taskDTO) throws TaskManagementException {
        if(taskDTO != null){
            //CZY DO ODDZIELNEJ METODY?
            taskDTO.setStatus(TaskDTO.autoCheckAndUpdateStatus(taskDTO));
            Task task = TaskDTO.createEntity(taskDTO);
            taskRepository.save(task);
        }
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
        UserDTO userDTO = UserDTO.createDTO(user);
        userDTO.setEmail(userEmail);
    }

    @Override
    public void autoUpdateTaskStatus(Integer taskId) throws TaskManagementException {
        Task task = getTaskById(taskId);
        TaskDTO taskDTO = TaskDTO.createDTO(task);
        taskDTO.setStatus(TaskDTO.autoCheckAndUpdateStatus(taskDTO));
        task = TaskDTO.createEntity(taskDTO);
        taskRepository.save(task);
    }

    @Override
    public void manuallyUpdateTaskStatus(Integer taskId, Status status) throws TaskManagementException {
        Task task = getTaskById(taskId);
        TaskDTO taskDTO = TaskDTO.createDTO(task);
        taskDTO.setStatus(status);
        task = TaskDTO.createEntity(taskDTO);
        taskRepository.save(task);
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
    public List<UserDTO> getUsersWithNameOrSurnameOrEmailLike(String snippet) throws TaskManagementException {
        return null;
    }
    @Override
    public List<TaskDTO> getTasksWithTitleLike(String snippet) throws TaskManagementException {
        return null;
    }
    @Override
    public List<TaskDTO> getTasksWithGivenStatus(Status status) throws TaskManagementException {
        return null;
    }
    @Override
    public List<TaskDTO> getTasksWithDateAfterGivenDate(LocalDate date) throws TaskManagementException {
        return null;
    }
    @Override
    public List<TaskDTO> getTasksWithDateBeforeGivenDate(LocalDate date) throws TaskManagementException {
        return null;
    }
    @Override
    public List<TaskDTO> getTasksWithGivenNumberOfAssignedUsers(Integer numberOfUsers) throws TaskManagementException {
        return null;
    }
}
