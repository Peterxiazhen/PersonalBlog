package com.peterxiazhen.mapper.custom;

import com.peterxiazhen.domain.Link;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LinkMapperCustom {
    List<Link> getLinkListOrdered();

    List<Link> getLinkListByPage(@Param("start") Integer start, @Param("rows") Integer rows);
}
