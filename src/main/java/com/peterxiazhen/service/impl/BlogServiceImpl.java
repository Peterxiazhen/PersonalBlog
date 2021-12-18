package com.peterxiazhen.service.impl;

import com.peterxiazhen.mapper.custom.BlogMapperCustom;
import com.peterxiazhen.service.BlogService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.peterxiazhen.mapper.BlogMapper;
import com.peterxiazhen.domain.Blog;
import java.util.List;
import com.peterxiazhen.domain.BlogExample;

@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogMapperCustom blogMapperCustom;

    @Resource
    private BlogMapper blogMapper;

    // 根据页码进行查询
    @Override
    public List<Blog> getBlogListByPage(int start, int rows) {
        return blogMapperCustom.getBlogListByPage(start, rows);
    }

    // 根据页码和title进行查询
    @Override
    public List<Blog> getBlogListByTitleAndPage(int start, int rows, String title) {
        return blogMapperCustom.getBlogListByTitleAndPage(start, rows, title);
    }


    // 获取博客总数
    @Override
    public Long getBlogCount() {
        BlogExample blogExample = new BlogExample();
        return blogMapper.countByExample(blogExample);
    }

    // 根据title获取博客总数
    @Override
    public Long getBlogCountByTitle(String title) {
        BlogExample blogExample = new BlogExample();
        BlogExample.Criteria criteria = blogExample.createCriteria();
        criteria.andTitleLike(title);

        return blogMapper.countByExample(blogExample);
    }

    // 根据BlogId查询Blog
    @Override
    public Blog selectByPrimaryKey(Integer id) {
        return blogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Blog> getBlogListByTypeIdTitleAndPage(Integer start, Integer rows, Integer typeId, String title) {
        return blogMapperCustom.getBlogListByTypeIdTitleAndPage(start, rows, typeId, title);
    }

    /**
     * 根据typeId查找博客数量
     * @param typeId
     * @return
     */
    @Override
    public Long getBlogCountByTypeId(Integer typeId) {
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andTypeIdEqualTo(typeId);
        return blogMapper.countByExample(example);
    }

    /**
     * 根据博客点击数获取博客列表
     * @return
     */
    @Override
    public List<Blog> getBlogListOrderByClickCount() {
        return blogMapper.getBlogListOrderByClickCount();
    }

    @Override
    public long countByExample(BlogExample example) {
        return blogMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(BlogExample example) {
        return blogMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return blogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Blog record) {
        return blogMapper.insert(record);
    }

    @Override
    public int insertSelective(Blog record) {
        return blogMapper.insertSelective(record);
    }

    @Override
    public List<Blog> selectByExample(BlogExample example) {
        return blogMapper.selectByExample(example);
    }

    @Override
    public int updateByExampleSelective(Blog record,BlogExample example) {
        return blogMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Blog record,BlogExample example) {
        return blogMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Blog record) {
        return blogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Blog record) {
        return blogMapper.updateByPrimaryKey(record);
    }

    @Override
    public void insertBlog(Blog blog) {
        blogMapper.insert(blog);
    }

    @Override
    public void updateBlog(Blog blog) {
        blogMapper.updateByPrimaryKeyWithBLOBs(blog);
    }

    @Override
    public void deleteBlogByTypeId(int typeId) {
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andTypeIdEqualTo(typeId);
        blogMapper.deleteByExample(example);
    }

}
