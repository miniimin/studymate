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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PageService {

    private final ParticipantService participantService;
    private final GroupService groupService;
    private final RecordService recordService;
    private final CommentService commentService;

    public Map<String, Object> getMain(User user) {
        String query = "";
        int page = 0;
        int size = 2;

        MyStudyPageResponse ongoingResponse = (user != null)
            ? participantService.getMyStudyOngoingList(page, size, user)
            : null;

        SearchStudyPageResponse recruitingResponse = groupService.getRecruitingStudiesNotFull(query, page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("ongoingStudyList", ongoingResponse);
        response.put("recruitingStudyList", recruitingResponse);

        return response;
    }

    public Map<String, Object> getMyStudy(User user) {
        int page = 0;
        int size = 4;
        MyStudyPageResponse ongoingResponse = participantService.getMyStudyOngoingList(page, size, user);
        MyStudyPageResponse completedResponse = participantService.getMyStudyCompletedList(page, size, user);

        Map<String, Object> response = new HashMap<>();
        response.put("ongoingStudyList", ongoingResponse);
        response.put("completeStudyList", completedResponse);

        return response;
    }

    public Map<String, Object> getSearchStudy(String query, int page, int size) {
        SearchStudyPageResponse studyPageData = groupService.getRecruitingStudiesNotFull(query, page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("searchStudyPageData", studyPageData);

        return response;
    }

    public Map<String, Object> getStudyFeed(Long studyId, User user) {
        boolean isParticipant = user != null && participantService.isParticipant(studyId, user);

        int page = 0;
        int size = 5;

        StudyResponse study = groupService.getStudy(studyId);
        List<ParticipantsDto> participants = participantService.getParticipantsWithNickname(studyId);

        Map<String, Object> response = new HashMap<>();
        response.put("isParticipant", isParticipant);
        response.put("participantNum", participants.size());
        response.put("participantsList", participants);
        response.put("studyDetail", study);

        // 분기점
        if(!isParticipant) {
            Page<RecordListResponse> recordPage = recordService.getRecordsList(page, size, studyId);
            response.put("recordList", recordPage.getContent());
            response.put("currentPage", recordPage.getNumber());
            response.put("totalPages", recordPage.getTotalPages());

        } else {
            Page<RecordResponse> recordPage = recordService.getRecords(page, size, studyId);
            List<RecordResponse> records = recordPage.getContent();

            List<Long> recordIds = records.stream().map(RecordResponse::getId).toList();

            List<CommentResponse> comments = commentService.getCommentsOfRecords(recordIds);
            Map<Long, List<CommentResponse>> commentMap = comments
                    .stream()
                    .collect(Collectors.groupingBy(CommentResponse::getRecordId));
            List<RecordsWithCommentsResponse> recordResponse =
                    records.stream()
                            .map(r ->
                                    RecordsWithCommentsResponse.of(r, commentMap.get(r.getId()))
                                    )
                            .toList();

            response.put("recordList", recordResponse);
            response.put("currentPage", recordPage.getNumber());
            response.put("totalPages", recordPage.getTotalPages());
        }
        return response;
    }

    public Map<String, Object> getOnlyRecordsAndComments(Long studyId, int page, int size, User user) {
        boolean isParticipant = user != null && participantService.isParticipant(studyId, user);

        Map<String, Object> response = new HashMap<>();

        // 분기점
        if(!isParticipant) {
            Page<RecordListResponse> recordPage = recordService.getRecordsList(page, size, studyId);
            response.put("recordList", recordPage.getContent());
            response.put("currentPage", recordPage.getNumber());
            response.put("totalPages", recordPage.getTotalPages());

        } else {
            Page<RecordResponse> recordPage = recordService.getRecords(page, size, studyId);
            List<RecordResponse> records = recordPage.getContent();

            List<Long> recordIds = records.stream().map(RecordResponse::getId).toList();

            List<CommentResponse> comments = commentService.getCommentsOfRecords(recordIds);
            Map<Long, List<CommentResponse>> commentMap = comments
                    .stream()
                    .collect(Collectors.groupingBy(CommentResponse::getRecordId));
            List<RecordsWithCommentsResponse> recordResponse =
                    records.stream()
                            .map(r ->
                                    RecordsWithCommentsResponse.of(r, commentMap.get(r.getId()))
                            )
                            .toList();

            response.put("recordList", recordResponse);
            response.put("currentPage", recordPage.getNumber());
            response.put("totalPages", recordPage.getTotalPages());
        }
        return response;
    }

    public Map<String, Object> getOneRecordAndComments(Long studyId, Long recordId, User user) {
        boolean isParticipant = user != null && participantService.isParticipant(studyId, user);
        if(!isParticipant) return null;

        Map<String, Object> response = new HashMap<>();
        RecordResponse record = recordService.getRecord(recordId);
        List<CommentResponse> comments = commentService.getCommentsOfRecord(recordId);

        RecordsWithCommentsResponse recordResponse = RecordsWithCommentsResponse.of(record, comments);

        response.put("recordList", recordResponse);
        return response;
    }

}
