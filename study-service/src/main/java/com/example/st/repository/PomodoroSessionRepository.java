package com.example.st.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.st.model.PomodoroSession;

import java.util.List;

public interface PomodoroSessionRepository extends JpaRepository<PomodoroSession, String> {
    List<PomodoroSession> findByUsername(String username);
}
