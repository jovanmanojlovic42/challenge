package com.company.model;

import java.time.LocalDateTime;

public class Activity {
    private Long id;
    private ActivityType type;
    private ActivityStatus status;
    private LocalDateTime start;
    private LocalDateTime stop;
    private String username;

    public Activity(Long id, ActivityType type, ActivityStatus status, LocalDateTime start, LocalDateTime stop, String username) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.start = start;
        this.stop = stop;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public ActivityStatus getStatus() {
        return status;
    }

    public void setStatus(ActivityStatus status) {
        this.status = status;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getStop() {
        return stop;
    }

    public void setStop(LocalDateTime stop) {
        this.stop = stop;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", type=" + type +
                ", status=" + status +
                ", start=" + start +
                ", stop=" + stop +
                ", username='" + username + '\'' +
                '}';
    }
}


