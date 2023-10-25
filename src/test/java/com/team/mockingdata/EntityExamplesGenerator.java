package com.team.mockingdata;

import com.team.model.Status;
import com.team.model.Task;
import com.team.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EntityExamplesGenerator {
    public static User generateExampleUserWithId(Integer userId) {
        switch (userId) {
            case 1:
                User user1 = new User();
                user1.setId(1);
                user1.setName("Kanye");
                user1.setSurname("Westie");
                user1.setEmail("kanye.westie@ggmail.com");
                return user1;
            case 2:
                User user2 = new User();
                user2.setId(2);
                user2.setName("Ken");
                user2.setSurname("West");
                user2.setEmail("ken.west@example.com");
                return user2;
            case 3:
                User user3 = new User();
                user3.setId(3);
                user3.setName("Sarah");
                user3.setSurname("Sally");
                user3.setEmail("sarah.sally@ggmail.com");
                return user3;
            case 4:
                User user4 = new User();
                user4.setId(4);
                user4.setName("Molly");
                user4.setSurname("May");
                user4.setEmail("molly.may@example.com");
                return user4;
        }
        return null;
    }
    public static Task generateExampleTaskWithIdAndUsersIdsList(Integer taskId, List<Integer> usersIdsList) {
        switch (taskId) {
            case 1:
                Task task1 = new Task();
                task1.setId(1);
                task1.setTitle("Title Example1");
                task1.setDescription("Description example1. Example description.");
                task1.setStatus(Status.ASSIGNED);
                task1.setExpectedCompletionDate(LocalDate.of(2023, 10, 26));
                task1.setUsers(getListOfUsers(usersIdsList));
                task1.setExpectedUsersNumber(2);
                return task1;
            case 2:
                Task task2 = new Task();
                task2.setId(2);
                task2.setTitle("Title Example2");
                task2.setDescription("Description example2. Example description.");
                task2.setStatus(Status.ASSIGNED);
                task2.setExpectedCompletionDate(LocalDate.of(2023, 10, 30));
                task2.setUsers(getListOfUsers(usersIdsList));
                task2.setExpectedUsersNumber(2);
                return task2;
            case 3:
                Task task3 = new Task();
                task3.setId(3);
                task3.setTitle("Title Example3");
                task3.setDescription("Description example3. Example description.");
                task3.setStatus(Status.ASSIGNED);
                task3.setExpectedCompletionDate(LocalDate.of(2023, 10, 10));
                task3.setUsers(getListOfUsers(usersIdsList));
                task3.setExpectedUsersNumber(2);
                return task3;
        }
        return null;
    }
    public static List<User> getListOfUsers(List<Integer> listOfUsersIds) {
//        List<User> userList = new ArrayList<>();
//        listOfUsersIds.forEach(id -> {
//            User user = generateExampleUsersWithId(id);
//            userList.add(user);
//        });
        return listOfUsersIds.stream().map(EntityExamplesGenerator::generateExampleUserWithId).collect(Collectors.toList());
    }
    public static List<Task> getListOfTasksWithoutUsers(List<Integer> listOfTasksIds) {
        return listOfTasksIds.stream().map(id ->
                EntityExamplesGenerator.
                        generateExampleTaskWithIdAndUsersIdsList(id, null)).
                collect(Collectors.toList());
    }
}
