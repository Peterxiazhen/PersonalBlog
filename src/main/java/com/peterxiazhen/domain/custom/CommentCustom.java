package com.peterxiazhen.domain.custom;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.peterxiazhen.domain.Comment;

import java.util.Date;

public class CommentCustom extends Comment {
    String blogTitle;

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    @Override
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GTM+8")
    public Date getCommentDate() {
        return super.getCommentDate();
    }
}
