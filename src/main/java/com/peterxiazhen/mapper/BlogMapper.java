package com.peterxiazhen.mapper;

import com.peterxiazhen.domain.Blog;
import com.peterxiazhen.domain.BlogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;


public interface BlogMapper {
    long countByExample(BlogExample example);

    int deleteByExample(BlogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Blog record);

    int insertSelective(Blog record);

    List<Blog> selectByExample(BlogExample example);

    Blog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByExample(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    void updateByPrimaryKeyWithBLOBs(Blog blog);

    List<Blog> getBlogListOrderByClickCount();
}