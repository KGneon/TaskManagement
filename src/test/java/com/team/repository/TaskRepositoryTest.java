package com.team.repository;

import com.team.mockingdata.EntityExamplesGenerator;
import com.team.model.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
@DataJpaTest
class TaskRepositoryTest {
    private static final Log TestLOGGER = LogFactory.getLog(TaskRepositoryTest.class);
    private final TaskRepository taskRepository;
    @Autowired
    public TaskRepositoryTest(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Test
    void shouldFindByTitleOrDescriptionContainingExampleSnippetOfTaskTitleThenTrue() {
    }
    @Test
    void shouldFindByStatusGivenThenTrue() {
    }
    @Test
    void findByExpectedCompletionDateAfter() {
    }
    @Test
    void findByExpectedCompletionDateBefore() {
    }
    @Test
    void findByUsersSize() {
    }
}