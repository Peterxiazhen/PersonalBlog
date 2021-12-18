package com.peterxiazhen.controller.admin;

import com.peterxiazhen.domain.Blog;
import com.peterxiazhen.domain.custom.BlogCustom;
import com.peterxiazhen.entity.MyResult;
import com.peterxiazhen.service.BlogService;
import com.peterxiazhen.service.BlogTypeService;
import com.peterxiazhen.service.CommentService;
import com.peterxiazhen.utils.UploadUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/blog", method = RequestMethod.POST)
public class AdminBlogController {
    @Value("${UPLOAD_PATH}")
    private String UPLOAD_PATH;

    @Autowired
    BlogService blogService;

    @Autowired
    BlogTypeService blogTypeService;

    @Autowired
    CommentService commentService;

    /**
     * page：表示当前页
     * rows：表示每页记录数
     */
    @RequestMapping("/list")
    @ResponseBody    //java对象转为json格式的数据
    public MyResult getBlogList(@RequestParam(value = "page", required = false) Integer page,
                            @RequestParam(value = "rows", required = false) Integer rows,
                            @RequestParam(value = "title", required = false) String title) {
        MyResult myResult = new MyResult();
        List<Blog> list = null;

        // 若请求文章标题为空
        if (title == null || title.equals("")) {
            list = blogService.getBlogListByPage(rows * (page - 1), rows);
        } else {
            list = blogService.getBlogListByTitleAndPage(rows * (page - 1), rows, "%" + title + "%");
        }

        List<BlogCustom> resultList = new LinkedList<>();
        list.forEach(blog->{
            BlogCustom blogCustom = new BlogCustom();
            BeanUtils.copyProperties(blog, blogCustom); // 对象之间的属性赋值

            Integer typeId = blogCustom.getTypeId();
            String typeName = blogTypeService.getBlogTypeNameById(typeId);  // 查询blog的所属文章类型

            blogCustom.setBlogType(typeName);
            resultList.add(blogCustom);
        });

        myResult.setRows(resultList);   // 返回结果的统一格式：数量 + 查询连标记集合

        if (title == null || title.equals("")) {
            myResult.setTotal(blogService.getBlogCount());
        } else {
            myResult.setTotal(blogService.getBlogCountByTitle(title));
        }

        return myResult;
    }

    // 插入博客
    @RequestMapping("/insert")
    @ResponseBody
    public String insert(Blog blog, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile, HttpServletRequest request) {
        // 补全Blog的所有属性
        blog.setClickCount(0); blog.setLikeCount(0); blog.setReplyCount(0);
        blog.setCreateDate(new Date());
        blog.setUpdateDate(new Date());

        if (imageFile != null && !imageFile.isEmpty()) {
            String imageName = null;
            try {
                imageName = UploadUtil.uploadFile(UPLOAD_PATH, imageFile, request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            blog.setBlogImage(imageName);
        }

        blogService.insertBlog(blog);
        return "success";
    }

    // 更新博客
    @RequestMapping("/update")
    @ResponseBody
    public String update(Blog blog,
                         @RequestParam(value = "imageFile",required = false) MultipartFile imageFile,
                         HttpServletRequest request) {
        // 更新博客不需要填充blog的create_date属性
        Blog blog1 = blogService.selectByPrimaryKey(blog.getId());
        System.out.println(blog1.getCreateDate());
        blog1.setTitle(blog.getTitle());
        blog1.setContent(blog.getContent());
        blog1.setTypeId(blog.getTypeId());
        blog1.setSummary(blog.getSummary());
        blog1.setUpdateDate(new Date());

        if (imageFile != null && !imageFile.isEmpty()) {
            String imageName = null;
            try {
                imageName = UploadUtil.uploadFile(UPLOAD_PATH, imageFile, request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            blog1.setBlogImage(imageName);
        }

        if (!(blog.getReprint() == null || blog.getReprint().equals(""))) {
            blog1.setReprint(blog.getReprint());
        }

        blogService.updateBlog(blog1);
        return "success";
    }

    // 删除博客
    @RequestMapping("/delete/{ids}")
    @ResponseBody
    public String deleteBlogById(@PathVariable String ids) {
        String[] strings = ids.split(",");
        for (String string : strings) {
            int index = Integer.parseInt(string);
            // 删掉评论
            commentService.deleteByPrimaryKey(index);
            // 删除博客
            blogService.deleteByPrimaryKey(index);
        }
        return "success";
    }

    // 查询博客
    @RequestMapping("/get/{blogId}")
    @ResponseBody
    public String getBlogById(@PathVariable Integer blogId) {
        BlogCustom blogCustom = new BlogCustom();
        Blog blog = blogService.selectByPrimaryKey(blogId);
        BeanUtils.copyProperties(blog, blogCustom);
        // 对blogCustom设置属性blogType
        blogCustom.setBlogType(blogTypeService.getBlogTypeNameById(blogCustom.getTypeId()));
        //return blogCustom;
        return "success";
    }
}
