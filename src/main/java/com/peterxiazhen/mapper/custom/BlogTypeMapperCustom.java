package com.peterxiazhen.mapper.custom;

import com.peterxiazhen.domain.BlogType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogTypeMapperCustom {
    List<BlogType> getBlogTypeListByPage(@Param("start") int start, @Param("rows") int rows);
}
