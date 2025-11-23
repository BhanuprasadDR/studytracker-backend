package com.example.st.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.st.model.StudySession;

import java.util.List;

public interface StudySessionRepository extends JpaRepository<StudySession, String> {
    List<StudySession> findByUsername(String username);
}

