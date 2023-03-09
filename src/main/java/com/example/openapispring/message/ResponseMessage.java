package com.example.openapispring.message;

public class ResponseMessage {
    private String status;
    private String errorMessage;

    public ResponseMessage(String status) {
        this.status = status;
    }

    public ResponseMessage(String status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
