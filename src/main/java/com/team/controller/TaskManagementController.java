package com.team.controller;

import com.team.exception.TaskManagementException;
import com.team.model.Task;
import com.team.model.User;
import com.team.service.TaskManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<User>> getAllUsers() throws TaskManagementException {
        List<User> listOfUsers = taskManagementService.getUsers();
        return new ResponseEntity<>(listOfUsers, HttpStatus.OK);
    }
    @GetMapping(value="/tasks")
    public ResponseEntity<List<Task>> getAllTasks() throws TaskManagementException {
        List<Task> listOfTask = taskManagementService.getTasks();
        return new ResponseEntity<>(listOfTask, HttpStatus.OK);
    }
    //POST(ADD)
    @PostMapping(value="/users/add")
    public ResponseEntity<String> addUser(@RequestBody User user) throws TaskManagementException{
        taskManagementService.addUser(user);
        String message = "API.STUDENT_ADDED";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    @PostMapping(value="/tasks/add")
    public ResponseEntity<String> addTask(@RequestBody Task task) throws TaskManagementException{
        taskManagementService.addTask(task);
        String message = "API.STUDENT_ADDED";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    //UPDATE(PUT)
    @PutMapping(value="/users/update")
    public ResponseEntity<String> updateUser(@RequestBody User user) throws TaskManagementException{
        taskManagementService.addUser(user);
        String message = "";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping(value="/tasks/update")
    public ResponseEntity<String> updateTask(@RequestBody Task task) throws TaskManagementException{
        taskManagementService.addTask(task);
        String message = "";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    //DELETE
    @DeleteMapping(value="/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) throws TaskManagementException{
        taskManagementService.deleteUser(userId);
        String message = "API.STUDENT_DELETED";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @DeleteMapping(value="/task/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Integer taskId) throws TaskManagementException{
        taskManagementService.deleteUser(taskId);
        String message = "API.STUDENT_DELETED";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
