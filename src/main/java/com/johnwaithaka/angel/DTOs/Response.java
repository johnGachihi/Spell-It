package com.johnwaithaka.angel.DTOs;

import com.johnwaithaka.angel.entities.Angel;

public class Response {
    boolean error;
    String errorMessage;
    Angel angel;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Angel getAngel() {
        return angel;
    }

    public void setAngel(Angel angel) {
        this.angel = angel;
    }
}
