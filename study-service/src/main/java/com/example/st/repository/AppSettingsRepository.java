package com.example.st.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.st.model.AppSettings;

public interface AppSettingsRepository extends JpaRepository<AppSettings, String> {
    // username is ID
}
