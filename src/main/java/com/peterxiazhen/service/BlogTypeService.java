package com.peterxiazhen.service;

import java.util.List;
import com.peterxiazhen.domain.BlogType;
import com.peterxiazhen.domain.BlogTypeExample;
public interface BlogTypeService{
    String getBlogTypeNameById(Integer id);

    long countByExample(BlogTypeExample example);

    int deleteByExample(BlogTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogType record);

    int insertSelective(BlogType record);

    List<BlogType> selectByExample(BlogTypeExample example);

    BlogType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(BlogType record,BlogTypeExample example);

    int updateByExample(BlogType record,BlogTypeExample example);

    int updateByPrimaryKeySelective(BlogType record);

    int updateByPrimaryKey(BlogType record);

    List<BlogType> getBlogTypeList();
}
