package com.example.studymate.Page;

import com.example.studymate.Study.dto.*;
import com.example.studymate.Study.service.CommentService;
import com.example.studymate.Study.service.GroupService;
import com.example.studymate.Study.service.ParticipantService;
import com.example.studymate.Study.service.RecordService;
import com.example.studymate.User.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PageService {

    private final ParticipantService participantService;
    private final GroupService groupService;
    private final RecordService recordService;
    private final CommentService commentService;

    public Map<String, Object> getMain(User user) {
        List<MyStudyListDto> ongoingResponse = (user != null)
            ? participantService.getMyStudyOngoingList(user)
            : null;

        String query = "";
        int page = 0;
        int size = 4;
        List<StudyListResponse> recruitingResponse = groupService.getRecruitingSearchList(query, page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("ongoingStudyList", ongoingResponse);
        response.put("recruitingStudyList", recruitingResponse);

        return response;
    }

    public Map<String, Object> getMyStudy(User user) {
        List<MyStudyListDto> ongoingResponse = participantService.getMyStudyOngoingList(user);
        List<MyStudyListDto> completedResponse = participantService.getMyStudyCompletedList(user);

        Map<String, Object> response = new HashMap<>();
        response.put("ongoingStudyList", ongoingResponse);
        response.put("completeStudyList", completedResponse);

        return response;
    }

//    public Map<String, Object> getNewStudy(User user) {
//        return null;
//    }

    public Map<String, Object> getSearchStudy(String query, int page, int size) {
        SearchStudyPageResponse studyPageData = groupService.getRecruitingStudiesNotFull(query, page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("searchStudyPageData", studyPageData);

        return response;
    }

    public Map<String, Object> getStudyFeed(Long studyId, User user) {

        boolean isParticipant = user != null && participantService.isParticipant(studyId, user);

        StudyResponse study = groupService.getStudy(studyId);
        List<ParticipantResponse> participants = participantService.getParticipants(studyId);
        List<RecordListResponse> recordList = recordService.getRecordsList(studyId);

        Map<String, Object> response = new HashMap<>();
        response.put("isParticipant", isParticipant);
        response.put("participantNum", participants.size());
        response.put("studyDetail", study);
        response.put("recordList", recordList);

        return response;
    }

    public Map<String, Object> getRecordDetail(Long studyId, Long recordId, User user) {

        Map<String, Object> response = new HashMap<>();

        boolean isParticipant = participantService.isParticipant(studyId, user);
        if(!isParticipant) {
            response.put("notMember", null);
            return response;
        }

        RecordResponse record = recordService.getRecord(recordId, user);
        List<CommentResponse> commentsList = commentService.getCommentsList(recordId, user);

        response.put("recordDetail", record);
        response.put("comments", commentsList);

        return response;
    }
}
