package com.example.st.model;



import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "study_sessions")
public class StudySession {

    @Id
    private String id;

    @Column(nullable = false)
    private String username;

    private String date;       // ISO string from frontend
    private String subject;
    private String topic;
    private int duration;      // minutes
    private String type;       // "study" or "pomodoro"

    @Lob
    private String revisionsJson;  // store revisions map as JSON string

    private Boolean isCompleted;
    private String completedAt;

    public StudySession() {}

    
}

