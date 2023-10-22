package com.team.exception;

public class TaskManagementException extends RuntimeException {
    static final long serialVersionUID = 1L;

    public TaskManagementException(String message) {
        super(message);
    }
}
