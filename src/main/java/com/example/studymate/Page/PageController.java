package com.example.studymate.Page;

import com.example.studymate.User.entity.User;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PageController {

    private final PageService pageService;

    // 메인 페이지
    @GetMapping("/api/page/main")
    public ResponseEntity<Map<String, Object>> getMainPage(@AuthenticationPrincipal User user) {
        Map<String, Object> pageData = pageService.getMain(user);
        return ResponseEntity.status(HttpStatus.OK).body(pageData);
    }

    // 마이 스터디 페이지
    @GetMapping("/api/page/my-study")
    public ResponseEntity<Map<String, Object>> getMyStudyPage(@AuthenticationPrincipal User user) {
        Map<String, Object> pageData = pageService.getMyStudy(user);
        return ResponseEntity.status(HttpStatus.OK).body(pageData);
    }

    // 스터디 만들기 페이지
//    @GetMapping("/api/page/new-study")
//    public ResponseEntity<Map<String, Object>> getNewStudyPage(@AuthenticationPrincipal User user) {
//        Map<String, Object> pageData = pageService.getNewStudy(user);
//        return ResponseEntity.status(HttpStatus.OK).body(pageData);
//    }

    // 스터디 검색 페이지
    @GetMapping("/api/page/search-study")
    public ResponseEntity<Map<String, Object>> getSearchStudyPage(@RequestParam(defaultValue = "all") String status,
                                                                  @RequestParam(defaultValue = "") String query,
                                                                  @RequestParam(defaultValue = "1") @Positive int page,
                                                                  @RequestParam(defaultValue = "4") @Positive int size) {
        Map<String, Object> pageData = pageService.getSearchStudy(status, query.trim(), page - 1, size);
        return ResponseEntity.status(HttpStatus.OK).body(pageData);
    }

    // 스터디 피드 ( 스터디 설명 + 기록 + 덧글)
    @GetMapping("/api/page/studies/{studyId}")
    public ResponseEntity<Map<String, Object>> getStudyFeedPage(@PathVariable Long studyId,
                                                                @AuthenticationPrincipal User user) {
        Map<String, Object> pageData = pageService.getStudyFeed(studyId, user);
        return ResponseEntity.status(HttpStatus.OK).body(pageData);
    }

    // 스터디 상세에서 페이지별 기록이랑 덧글
    @GetMapping("/api/page/studies/{studyId}/records")
    public ResponseEntity<Map<String, Object>> getOnlyRecordsAndComments(@PathVariable Long studyId,
                                                                         @RequestParam int page,
                                                                         @RequestParam int size,
                                                                @AuthenticationPrincipal User user) {
        Map<String, Object> pageData = pageService.getOnlyRecordsAndComments(studyId, page - 1, size, user);
        return ResponseEntity.status(HttpStatus.OK).body(pageData);
    }

    // 스터디 상세에서 하나의 기록이랑 덧글들
    // 사용 안하는 상태
    @GetMapping("/api/page/studies/{studyId}/records/{recordId}")
    public ResponseEntity<Map<String,Object>> getOneRecordAndComments(@PathVariable Long studyId,
                                                                      @PathVariable Long recordId,
                                                                      @AuthenticationPrincipal User user) {
        Map<String, Object> pageData = pageService.getOneRecordAndComments(studyId, recordId, user);
        return ResponseEntity.ok().body(pageData);
    }

    // 스터디 기록 상세 내용
//    @GetMapping("/api/studies/{studyId}/records/{recordId}/details")
//    public ResponseEntity<Map<String, Object>> getRecordDetail(@PathVariable Long studyId,
//                                                               @PathVariable Long recordId,
//                                                               @AuthenticationPrincipal User user) {
//        Map<String, Object> pageData = pageService.getRecordDetail(studyId, recordId, user);
//        return ResponseEntity.status(HttpStatus.OK).body(pageData);
//    }

}
