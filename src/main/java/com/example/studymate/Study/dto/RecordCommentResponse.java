package com.example.studymate.Study.dto;

import com.example.studymate.Study.entity.StudyComment;
import com.example.studymate.Study.entity.StudyRecord;
import lombok.Getter;

import java.util.List;

@Getter
public class RecordCommentResponse {
    private String recordAuthorName;
    private String recordTitle;
    private String recordContent;

    private List<CommentResponse> commentList;

    public RecordCommentResponse(StudyRecord record,
                                 List<CommentResponse> commentList) {
        this.recordAuthorName = record.getAuthorName();
        this.recordTitle = record.getTitle();
        this.recordContent = record.getContent();
        this.commentList = commentList;
    }
}
