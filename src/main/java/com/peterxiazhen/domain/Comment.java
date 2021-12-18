package com.peterxiazhen.domain;

import java.util.Date;

public class Comment {
    private Integer id;

    private Integer blogId;

    private String content;

    private Date commentDate;

    private Integer state;

    private String visitorName;

    private String visitorIp;

    private String visitorAvatar;   // 表示头像

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorIp() {
        return visitorIp;
    }

    public void setVisitorIp(String visitorIp) {
        this.visitorIp = visitorIp;
    }

    public String getVisitorAvatar() {
        return visitorAvatar;
    }

    public void setVisitorAvatar(String visitorAvatar) {
        this.visitorAvatar = visitorAvatar;
    }
}