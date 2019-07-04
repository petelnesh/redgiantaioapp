package com.keredgiantaio.techsavanna.redgiantaio.methods;

public class DetailsOneResponse {
    private Integer success;
    private String message;

    public DetailsOneResponse() {
    }

    public DetailsOneResponse(Integer success, String message) {
        this.success = success;
        this.message = message;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
