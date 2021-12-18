package com.peterxiazhen.controller.home;

import com.peterxiazhen.domain.Blog;
import com.peterxiazhen.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
    @Value("${ABOUT_ID}")
    private String ABOUT_ID;

    @Autowired
    BlogService blogService;

    /**
     * 首页
     * @param title
     * @param blogType
     * @param model
     * @return
     */
    @RequestMapping(value = {"/", "index"}) // 可以通过两种路径来访问该方法
    public String showIndex(@RequestParam(value = "title", required = false) String title,
                            @RequestParam(value = "blogType", required = false) Integer blogType,
                            Model model) {
        if (title != null && !title.isEmpty()) {
            model.addAttribute("title", title);
        } else {
            model.addAttribute("title", "");
        }

        if (blogType != null) {
            model.addAttribute("blogType", blogType);
        } else {
            model.addAttribute("blogType", 0);
        }
        return "index";
    }

    /**
     * 根据id查找博客
     * @param id
     * @param model
     * @return jsp
     */
    @RequestMapping("/article/{id}")
    public String showArticle(@PathVariable int id, Model model) {
        Blog blog = blogService.selectByPrimaryKey(id);
        if (blog == null) {
            return "error";
        }

        model.addAttribute("blog", blog);
        return "article";
    }

    /**
     * 转到登陆页面
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "admin/login";
    }

    /**
     * 转到404页面
     * @return
     */
    @RequestMapping("/error")
    public String showError() {
        return "error";
    }

    /**
     * 关于本博文
     * @param model
     * @return
     */
    @RequestMapping("/about")
    public String showAbout(Model model) {
        Blog blog = blogService.selectByPrimaryKey(Integer.parseInt(ABOUT_ID));
        model.addAttribute("blog", blog);
        return "about";
    }
}
