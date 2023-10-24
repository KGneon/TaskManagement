package com.team.service;

import com.team.dto.TaskDTO;
import com.team.dto.UserDTO;
import com.team.model.Status;
import com.team.model.Task;
import com.team.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagementServiceImplTest {

    @Test
    void shouldGetTheSameValuesFromUserDTOAsFromUser() {
        TaskManagementServiceImpl service = new TaskManagementServiceImpl(null, null);
        User user1 = new User();
        user1.setId(1);
        user1.setName("Kanye");
        user1.setSurname("Westie");
        user1.setEmail("kanye.westie@ggmail.com");

        UserDTO userDTO1 = UserDTO.createDTO(user1);
        Assertions.assertEquals(user1.getId(), userDTO1.getId());
        Assertions.assertEquals(user1.getName(), userDTO1.getName());
        Assertions.assertEquals(user1.getSurname(), userDTO1.getSurname());
        Assertions.assertEquals(user1.getEmail(), userDTO1.getEmail());
    }
    @Test
    void shouldGetTheSameValuesFromTaskDTOAsFromTask() {
        TaskManagementServiceImpl service = new TaskManagementServiceImpl(null, null);
        Task task1 = new Task();
        List<User> userList = new ArrayList<>();

        User user1 = new User();
        user1.setId(1);
        user1.setName("Kanye");
        user1.setSurname("Westie");
        user1.setEmail("kanye.westie@example.com");

        User user2 = new User();
        user2.setId(2);
        user2.setName("Ken");
        user2.setSurname("West");
        user2.setEmail("ken.west@example.com");

        userList.add(user1);
        userList.add(user2);

        task1.setId(1);
        task1.setTitle("Title Example");
        task1.setDescription("Dexcription example. Example description.");
        task1.setStatus(Status.ASSIGNED);
        task1.setExpectedCompletionDate(LocalDate.of(2023, 10, 26));
        task1.setUsers(userList);
        task1.setExpectedUsersNumber(2);


        TaskDTO taskDTO1 = TaskDTO.createDTO(task1);
        Assertions.assertEquals(task1.getId(), taskDTO1.getId());
        Assertions.assertEquals(task1.getTitle(), taskDTO1.getTitle());
        Assertions.assertEquals(task1.getDescription(), taskDTO1.getDescription());
        Assertions.assertEquals(task1.getStatus(), taskDTO1.getStatus());
        Assertions.assertEquals(task1.getExpectedCompletionDate(), taskDTO1.getExpectedCompletionDate());
        Assertions.assertEquals(task1.getUsers(), taskDTO1.getUsers());
        Assertions.assertEquals(task1.getExpectedUsersNumber(), taskDTO1.getExpectedUsersNumber());
    }

    @Test
    void getUsers() {
    }

    @Test
    void getUsersWithNameOrSurnameOrEmailLike() {
    }
}