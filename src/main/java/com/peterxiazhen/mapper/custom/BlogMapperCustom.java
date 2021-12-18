package com.peterxiazhen.mapper.custom;

import com.peterxiazhen.domain.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogMapperCustom {
    List<Blog> getBlogListByPage(@Param("start") int start, @Param("rows") int rows);
    List<Blog> getBlogListByTitleAndPage(@Param("start") int start, @Param("rows") int rows, @Param("title") String title);
    List<Blog> getBlogListByTypeIdTitleAndPage(@Param("start") Integer start, @Param("rows") Integer rows,
                                               @Param("typeId")Integer typeId, @Param("title") String title);
}
