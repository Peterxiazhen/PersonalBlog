package com.peterxiazhen.service.impl;

import com.peterxiazhen.mapper.custom.LinkMapperCustom;
import com.peterxiazhen.service.LinkService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.peterxiazhen.mapper.LinkMapper;
import com.peterxiazhen.domain.Link;
import com.peterxiazhen.domain.LinkExample;

@Service
public class LinkServiceImpl implements LinkService {

    @Resource
    private LinkMapper linkMapper;

    @Resource
    private LinkMapperCustom linkMapperCustom;

    @Override
    public long countByExample(LinkExample example) {
        return linkMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(LinkExample example) {
        return linkMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return linkMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Link record) {
        return linkMapper.insert(record);
    }

    @Override
    public int insertSelective(Link record) {
        return linkMapper.insertSelective(record);
    }

    @Override
    public List<Link> selectByExample(LinkExample example) {
        return linkMapper.selectByExample(example);
    }

    @Override
    public Link selectByPrimaryKey(Integer id) {
        return linkMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Link record,LinkExample example) {
        return linkMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Link record,LinkExample example) {
        return linkMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Link record) {
        return linkMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Link record) {
        return linkMapper.updateByPrimaryKey(record);
    }

    /**
     * 根据link_order获取所有的友情链接
     * @return
     */
    @Override
    public List<Link> getLinkList() {
        return linkMapperCustom.getLinkListOrdered();
    }

    @Override
    public List<Link> getLinkListByPage(Integer start, Integer rows) {
        return linkMapperCustom.getLinkListByPage(start, rows);
    }

    @Override
    public long getLinkCount() {
        LinkExample example = new LinkExample();
        return linkMapper.countByExample(example);
    }

}
