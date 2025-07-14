package com.example.studymate.Study.controller;

import com.example.studymate.Study.dto.AddCommentRequest;
import com.example.studymate.Study.dto.CommentResponse;
import com.example.studymate.Study.dto.UpdateCommentRequest;
import com.example.studymate.Study.entity.StudyComment;
import com.example.studymate.Study.service.CommentService;
import com.example.studymate.User.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 덧글 작성
    @PostMapping("/api/records/{recordId}/comments")
    public ResponseEntity<CommentResponse> addComment(@PathVariable Long recordId,
                                                      @RequestBody AddCommentRequest request,
                                                      @AuthenticationPrincipal User user) {
        CommentResponse savedComment = commentService.addComment(recordId, request, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }

    // 덧글 전체 조회
    @GetMapping("/api/records/{recordId}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentsList(@PathVariable Long recordId,
                                                                 @AuthenticationPrincipal User user) {
        List<CommentResponse> commentList = commentService.getCommentList(recordId);
        return ResponseEntity.ok().body(commentList);
    }

    // 개별 덧글 조회
    @GetMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentResponse> getComment(@PathVariable Long commentId,
                                                      @AuthenticationPrincipal User user) {
        CommentResponse comment = commentService.getComment(commentId, user);
        return ResponseEntity.ok().body(comment);
    }

    // 개별 덧글 수정
    @PutMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long commentId,
                                                         @RequestBody UpdateCommentRequest request,
                                                         @AuthenticationPrincipal User user) {
        CommentResponse updatedComment = commentService.updateComment(commentId, request, user);
        return ResponseEntity.ok().body(updatedComment);
    }

    // 개별 덧글 삭제
    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId,
                                              @AuthenticationPrincipal User user) {
        commentService.deleteComment(commentId, user);
        return ResponseEntity.ok().build();
    }

}
