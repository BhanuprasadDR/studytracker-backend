package com.example.st.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.st.dto.StudyDataDto;
import com.example.st.service.StudyDataService;

@RestController
@RequestMapping("/api/study")
public class StudyController {

    private final StudyDataService studyDataService;

    public StudyController(StudyDataService studyDataService) {
        this.studyDataService = studyDataService;
    }

    @GetMapping("/data")
    public ResponseEntity<StudyDataDto> getData(Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok(studyDataService.getDataForUser(username));
    }

    @PutMapping("/data")
    public ResponseEntity<Void> saveData(@RequestBody StudyDataDto dto,
                                         Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        studyDataService.saveDataForUser(username, dto);
        return ResponseEntity.ok().build();
    }
}

