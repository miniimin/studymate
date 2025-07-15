package com.example.studymate.Study.service;

import com.example.studymate.Study.dto.AddCommentRequest;
import com.example.studymate.Study.dto.CommentResponse;
import com.example.studymate.Study.dto.UpdateCommentRequest;
import com.example.studymate.Study.entity.StudyComment;
import com.example.studymate.Study.repository.CommentRepository;
import com.example.studymate.User.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    public CommentResponse addComment(Long recordId,
                                      AddCommentRequest request,
                                      User user) {
        StudyComment studyComment = StudyComment.builder()
                .recordId(recordId)
                .authorId(user.getId())
                .authorName(user.getNickname())
                .content(request.getContent())
                .build();
        StudyComment savedComment = commentRepository.save(studyComment);
        return CommentResponse.from(savedComment);
    }

    public List<CommentResponse> getCommentsOfRecord(Long recordId) {
        return commentRepository.findAllByRecordId(recordId);
    }

    public List<CommentResponse> getCommentsOfRecords(List<Long> recordIds) {
        return commentRepository.findAllByRecordIdIn(recordIds);
    }

    public CommentResponse getComment(Long commentId,
                                      User user) {
        StudyComment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 덧글이 없습니다."));

        return CommentResponse.from(comment);
    }

    public CommentResponse updateComment(Long commentId,
                                         UpdateCommentRequest request,
                                         User user) {
        StudyComment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 덧글이 없습니다."));

        comment.update(request.getContent());
        return CommentResponse.from(comment);
    }

    public void deleteComment(Long commentId,
                              User user) {
        StudyComment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 덧글이 없습니다."));

        commentRepository.deleteById(commentId);
    }
}
