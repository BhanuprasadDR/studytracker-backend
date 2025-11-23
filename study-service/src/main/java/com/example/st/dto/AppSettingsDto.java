package com.example.st.dto;

public record AppSettingsDto(
        int pomodoroDuration,
        int shortBreakDuration,
        int longBreakDuration,
        double dailyTargetHours,
        String examDate,
        double examTargetHours,
        String revisionSettingsJson,
        Integer longBreakInterval
) {}
