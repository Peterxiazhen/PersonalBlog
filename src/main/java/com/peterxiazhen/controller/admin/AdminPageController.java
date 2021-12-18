package com.peterxiazhen.controller.admin;

import com.peterxiazhen.service.BlogService;
import com.peterxiazhen.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminPageController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogTypeService blogTypeService;

    /**
     * 转到后台主页面
     * @return
     */
    @RequestMapping(value = {"/index", "/"})
    public String showIndex() {
        return "admin/main";
    }

    /**
     * 让前端页面修改
     * @param blogId
     * @param model
     * @return
     */
    @RequestMapping("modifyBlog/{blogId}")
    public String showModifyBlog(@PathVariable Integer blogId, Model model) {
        model.addAttribute("blogTypeList", blogTypeService.getBlogTypeList());
        model.addAttribute("blog", blogService.selectByPrimaryKey(blogId));
        return "admin/modifyBlog";
    }

    @RequestMapping("/writeBlog")
    public String showWriteBlog(Model model) {
        model.addAttribute("blogTypeList", blogTypeService.getBlogTypeList());
        return "admin/writeBlog";
    }

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return "admin/" + page;
    }
}
