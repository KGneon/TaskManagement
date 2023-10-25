package com.team.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class TaskRepositoryTest {

    private final TaskRepository taskRepository;
    @Autowired
    public TaskRepositoryTest(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Test
    void shouldFindByTitleContainingExampleSnippetOfTaskTitleThenTrue() {

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