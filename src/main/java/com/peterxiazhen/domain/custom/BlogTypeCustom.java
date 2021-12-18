package com.peterxiazhen.domain.custom;

import com.peterxiazhen.domain.BlogType;

public class BlogTypeCustom extends BlogType {
    private Long blogCount;

    public Long getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Long blogTypeCount) {
        this.blogCount = blogTypeCount;
    }

    @Override
    public String toString() {
        return "BlogTypeCustom{" +
                "blogCount=" + blogCount +
                '}';
    }
}
