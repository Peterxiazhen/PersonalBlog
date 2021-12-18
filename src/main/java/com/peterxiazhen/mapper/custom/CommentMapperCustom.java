package com.peterxiazhen.mapper.custom;

import com.peterxiazhen.domain.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapperCustom {
    List<Comment> getCommentListByPageAndBlogId(@Param("start") Integer start,
                                                @Param("rows") Integer rows,
                                                @Param("blogId") Integer blogId);

    List<Comment> getCommentListByPage(@Param("start") Integer start, @Param("rows") Integer rows);

    List<Comment> getUnreviewedCommentListByPage(@Param("start") Integer start, @Param("rows") Integer rows);
}
