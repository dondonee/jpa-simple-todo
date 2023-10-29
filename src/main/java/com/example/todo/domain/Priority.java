package com.example.todo.domain;

public enum Priority {
    HIGH("높음"), MEDIUM("보통"), LOW("낮음");

    private final String description;

    Priority(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
