package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Category;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private CategoryRepository categoryRepository;

    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = modelMapper;
    }

    @Override
    public PostResponse listPost(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        // Get content for page object
        List<PostDto> items = posts.getContent().stream().map((a) -> mapper.map(a, PostDto.class)).toList();
        PostResponse postResponse = new PostResponse();
        postResponse.setItems(items);
        postResponse.setPageNumber(pageNumber);
        postResponse.setPageSize(pageSize);
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setTotalItems(posts.getTotalElements());
        postResponse.setLastPage(posts.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(long postId) {
        return mapper.map(postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId)), PostDto.class);
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));

        Post post = mapper.map(postDto, Post.class);
        post.setCategory(category);
        Post newPost = postRepository.save(post);
        return mapper.map(newPost, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategoryId(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        List<Post> posts = postRepository.findByCategoryId(id);
        return posts.stream().map(a -> mapper.map(a, PostDto.class)).toList();
    }

    @Override
    public PostDto updatePostById(long postId, PostDto newPost) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Category category = categoryRepository.findById(newPost.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category", "id", newPost.getCategoryId()));

        post.setTitle(newPost.getTitle());
        post.setDescription(newPost.getDescription());
        post.setContent(newPost.getContent());
        post.setCategory(category);
        return mapper.map(postRepository.save(post), PostDto.class);
    }

    @Override
    public void deletePost(long postId) {
        postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        postRepository.deleteById(postId);
    }
}
