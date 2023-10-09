package com.springboot.blog.service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostResponse listPost(int pageNumber, int pageSize, String sortBy, String sortDir);
    PostDto getPostById(long postId);
    PostDto updatePostById(long postId, PostDto newPost);
    void deletePost(long postId);
    PostDto createPost(PostDto postDto);

    List<PostDto> getPostsByCategoryId(long id);
}
