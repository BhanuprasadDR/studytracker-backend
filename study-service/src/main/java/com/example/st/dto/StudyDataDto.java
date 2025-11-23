package com.example.st.dto;


import java.util.List;

public record StudyDataDto(
        List<StudySessionDto> studySessions,
        List<PomodoroSessionDto> pomodoroSessions,
        List<SubjectDto> subjects,
        AppSettingsDto settings
) {}
