package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.mapper = modelMapper;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        return commentRepository.findByPostId(postId).stream().map((a) -> mapper.map(a, CommentDto.class)).toList();
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));
        return mapper.map(commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "ID", commentId)), CommentDto.class);
    }

    @Override
    public CommentDto createComment(long postId, CommentDto newComment) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = mapper.map(newComment, Comment.class);
        comment.setPost(post);
        return mapper.map(commentRepository.save(comment), CommentDto.class);
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto newComment) {
        postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "ID", commentId));
        comment.setEmail(newComment.getEmail());
        comment.setName(newComment.getName());
        comment.setBody(newComment.getBody());
        return mapper.map(commentRepository.save(comment), CommentDto.class);
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));
        commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "ID", commentId));
        commentRepository.deleteById(commentId);
    }
}
