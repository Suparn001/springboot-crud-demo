package com.gautam.crudSpringBootDemo.dto;

import java.time.Instant;

public class ExceptionResponseDTO {
    private String error;
    private Instant timestamp;
    private String message;
    private int statusCode;
    private String path;

    public ExceptionResponseDTO(String error, Instant timestamp, String message, int statusCode, String path) {
        this.error = error;
        this.timestamp = timestamp;
        this.message = message;
        this.statusCode = statusCode;
        this.path = path;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
