package com.example.st.dto;


public record StudySessionDto(
        String id,
        String date,
        String subject,
        String topic,
        int duration,
        String type,
        String revisionsJson,
        Boolean isCompleted,
        String completedAt
) {}
