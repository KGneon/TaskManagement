package com.team.controller;

import com.team.dto.TaskDTO;
import com.team.dto.UserDTO;
import com.team.exception.TaskManagementException;
import com.team.model.Status;
import com.team.service.TaskManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(value="/api")
@Validated
public class TaskManagementController {
    @Autowired
    TaskManagementService taskManagementService;
    @Autowired
    Environment environment;

    //GET ALL
    @GetMapping(value="/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() throws TaskManagementException {
        List<UserDTO> listOfUsers = taskManagementService.getUsers();
        return new ResponseEntity<>(listOfUsers, HttpStatus.OK);
    }
    @GetMapping(value="/tasks")
    public ResponseEntity<List<TaskDTO>> getAllTasks() throws TaskManagementException {
        List<TaskDTO> listOfTask = taskManagementService.getTasks();
        return new ResponseEntity<>(listOfTask, HttpStatus.OK);
    }
    //GET WITH FILTERS
    @GetMapping(value="/users/search_by_name_surname_email/{snippet}")
    public ResponseEntity<List<UserDTO>> getUsersBySnippetOfNameOrSurnamOrEmail(@PathVariable String snippet) throws TaskManagementException {
        List<UserDTO> listOfUsers = taskManagementService.getUsersWithNameOrSurnameOrEmailLike(snippet);
        return new ResponseEntity<>(listOfUsers, HttpStatus.OK);
    }
    @GetMapping(value="/tasks/search_for/{snippet}")
    public ResponseEntity<List<TaskDTO>> getTasksByTitleNameSnippet(@PathVariable String snippet) throws TaskManagementException {
        List<TaskDTO> listOfTask = taskManagementService.getTasksWithTitleLike(snippet);
        return new ResponseEntity<>(listOfTask, HttpStatus.OK);
    }
    @GetMapping(value="/tasks/with_status/{status}")
    public ResponseEntity<List<TaskDTO>> getTasksByStatus(@PathVariable Status status) throws TaskManagementException {
        List<TaskDTO> listOfTask = taskManagementService.getTasksWithGivenStatus(status);
        return new ResponseEntity<>(listOfTask, HttpStatus.OK);
    }
    @GetMapping(value="/tasks/after_date/{date}")
    public ResponseEntity<List<TaskDTO>> getTasksByExpectedCompletionDateAfter(@PathVariable LocalDate date) throws TaskManagementException {
        List<TaskDTO> listOfTask = taskManagementService.getTasksWithDateAfterGivenDate(date);
        return new ResponseEntity<>(listOfTask, HttpStatus.OK);
    }
    @GetMapping(value="/tasks/before_date/{date}")
    public ResponseEntity<List<TaskDTO>> getTasksByExpectedCompletionDateBefore(@PathVariable LocalDate date) throws TaskManagementException {
        List<TaskDTO> listOfTask = taskManagementService.getTasksWithDateBeforeGivenDate(date);
        return new ResponseEntity<>(listOfTask, HttpStatus.OK);
    }
    @GetMapping(value="/tasks/assigned_users/{number}")
    public ResponseEntity<List<TaskDTO>> getTasksByNumberOfUsersAssigned(@PathVariable("number") Integer numberOfUsers) throws TaskManagementException {
        List<TaskDTO> listOfTask = taskManagementService.getTasksWithGivenNumberOfAssignedUsers(numberOfUsers);
        return new ResponseEntity<>(listOfTask, HttpStatus.OK);
    }
    //POST(ADD)
    @PostMapping(value="/users/add")
    public ResponseEntity<String> addUser(@RequestBody @Valid UserDTO userDTO) throws TaskManagementException{
        taskManagementService.addUser(userDTO);
        String message = "API.USER_ADDED";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    @PostMapping(value="/tasks/add")
    public ResponseEntity<String> addTask(@RequestBody @Valid TaskDTO taskDTO) throws TaskManagementException{
        taskManagementService.addTask(taskDTO);
        String message = "API.TASK_ADDED";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    //DELETE
    @DeleteMapping(value="/users/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) throws TaskManagementException{
        taskManagementService.deleteUser(userId);
        String message = "API.USER_DELETED";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @DeleteMapping(value="/tasks/delete/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Integer taskId) throws TaskManagementException{
        taskManagementService.deleteTask(taskId);
        String message = "API.TASK_DELETED";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    //UPDATE(PUT)
    @PutMapping(value="/users/update/name/{userId}/{name}")
    public ResponseEntity<String> updateUserName(@PathVariable Integer userId, @PathVariable String name) throws TaskManagementException{
        taskManagementService.updateUserName(userId, name);
        String message = "API.USER_NAME_UPDATED";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping(value="/users/update/surname/{userId}/{surname}")
    public ResponseEntity<String> updateUserSurname(@PathVariable Integer userId, @PathVariable String surname) throws TaskManagementException{
        taskManagementService.updateUserSurname(userId, surname);
        String message = "API.USER_SURNAME_UPDATED";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping(value="/users/update/email/{userId}/{email}")
    public ResponseEntity<String> updateUserEmail(@PathVariable Integer userId, @PathVariable String email) throws TaskManagementException{
        taskManagementService.updateUserEmail(userId, email);
        String message = "API.USER_EMAIL_UPDATED";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping(value="/tasks/update/description/{taskId}/{description}")
    public ResponseEntity<String> updateTaskDescription(@PathVariable Integer taskId, @PathVariable String description) throws TaskManagementException{
        taskManagementService.updateTaskDescription(taskId, description);
        String message = "API.TASK_DESCRIPTION_UPDATED";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping(value="/tasks/update/status/auto/{taskId}")
    public ResponseEntity<String> updateTaskStatusAutomatically(@PathVariable Integer taskId) throws TaskManagementException{
        taskManagementService.autoUpdateTaskStatus(taskId);
        String message = "API.TASK_STATUS_AUTO_UPDATE";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping(value="/tasks/update/status/{taskId}/{status}")
    public ResponseEntity<String> updateTaskStatusManually(@PathVariable Integer taskId, @PathVariable Status status) throws TaskManagementException{
        taskManagementService.manuallyUpdateTaskStatus(taskId, status);
        String message = "API.TASK_STATUS_MANUAL_UPDATE";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping(value="/tasks/update/completion_date/{taskId}/{date}")
    public ResponseEntity<String> updateTaskExpectedCompletionDate(@PathVariable Integer taskId, @PathVariable("date") LocalDate expectedCompletionDate) throws TaskManagementException{
        taskManagementService.updateTaskExpectedCompletionDate(taskId, expectedCompletionDate);
        String message = "API.TASK_EXPECTED_COMPLETION_UPDATED";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping(value="/tasks/update/description/{taskId}/{number}")
    public ResponseEntity<String> updateTaskExpectedUsersNumber(@PathVariable Integer taskId, @PathVariable("number") Integer expectedNumberOfUsers) throws TaskManagementException{
        taskManagementService.updateTaskExpectedUsersNumber(taskId, expectedNumberOfUsers);
        String message = "API.TASK_EXPECTED_USERS_UPDATED";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping(value="/tasks/update/assignment/{taskId}")
    public ResponseEntity<String> updateTaskUsersList(@PathVariable Integer taskId, @RequestBody List<Integer> listOfUsersIds) throws TaskManagementException{
        taskManagementService.assignUsersToTask(taskId, listOfUsersIds);
        String message = "API.TASK_USERS_UPDATE";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
