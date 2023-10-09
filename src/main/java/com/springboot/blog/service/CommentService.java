package com.springboot.blog.service;

import com.springboot.blog.payload.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommentService {
    List<CommentDto> getCommentsByPostId(long postId);
    CommentDto getCommentById(long postId, long commentId);
    CommentDto createComment(long postId, CommentDto newComment);
    CommentDto updateComment(long postId, long commentId, CommentDto newComment);
    void deleteComment(long postId, long commentId);
}
