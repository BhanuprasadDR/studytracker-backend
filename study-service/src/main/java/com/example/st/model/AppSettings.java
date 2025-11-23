package com.example.st.model;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "app_settings")
public class AppSettings {

    @Id
    private String username;   // one row per user

    private int pomodoroDuration;
    private int shortBreakDuration;
    private int longBreakDuration;
    private double dailyTargetHours;
    private String examDate;
    private double examTargetHours;

    @Lob
    private String revisionSettingsJson; // JSON from frontend RevisionSettings

    private Integer longBreakInterval;

    public AppSettings() {}

    // getters & setters
}
