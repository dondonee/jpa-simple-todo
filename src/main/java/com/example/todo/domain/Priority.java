package com.example.todo.domain;

public enum Priority {
    HIGH("label.priority.HIGH"), MEDIUM("label.priority.MEDIUM"), LOW("label.priority.LOW");

    private final String description;

    Priority(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
