package com.peterxiazhen.service;

import com.peterxiazhen.domain.Blog;
import java.util.List;
import com.peterxiazhen.domain.BlogExample;
public interface BlogService{
    List<Blog> getBlogListByPage(int start, int rows);  // 查询某一页的所有博客
    List<Blog> getBlogListByTitleAndPage(int start, int rows, String title);
    Long getBlogCount();    // 获取博客的数量
    Long getBlogCountByTitle(String title); // 根据title获取博客的数量
    Blog selectByPrimaryKey(Integer id);    // 根据BlogId查询Blog
    List<Blog> getBlogListByTypeIdTitleAndPage(Integer start, Integer rows, Integer typeId, String title);
    Long getBlogCountByTypeId(Integer typeId);  // 根据typeId获取博客的数量
    List<Blog> getBlogListOrderByClickCount();  // 根据阅读量获取博客列表

    long countByExample(BlogExample example);

    int deleteByExample(BlogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Blog record);

    int insertSelective(Blog record);

    List<Blog> selectByExample(BlogExample example);

    int updateByExampleSelective(Blog record,BlogExample example);

    int updateByExample(Blog record,BlogExample example);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    void insertBlog(Blog blog);

    void updateBlog(Blog blog);

    void deleteBlogByTypeId(int typeId);
}
