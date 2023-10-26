package com.team.service;

import com.team.dto.TaskDTO;
import com.team.dto.UserDTO;
import com.team.mockingdata.EntityExamplesGenerator;
import com.team.model.Task;
import com.team.model.User;
import com.team.repository.UserRepository;
import com.team.utility.ExceptionAdviceController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskManagementServiceImplTest {

    private static final Log TestLOGGER = LogFactory.getLog(TaskManagementServiceImplTest.class);

    @InjectMocks
    private TaskManagementServiceImpl service;

    @Mock
    private UserRepository userRepository;

    @Test
    void testShouldGetTheSameValuesFromUserDTOAsFromUser() {
        User user1 = EntityExamplesGenerator.generateExampleUserWithId(1);
        UserDTO userDTO1 = UserDTO.createDTO(user1);

        TestLOGGER.info(user1.getName() + "," + userDTO1.getName());

        Assertions.assertEquals(user1.getId(), userDTO1.getId());
        Assertions.assertEquals(user1.getName(), userDTO1.getName());
        Assertions.assertEquals(user1.getSurname(), userDTO1.getSurname());
        Assertions.assertEquals(user1.getEmail(), userDTO1.getEmail());
    }

    @Test
    void testShouldGetTheSameValuesFromTaskDTOAsFromTaskThenTrue() {
        List<Integer> userIdsList = Arrays.asList(1, 2);
        Task task1 = EntityExamplesGenerator.
                generateExampleTaskWithIdAndUsersIdsList(1, userIdsList);
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
    @DisplayName("For service method getUsers")
    void testShouldGetTheSameListOfUsersAsGivenAndCheckIfIsEmptyOrIfSizeIsTheSame() {
        List<User> userList = EntityExamplesGenerator.getListOfUsers(Arrays.asList(1, 2));

        TestLOGGER.info(userList.get(0).toString()
                + "," + userList.get(1).toString());

        when(userRepository.findAll()).thenReturn(userList);
        List<UserDTO> userDTOList = service.getUsers();

        Assertions.assertNotNull(userDTOList);
        assertThat(userDTOList).isNotEmpty();
        assertThat(userDTOList.size()).isEqualTo(userList.size());
    }

    @Test
    @DisplayName("For service method getUsersWithNameOrSurnameOrEmailLike")
    void testShouldGetTheSameListOfUsersByCredentialsAsGivenAndCheckIfIsEmptyOrIfSizeIsTheSame() {
        List<User> userList1 = EntityExamplesGenerator.getListOfUsers(Arrays.asList(3, 4));
        String snippet = "ll";
        when(userRepository.findByNameContainingOrSurnameContainingOrEmailContaining(snippet, snippet, snippet)).thenReturn(userList1);
        List<UserDTO> userDTOList = service.getUsersWithNameOrSurnameOrEmailLike(snippet);

        Assertions.assertNotNull(userDTOList);
        assertThat(userDTOList).isNotEmpty();
        assertThat(userDTOList.size()).isEqualTo(2);
    }
}