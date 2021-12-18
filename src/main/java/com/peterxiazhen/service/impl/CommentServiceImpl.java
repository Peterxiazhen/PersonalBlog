package com.peterxiazhen.service.impl;

import com.peterxiazhen.mapper.custom.CommentMapperCustom;
import com.peterxiazhen.service.CommentService;
import lombok.experimental.PackagePrivate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.peterxiazhen.mapper.CommentMapper;
import java.util.List;
import com.peterxiazhen.domain.CommentExample;
import com.peterxiazhen.domain.Comment;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private CommentMapperCustom commentMapperCustom;

    @Override
    public long getPassedCommentCountByBlogId(Integer blogId) {
        CommentExample example = new CommentExample();
        CommentExample.Criteria criteria = example.createCriteria();

        criteria.andBlogIdEqualTo(blogId);
        criteria.andStateEqualTo(1);    // 审核通过

        return commentMapper.countByExample(example);
    }


    // 查询所有的
    @Override
    public long getCommentCount() {
        CommentExample example = new CommentExample();
        return commentMapper.countByExample(example);
    }

    @Override
    public List<Comment> getCommentListByPage(Integer start, Integer rows) {
        return commentMapperCustom.getCommentListByPage(start, rows);
    }

    @Override
    public List<Comment> getUnreviewedCommentListByPage(Integer start, Integer rows) {
        // 使用start和rows这些参数的用commentMapperCustom
        return commentMapperCustom.getUnreviewedCommentListByPage(start, rows);
    }

    @Override
    public long getCommentCountByState(Integer state) {
        CommentExample example = new CommentExample();
        CommentExample.Criteria criteria = example.createCriteria();

        criteria.andStateEqualTo(state);
        return commentMapper.countByExample(example);
    }

    @Override
    public long countByExample(CommentExample example) {
        return commentMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(CommentExample example) {
        return commentMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Comment record) {
        return commentMapper.insert(record);
    }

    @Override
    public int insertSelective(Comment record) {
        return commentMapper.insertSelective(record);
    }

    @Override
    public List<Comment> selectByExample(CommentExample example) {
        return commentMapper.selectByExample(example);
    }

    @Override
    public Comment selectByPrimaryKey(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Comment record,CommentExample example) {
        return commentMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Comment record,CommentExample example) {
        return commentMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Comment record) {
        return commentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Comment record) {
        return commentMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Comment> getCommentListByPageAndBlogId(Integer start, Integer rows, Integer blogId) {
        return commentMapperCustom.getCommentListByPageAndBlogId(start, rows, blogId);
    }

}
