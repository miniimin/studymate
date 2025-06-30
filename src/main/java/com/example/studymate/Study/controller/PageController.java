package com.example.studymate.Study.controller;

import com.example.studymate.Study.dto.MyStudyResponse;
import com.example.studymate.Study.dto.StudySummary;
import com.example.studymate.Study.service.GroupService;
import com.example.studymate.Study.service.ParticipantService;
import com.example.studymate.User.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PageController {

    private final ParticipantService participantService;
    private final GroupService groupService;

    @GetMapping("/api/main")
    public ResponseEntity<Map<String, Object>> getMainPage(@AuthenticationPrincipal User user){
        System.out.println("main api 실행 " + user);

        MyStudyResponse myStudyResponse = null;
        if (user != null) {
            myStudyResponse = participantService.getMyStudyOngoingList(user);
        }
        List<StudySummary> recruitingResponse = groupService.getRecruitingStudyList();

        Map<String, Object> response = new HashMap<>();
        response.put("myStudyOngoing", myStudyResponse);
        response.put("StudyRecruiting", recruitingResponse);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
