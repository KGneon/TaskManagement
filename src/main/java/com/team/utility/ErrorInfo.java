package com.team.utility;

import java.util.concurrent.atomic.AtomicInteger;

public class ErrorInfo {
    protected static AtomicInteger count = new AtomicInteger(0);
    private Integer errorId;
    private String message;
    private Integer errorCode;

    public Integer getErrorId() {
        return errorId;
    }

    public void setErrorId(Integer errorId) {
        this.errorId = errorId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String errorMessage) {
        this.message = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }


}
