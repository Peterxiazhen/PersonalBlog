package com.peterxiazhen.controller.home;

import com.peterxiazhen.domain.BlogType;
import com.peterxiazhen.domain.custom.BlogTypeCustom;
import com.peterxiazhen.service.BlogService;
import com.peterxiazhen.service.BlogTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/articleType")
public class ArticleTypeController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogTypeService blogTypeService;

    /**
     * 获取博客的类型
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public List<BlogTypeCustom> getArticleTypeList() {
        List<BlogTypeCustom> resultList = new LinkedList<>();
        List<BlogType> blogTypeList = blogTypeService.getBlogTypeList();

        blogTypeList.forEach(blogType -> {
            BlogTypeCustom blogTypeCustom = new BlogTypeCustom();
            BeanUtils.copyProperties(blogType, blogTypeCustom);

            blogTypeCustom.setBlogCount(blogService.getBlogCountByTypeId(blogType.getId()));
            resultList.add(blogTypeCustom);
        });
        return resultList;
    }
}
