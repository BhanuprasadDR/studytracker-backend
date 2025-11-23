package com.example.st.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.st.dto.AppSettingsDto;
import com.example.st.dto.PomodoroSessionDto;
import com.example.st.dto.StudyDataDto;
import com.example.st.dto.StudySessionDto;
import com.example.st.dto.SubjectDto;
import com.example.st.model.AppSettings;
import com.example.st.model.PomodoroSession;
import com.example.st.model.StudySession;
import com.example.st.model.Subject;
import com.example.st.repository.AppSettingsRepository;
import com.example.st.repository.PomodoroSessionRepository;
import com.example.st.repository.StudySessionRepository;
import com.example.st.repository.SubjectRepository;

import java.util.List;

@Service
public class StudyDataService {

    private final StudySessionRepository studySessionRepository;
    private final PomodoroSessionRepository pomodoroSessionRepository;
    private final SubjectRepository subjectRepository;
    private final AppSettingsRepository appSettingsRepository;

    public StudyDataService(StudySessionRepository studySessionRepository,
                            PomodoroSessionRepository pomodoroSessionRepository,
                            SubjectRepository subjectRepository,
                            AppSettingsRepository appSettingsRepository) {
        this.studySessionRepository = studySessionRepository;
        this.pomodoroSessionRepository = pomodoroSessionRepository;
        this.subjectRepository = subjectRepository;
        this.appSettingsRepository = appSettingsRepository;
    }

    @Transactional(readOnly = true)
    public StudyDataDto getDataForUser(String username) {
        List<StudySessionDto> studySessions = studySessionRepository.findByUsername(username)
                .stream()
                .map(s -> new StudySessionDto(
                        s.getId(), s.getDate(), s.getSubject(), s.getTopic(),
                        s.getDuration(), s.getType(), s.getRevisionsJson(),
                        s.getIsCompleted(), s.getCompletedAt()
                ))
                .toList();

        List<PomodoroSessionDto> pomodoroSessions = pomodoroSessionRepository.findByUsername(username)
                .stream()
                .map(p -> new PomodoroSessionDto(
                        p.getId(), p.getDate(), p.getSubject(), p.getTopic(), p.getDuration()
                ))
                .toList();

        List<SubjectDto> subjects = subjectRepository.findByUsername(username)
                .stream()
                .map(sub -> new SubjectDto(
                        sub.getId(), sub.getName(), sub.getEstimatedHours(),
                        sub.getActualHours(), sub.isCompleted()
                ))
                .toList();

        AppSettings settings = appSettingsRepository.findById(username).orElse(null);
        AppSettingsDto settingsDto = null;
        if (settings != null) {
            settingsDto = new AppSettingsDto(
                    settings.getPomodoroDuration(),
                    settings.getShortBreakDuration(),
                    settings.getLongBreakDuration(),
                    settings.getDailyTargetHours(),
                    settings.getExamDate(),
                    settings.getExamTargetHours(),
                    settings.getRevisionSettingsJson(),
                    settings.getLongBreakInterval()
            );
        }

        return new StudyDataDto(studySessions, pomodoroSessions, subjects, settingsDto);
    }

    @Transactional
    public void saveDataForUser(String username, StudyDataDto dto) {

        // wipe existing for user (simple approach)
        studySessionRepository.deleteAll(studySessionRepository.findByUsername(username));
        pomodoroSessionRepository.deleteAll(pomodoroSessionRepository.findByUsername(username));
        subjectRepository.deleteAll(subjectRepository.findByUsername(username));

        if (dto.studySessions() != null) {
            for (StudySessionDto s : dto.studySessions()) {
                StudySession entity = new StudySession();
                entity.setId(s.id());
                entity.setUsername(username);
                entity.setDate(s.date());
                entity.setSubject(s.subject());
                entity.setTopic(s.topic());
                entity.setDuration(s.duration());
                entity.setType(s.type());
                entity.setRevisionsJson(s.revisionsJson());
                entity.setIsCompleted(s.isCompleted());
                entity.setCompletedAt(s.completedAt());
                studySessionRepository.save(entity);
            }
        }

        if (dto.pomodoroSessions() != null) {
            for (PomodoroSessionDto p : dto.pomodoroSessions()) {
                PomodoroSession entity = new PomodoroSession();
                entity.setId(p.id());
                entity.setUsername(username);
                entity.setDate(p.date());
                entity.setSubject(p.subject());
                entity.setTopic(p.topic());
                entity.setDuration(p.duration());
                pomodoroSessionRepository.save(entity);
            }
        }

        if (dto.subjects() != null) {
            for (SubjectDto s : dto.subjects()) {
                Subject sub = new Subject();
                sub.setId(s.id());
                sub.setUsername(username);
                sub.setName(s.name());
                sub.setEstimatedHours(s.estimatedHours());
                sub.setActualHours(s.actualHours());
                sub.setCompleted(s.completed());
                subjectRepository.save(sub);
            }
        }

        if (dto.settings() != null) {
            AppSettingsDto s = dto.settings();
            AppSettings settings = appSettingsRepository.findById(username)
                    .orElse(new AppSettings());
            settings.setUsername(username);
            settings.setPomodoroDuration(s.pomodoroDuration());
            settings.setShortBreakDuration(s.shortBreakDuration());
            settings.setLongBreakDuration(s.longBreakDuration());
            settings.setDailyTargetHours(s.dailyTargetHours());
            settings.setExamDate(s.examDate());
            settings.setExamTargetHours(s.examTargetHours());
            settings.setRevisionSettingsJson(s.revisionSettingsJson());
            settings.setLongBreakInterval(s.longBreakInterval());
            appSettingsRepository.save(settings);
        }
    }
}
