package com.team.dto;

import com.team.exception.TaskManagementException;
import com.team.model.Task;
import com.team.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class ListDTOGenerator {
    public static List<UserDTO> getVerifiedUsersDTOList(List<User> listOfUsers, String exceptionMessage) {
        if (listOfUsers != null && !listOfUsers.isEmpty()) {
            return listOfUsers.stream().map(UserDTO::createDTO).collect(Collectors.toList());
        } else {
            throw new TaskManagementException(exceptionMessage);
        }
    }

    public static List<TaskDTO> getVerifiedTasksDTOList(List<Task> listOfTasks, String exceptionMessage) {
        if (listOfTasks != null && !listOfTasks.isEmpty()) {
            return listOfTasks.stream().map(TaskDTO::createDTO).collect(Collectors.toList());
        } else {
            throw new TaskManagementException(exceptionMessage);
        }
    }
}
