package com.gs.common;

import org.springframework.stereotype.Controller;

public class ControllerResult {

    private String result;
    private String message;

    public ControllerResult() {}

    public ControllerResult(String result, String message) {
        this.result = result;
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
