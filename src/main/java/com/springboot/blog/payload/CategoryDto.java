package com.springboot.blog.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CategoryDto {
    private long id;
    @NotEmpty
    @Size(min = 2, message = "Category name should have at least 2 characters.")
    private String name;

    @NotEmpty
    @Size(min = 2, message = "Category description should have at least 10 characters.")
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
