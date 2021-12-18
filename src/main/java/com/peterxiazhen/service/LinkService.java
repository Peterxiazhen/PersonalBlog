package com.peterxiazhen.service;

import java.util.List;
import com.peterxiazhen.domain.Link;
import com.peterxiazhen.domain.LinkExample;
public interface LinkService{


    long countByExample(LinkExample example);

    int deleteByExample(LinkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Link record);

    int insertSelective(Link record);

    List<Link> selectByExample(LinkExample example);

    Link selectByPrimaryKey(Integer id);

    int updateByExampleSelective(Link record,LinkExample example);

    int updateByExample(Link record,LinkExample example);

    int updateByPrimaryKeySelective(Link record);

    int updateByPrimaryKey(Link record);

    List<Link> getLinkList();

    List<Link> getLinkListByPage(Integer start, Integer rows);

    long getLinkCount();
}
