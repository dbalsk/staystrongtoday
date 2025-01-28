package com.example.staystrongtoday.domain;

public class Today_encouragement {
    private Long id;
    private String message;

    public Today_encouragement(){
    }
    public Today_encouragement(String message) {
        this.message = message;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
