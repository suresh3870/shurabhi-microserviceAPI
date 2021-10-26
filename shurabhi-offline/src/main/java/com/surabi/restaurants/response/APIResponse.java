package com.surabi.restaurants.response;




public class APIResponse<T> {

    private String status;
    private String message;
    private T data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public APIResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}