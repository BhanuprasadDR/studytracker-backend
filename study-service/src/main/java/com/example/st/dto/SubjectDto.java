package com.example.st.dto;


public record SubjectDto(
        String id,
        String name,
        double estimatedHours,
        double actualHours,
        boolean completed
) {}

