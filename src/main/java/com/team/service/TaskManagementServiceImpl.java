package com.team.service;

import com.team.dto.ListDTOGenerator;
import com.team.dto.TaskDTO;
import com.team.dto.UserDTO;
import com.team.exception.TaskManagementException;
import com.team.model.Status;
import com.team.model.Task;
import com.team.model.User;
import com.team.repository.TaskRepository;
import com.team.repository.UserRepository;
import com.team.validation.TaskAndUserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskManagementServiceImpl implements TaskManagementService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskManagementServiceImpl(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }
    //GET
    @Override
    public List<UserDTO> getUsers() throws TaskManagementException {
        List<User> listOfUsers = userRepository.findAll();
        return ListDTOGenerator.getVerifiedUsersDTOList(listOfUsers, "Service.NO_USERS");
    }
    @Override
    public List<TaskDTO> getTasks() throws TaskManagementException {
        List<Task> listOfTasks = taskRepository.findAll();
        return ListDTOGenerator.getVerifiedTasksDTOList(listOfTasks, "Service.NO_TASKS");
    }
    @Override
    public List<UserDTO> getUsersWithNameOrSurnameOrEmailLike(String snippet) {
        //if(snippet == null)
        List<User> filteredUsers = userRepository.findByNameContainingOrSurnameContainingOrEmailContaining(snippet, snippet, snippet);
        return ListDTOGenerator.getVerifiedUsersDTOList(filteredUsers, "Service.NO_USERS_BY_TEXT");
    }
    @Override
    public List<TaskDTO> getTasksWithTitleOrDescriptionLike(String snippet) {
        List<Task> filteredTasks = taskRepository.findByTitleOrDescriptionContaining(snippet, snippet);
        return ListDTOGenerator.getVerifiedTasksDTOList(filteredTasks, "Service.NO_FILTERED_BY_TITLE");
    }
    @Override
    public List<TaskDTO> getTasksWithGivenStatus(Status status) {
        if(status == null) status = Status.NONE;
        List<Task> filteredTasks = taskRepository.findByStatus(status);
        return ListDTOGenerator.getVerifiedTasksDTOList(filteredTasks, "Service.NO_TASKS_BY_STATUS");
    }
    @Override
    public List<TaskDTO> getTasksWithDateAfterGivenDate(LocalDate date) {
        List<Task> filteredTasks = taskRepository.findByExpectedCompletionDateAfter(date);
        return ListDTOGenerator.getVerifiedTasksDTOList(filteredTasks, "Service.NO_TASKS_AFTER_DATE");
    }
    @Override
    public List<TaskDTO> getTasksWithDateBeforeGivenDate(LocalDate date) {
        List<Task> filteredTasks = taskRepository.findByExpectedCompletionDateBefore(date);
        return ListDTOGenerator.getVerifiedTasksDTOList(filteredTasks, "Service.NO_TASKS_BEFORE_DATE");
    }
    @Override
    public List<TaskDTO> getTasksWithGivenNumberOfAssignedUsers(Integer numberOfUsers) {
        List<Task> filteredTasks = taskRepository.findByUsersSize(numberOfUsers);
        return ListDTOGenerator.getVerifiedTasksDTOList(filteredTasks, "Service.NO_TASKS_NUMBER_ASSIGNMENTS");
    }
    //GET ONE
    @Override
    public User getUserById(Integer userId) {
        TaskAndUserValidation.validateId(userId);
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(() -> new TaskManagementException("Service.NO_SUCH_USER_ID"));
        return user;
    }
    @Override
    public Task getTaskById(Integer taskId) {
        TaskAndUserValidation.validateId(taskId);
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        return taskOptional.orElseThrow(() -> new TaskManagementException("Service.NO_SUCH_TASK_ID"));
    }
    //ADD
    @Override
    public void addUser(UserDTO userDTO) {
        if (userDTO != null) throw new TaskManagementException("Service.USER_IS_NULL");
        User user = UserDTO.createEntity(userDTO);
        userRepository.save(user);
    }
    @Override
    public void addTask(TaskDTO taskDTO) {
        if (taskDTO == null) throw new TaskManagementException("Service.TASK_IS_NULL");
        Task task = TaskDTO.createEntity(taskDTO);
        taskRepository.save(task);
    }
    //DELETE
    @Override
    public void deleteUser(Integer id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
    @Override
    public void deleteTask(Integer id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }
    //UPDATE
    @Override
    public void updateUserName(Integer userId, String userName) {
        User user = getUserById(userId);
        UserDTO userDTO = UserDTO.createDTO(user);
        userDTO.setEmail(userName);
        user = UserDTO.createEntity(userDTO);
        userRepository.save(user);
    }
    @Override
    public void updateUserSurname(Integer userId, String userSurname) {
        User user = getUserById(userId);
        UserDTO userDTO = UserDTO.createDTO(user);
        userDTO.setEmail(userSurname);
        user = UserDTO.createEntity(userDTO);
        userRepository.save(user);
    }
    @Override
    public void updateUserEmail(Integer userId, String userEmail) {
        User user = getUserById(userId);
        UserDTO userDTO = UserDTO.createDTO(user);
        userDTO.setEmail(userEmail);
        user = UserDTO.createEntity(userDTO);
        userRepository.save(user);
    }
    @Override
    public void updateTaskDescription(Integer taskId, String description) {
        Task task = getTaskById(taskId);
        TaskDTO taskDTO = TaskDTO.createDTO(task);
        taskDTO.setDescription(description);
        task = TaskDTO.createEntity(taskDTO);
        taskRepository.save(task);
    }
    @Override
    public void autoUpdateTaskStatus(Integer taskId) {
        Task task = getTaskById(taskId);
        TaskDTO taskDTO = TaskDTO.createDTO(task);
        taskDTO.setStatus(TaskDTO.autoCheckAndUpdateStatus(taskDTO));
        task = TaskDTO.createEntity(taskDTO);
        taskRepository.save(task);
    }
    @Override
    public void manuallyUpdateTaskStatus(Integer taskId, Status status) {
        Task task = getTaskById(taskId);
        TaskDTO taskDTO = TaskDTO.createDTO(task);
        taskDTO.setStatus(status);
        task = TaskDTO.createEntity(taskDTO);
        taskRepository.save(task);
    }
    @Override
    public void updateTaskExpectedCompletionDate(Integer taskId, LocalDate date) {
        Task task = getTaskById(taskId);
        TaskDTO taskDTO = TaskDTO.createDTO(task);
        taskDTO.setExpectedCompletionDate(date);
        task = TaskDTO.createEntity(taskDTO);
        taskRepository.save(task);
    }
    @Override
    public void updateTaskExpectedUsersNumber(Integer taskId, Integer number) {
        Task task = getTaskById(taskId);
        TaskDTO taskDTO = TaskDTO.createDTO(task);
        taskDTO.setExpectedUsersNumber(number);
        task = TaskDTO.createEntity(taskDTO);
        taskRepository.save(task);
    }
    @Override
    public void assignUsersToTask(Integer taskId, List<Integer> listOfUsersIdsDTO) {
        TaskAndUserValidation.validateListOfIds(listOfUsersIdsDTO);
        Task task = getTaskById(taskId);
        List<User> listOfUsers = task.getUsers();
        listOfUsersIdsDTO.forEach(userId -> {
            User user = getUserById(userId);
            if (!listOfUsers.contains(user)) {
                listOfUsers.add(user);
            }
        });
        task.setUsers(listOfUsers);

    }

    @Override
    public void unassignUsersFromTask(Integer taskId, List<Integer> listOfUsersIdsDTO) {
        TaskAndUserValidation.validateListOfIds(listOfUsersIdsDTO);
        Task task = getTaskById(taskId);
        List<User> listOfUsers = task.getUsers();
        listOfUsersIdsDTO.forEach(userId -> {
            User user = getUserById(userId);
            if (!listOfUsers.contains(user)) throw new TaskManagementException("Service.NOT_EXISTING_USER_ASSIGNED");
        });
        listOfUsers.removeAll(listOfUsersIdsDTO);
        task.setUsers(listOfUsers);
    }
}
