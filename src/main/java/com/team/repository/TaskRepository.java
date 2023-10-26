package com.team.repository;

import com.team.model.Status;
import com.team.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByTitleOrDescriptionContaining(String title, String description);
    List<Task> findByStatus(Status status);
    List<Task> findByExpectedCompletionDateAfter(LocalDate givenDate);
    List<Task> findByExpectedCompletionDateBefore(LocalDate givenDate);
    @Query("SELECT t FROM Task t WHERE SIZE(t.users) = :usersNumber")
    List<Task> findByUsersSize(@Param("usersNumber") Integer numberOfUsers);
}
