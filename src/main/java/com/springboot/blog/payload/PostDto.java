package com.springboot.blog.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private long id;
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters.")
    private String title;
    @NotEmpty
    @Size(min = 2, message = "Post description should have at least 10 characters.")
    private String description;
    @NotEmpty
    @Size(min = 2, message = "Post content should have at least 10 characters.")
    private String content;
    private Set<CommentDto> comments;

    public long getCategoryId() {
        return categoryId;
    }

    private long categoryId;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<CommentDto> getComments() {
        return comments;
    }

}
