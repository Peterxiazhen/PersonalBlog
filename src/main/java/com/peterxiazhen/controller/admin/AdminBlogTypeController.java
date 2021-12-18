package com.peterxiazhen.controller.admin;

import com.peterxiazhen.domain.BlogType;
import com.peterxiazhen.domain.BlogTypeExample;
import com.peterxiazhen.entity.MyResult;
import com.peterxiazhen.service.BlogService;
import com.peterxiazhen.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/blogType", method = RequestMethod.POST)
public class AdminBlogTypeController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogTypeService blogTypeService;

    @RequestMapping("/list")
    @ResponseBody
    public MyResult getBlogTypeList() {
        MyResult myResult = new MyResult();
        List<BlogType> blogTypeList = blogTypeService.getBlogTypeList();
        long count = blogTypeService.countByExample(new BlogTypeExample()); // 获取总数
        myResult.setRows(blogTypeList);
        myResult.setTotal(count);

        return myResult;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public String insertBlogType(BlogType blogType) {
        blogTypeService.insert(blogType);
        return "success";
    }

    @RequestMapping("/update/{id}")
    @ResponseBody
    public String updateBlogType(@PathVariable int id, BlogType blogType) {
        blogType.setId(id);
        blogTypeService.updateByPrimaryKey(blogType);
        return "success";
    }

    @RequestMapping("/delete/{ids}")
    @ResponseBody
    public String deleteBlogType(@PathVariable String ids) {
        String[] strings = ids.split(",");
        for (String string : strings) {
            int index = Integer.parseInt(string);
            //删除关联的blog
            blogService.deleteBlogByTypeId(index);
            blogTypeService.deleteByPrimaryKey(index);
        }
        return "success";
    }
}
