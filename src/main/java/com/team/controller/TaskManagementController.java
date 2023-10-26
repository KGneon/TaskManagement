package com.team.controller;

import com.team.dto.TaskDTO;
import com.team.dto.UserDTO;
import com.team.model.Status;
import com.team.service.TaskManagementService;
import com.team.validation.EnumNamePattern;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

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
    @GetMapping("/users/search")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> listOfUsers = taskManagementService.getUsers();
        return new ResponseEntity<>(listOfUsers, HttpStatus.OK);
    }
    @GetMapping("/tasks/search")
    public ResponseEntity<List<TaskDTO>> getAllTasks(){
        List<TaskDTO> listOfTask = taskManagementService.getTasks();
        return new ResponseEntity<>(listOfTask, HttpStatus.OK);
    }
    //GET WITH FILTERS
    @GetMapping("/users/search/{snippet}")
    public ResponseEntity<List<UserDTO>> getUsersBySnippetOfNameOrSurnamOrEmail(
            @PathVariable String snippet){
        List<UserDTO> listOfUsers = taskManagementService.getUsersWithNameOrSurnameOrEmailLike(snippet);
        return new ResponseEntity<>(listOfUsers, HttpStatus.OK);
    }
    @GetMapping("/tasks/search/{snippet}")
    public ResponseEntity<List<TaskDTO>> getTasksByTitleOrDescriptionSnippet(
            @PathVariable String snippet){
        List<TaskDTO> listOfTask = taskManagementService.getTasksWithTitleOrDescriptionLike(snippet);
        return new ResponseEntity<>(listOfTask, HttpStatus.OK);
    }
    @GetMapping("/tasks/search/status/{status}")
    public ResponseEntity<List<TaskDTO>> getTasksByStatus(
            @PathVariable("status")
            @EnumNamePattern(regexp = "^(FINISHED|IN_PROGRESS|LATE_IN_PROGRESS|ASSIGNED|UNASSIGNED|CANCELLED|NONE)$", message = "{task.status.invalid}")
            Status status){
        List<TaskDTO> listOfTask = taskManagementService.getTasksWithGivenStatus(status);
        return new ResponseEntity<>(listOfTask, HttpStatus.OK);
    }
    @GetMapping("/tasks/search/after_date/{date}")
    public ResponseEntity<List<TaskDTO>> getTasksByExpectedCompletionDateAfter(
            @PathVariable
            @NotNull(message = "{task.expectedcompletiondate.notpresent}")
            @Future(message = "{task.expectedcompletiondate.invalid}")
            LocalDate date){
        List<TaskDTO> listOfTask = taskManagementService.getTasksWithDateAfterGivenDate(date);
        return new ResponseEntity<>(listOfTask, HttpStatus.OK);
    }
    @GetMapping("/tasks/search/before_date/{date}")
    public ResponseEntity<List<TaskDTO>> getTasksByExpectedCompletionDateBefore(
            @PathVariable
            @NotNull(message = "{task.expectedcompletiondate.notpresent}")
            @Future(message = "{task.expectedcompletiondate.invalid}")
            LocalDate date){
        List<TaskDTO> listOfTask = taskManagementService.getTasksWithDateBeforeGivenDate(date);
        return new ResponseEntity<>(listOfTask, HttpStatus.OK);
    }
    @GetMapping("/tasks/search/assigned_users/{number}")
    public ResponseEntity<List<TaskDTO>> getTasksByNumberOfUsersAssigned(
            @PathVariable("number")
            @NotNull(message = "{task.expectedusersnumber.notpresent}")
            @Positive(message = "{task.expectedusersnumber.invalid}")
            Integer numberOfUsers){
        List<TaskDTO> listOfTask = taskManagementService.getTasksWithGivenNumberOfAssignedUsers(numberOfUsers);
        return new ResponseEntity<>(listOfTask, HttpStatus.OK);
    }
    //POST(ADD)
    @PostMapping("/users/add")
    public ResponseEntity<String> addUser(@RequestBody @Valid UserDTO userDTO){
        taskManagementService.addUser(userDTO);
        String message = "RestController.USER_ADDED";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    @PostMapping("/tasks/add")
    public ResponseEntity<String> addTask(@RequestBody @Valid TaskDTO taskDTO){
        taskManagementService.addTask(taskDTO);
        String message = "RestController.TASK_ADDED";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    //DELETE
    @DeleteMapping("/users/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId){
        taskManagementService.deleteUser(userId);
        String message = "RestController.USER_DELETED";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @DeleteMapping("/tasks/delete/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Integer taskId){
        taskManagementService.deleteTask(taskId);
        String message = "RestController.TASK_DELETED";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    //UPDATE(PUT)
    @PutMapping("/users/update/name/{userId}/{name}")
    public ResponseEntity<String> updateUserName(
            @PathVariable Integer userId,
            @PathVariable String name){
        taskManagementService.updateUserName(userId, name);
        String message = "RestController.USER_NAME_UPDATED";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping("/users/update/surname/{userId}/{surname}")
    public ResponseEntity<String> updateUserSurname(
            @PathVariable Integer userId,
            @PathVariable String surname){
        taskManagementService.updateUserSurname(userId, surname);
        String message = "RestController.USER_SURNAME_UPDATED";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping("/users/update/email/{userId}/{email}")
    public ResponseEntity<String> updateUserEmail(
            @PathVariable Integer userId,
            @PathVariable String email){
        taskManagementService.updateUserEmail(userId, email);
        String message = "RestController.USER_EMAIL_UPDATED";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping("/tasks/update/description/{taskId}/{description}")
    public ResponseEntity<String> updateTaskDescription(
            @PathVariable Integer taskId,
            @PathVariable String description){
        taskManagementService.updateTaskDescription(taskId, description);
        String message = "RestController.TASK_DESCRIPTION_UPDATED";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping("/tasks/update/status/auto/{taskId}")
    public ResponseEntity<String> updateTaskStatusAutomatically(
            @PathVariable Integer taskId){
        taskManagementService.autoUpdateTaskStatus(taskId);
        String message = "RestController.TASK_STATUS_AUTO_UPDATE";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping("/tasks/update/status/{taskId}/{status}")
    public ResponseEntity<String> updateTaskStatusManually(
            @PathVariable Integer taskId,
            @PathVariable Status status){
        taskManagementService.manuallyUpdateTaskStatus(taskId, status);
        String message = "RestController.TASK_STATUS_MANUAL_UPDATE";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping("/tasks/update/completion_date/{taskId}/{date}")
    public ResponseEntity<String> updateTaskExpectedCompletionDate(
            @PathVariable Integer taskId,
            @PathVariable("date") LocalDate expectedCompletionDate){
        taskManagementService.updateTaskExpectedCompletionDate(taskId, expectedCompletionDate);
        String message = "RestController.TASK_EXPECTED_COMPLETION_UPDATED";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping("/tasks/update/description/{taskId}/{number}")
    public ResponseEntity<String> updateTaskExpectedUsersNumber(
            @PathVariable Integer taskId,
            @PathVariable("number") Integer expectedNumberOfUsers){
        taskManagementService.updateTaskExpectedUsersNumber(taskId, expectedNumberOfUsers);
        String message = "RestController.TASK_EXPECTED_USERS_UPDATED";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping("/tasks/update/assign/{taskId}")
    public ResponseEntity<String> updateTaskAssigningUsers(
            @PathVariable Integer taskId,
            @RequestBody List<Integer> listOfUsersIds){
        taskManagementService.assignUsersToTask(taskId, listOfUsersIds);
        String message = "RestController.TASK_USERS_ASSIGN_UPDATE";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping("/tasks/update/unassign/{taskId}")
    public ResponseEntity<String> updateTaskUnassigningUsers(
            @PathVariable Integer taskId,
            @RequestBody List<Integer> listOfUsersIds){
        taskManagementService.unassignUsersFromTask(taskId, listOfUsersIds);
        String message = "RestController.TASK_USERS_UNASSIGN_UPDATE";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
