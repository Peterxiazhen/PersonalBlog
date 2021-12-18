package com.peterxiazhen.service.impl;

import com.peterxiazhen.service.BlogTypeService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import com.peterxiazhen.domain.BlogType;
import com.peterxiazhen.domain.BlogTypeExample;
import com.peterxiazhen.mapper.BlogTypeMapper;

@Service
public class BlogTypeServiceImpl implements BlogTypeService {

    @Resource
    private BlogTypeMapper blogTypeMapper;

    // 根据typeId（主键）查询类型名称
    @Override
    public String getBlogTypeNameById(Integer id) {
        BlogType blogType = blogTypeMapper.selectByPrimaryKey(id);
        String typeName = blogType.getTypeName();
        return typeName;
    }

    @Override
    public long countByExample(BlogTypeExample example) {
        return blogTypeMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(BlogTypeExample example) {
        return blogTypeMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return blogTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BlogType record) {
        return blogTypeMapper.insert(record);
    }

    @Override
    public int insertSelective(BlogType record) {
        return blogTypeMapper.insertSelective(record);
    }

    @Override
    public List<BlogType> selectByExample(BlogTypeExample example) {
        return blogTypeMapper.selectByExample(example);
    }

    @Override
    public BlogType selectByPrimaryKey(Integer id) {
        return blogTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(BlogType record,BlogTypeExample example) {
        return blogTypeMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(BlogType record,BlogTypeExample example) {
        return blogTypeMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(BlogType record) {
        return blogTypeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BlogType record) {
        return blogTypeMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<BlogType> getBlogTypeList() {
        BlogTypeExample example = new BlogTypeExample();
        return blogTypeMapper.selectByExample(example);
    }

}
