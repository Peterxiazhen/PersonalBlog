package com.peterxiazhen.controller.home;

import com.peterxiazhen.domain.Blog;
import com.peterxiazhen.entity.MyResult;
import com.peterxiazhen.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    BlogService blogService;

    @RequestMapping("/get/{id}")
    @ResponseBody
    public Blog getArticle(@PathVariable int id) {
        return blogService.selectByPrimaryKey(id);
    }

    /**
     * 查询博客列表
     * @return total + List<>
     */
    @RequestMapping("/list")
    @ResponseBody
    public MyResult getArticleList(@RequestParam(value = "page", required = false) Integer page,
                                   @RequestParam(value = "rows", required = false) Integer rows,
                                   @RequestParam(value = "blogType") Integer typeId,
                                   @RequestParam(value = "title", required = false) String title) {
        if (title == null) title = "";
        MyResult result = new MyResult();
        List<Blog> list;
        if (typeId == 0) {  // 从1开始
            list = blogService.getBlogListByTitleAndPage(rows * (page - 1), rows, "%" + title + "%");
            result.setTotal(blogService.getBlogCount());
        } else {
            list = blogService.getBlogListByTypeIdTitleAndPage(rows * (page - 1), rows, typeId,
                    "%" + title + "%");
            result.setTotal(blogService.getBlogCountByTypeId(typeId));
        }

        result.setRows(list);
        return result;
    }

    /**
     * 增加点击数
     * @param blogId
     * @return
     */
    @RequestMapping(value = "/click/add", method = RequestMethod.POST)
    @ResponseBody
    public Integer addClick(Integer blogId) {
        Blog blog = blogService.selectByPrimaryKey(blogId);
        blog.setClickCount(blog.getClickCount() + 1);
        blogService.updateBlog(blog);
        return blog.getClickCount();
    }

    /**
     * 增加喜欢数
     * @param blogId
     * @return
     */
    @RequestMapping(value = "/like/add", method = RequestMethod.POST)
    @ResponseBody
    public Integer addLike(Integer blogId) {
        Blog blog = blogService.selectByPrimaryKey(blogId);
        blog.setLikeCount(blog.getLikeCount() + 1);
        blogService.updateBlog(blog);
        return blog.getLikeCount();
    }

    /**
     * 根据点击量返回博客列表
     * @return
     */
    @RequestMapping(value = "/order/clickCount", method = RequestMethod.POST)
    @ResponseBody
    public List<Blog> getBlogListOrderByClickCount() {
        return blogService.getBlogListOrderByClickCount();
    }
}
