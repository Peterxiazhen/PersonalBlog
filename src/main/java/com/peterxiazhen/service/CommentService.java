package com.peterxiazhen.service;

import java.util.List;
import com.peterxiazhen.domain.CommentExample;
import com.peterxiazhen.domain.Comment;
public interface CommentService{

    long getPassedCommentCountByBlogId(Integer blogId); // 根据blogId查询过审的博客数量

    long countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(Comment record,CommentExample example);

    int updateByExample(Comment record,CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> getCommentListByPageAndBlogId(Integer start, Integer rows, Integer blogId);

    List<Comment> getCommentListByPage(Integer start, Integer rows);

    long getCommentCount();

    List<Comment> getUnreviewedCommentListByPage(Integer start, Integer rows);

    long getCommentCountByState(Integer state);
}
