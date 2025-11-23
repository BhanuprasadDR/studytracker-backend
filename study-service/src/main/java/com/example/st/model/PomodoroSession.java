package com.example.st.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "pomodoro_sessions")
public class PomodoroSession {

    @Id
    private String id;

    @Column(nullable = false)
    private String username;

    private String date;
    private String subject;
    private String topic;
    private int duration;   // minutes

    public PomodoroSession() {}

    // getters & setters
}
