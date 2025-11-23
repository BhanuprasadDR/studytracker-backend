package com.example.st.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.st.model.Subject;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, String> {
    List<Subject> findByUsername(String username);
}
