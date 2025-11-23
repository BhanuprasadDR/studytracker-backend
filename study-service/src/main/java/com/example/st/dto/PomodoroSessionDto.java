package com.example.st.dto;


public record PomodoroSessionDto(
        String id,
        String date,
        String subject,
        String topic,
        int duration
) {}

