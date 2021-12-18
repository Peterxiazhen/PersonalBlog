package com.peterxiazhen.controller.home;

import com.peterxiazhen.domain.Link;
import com.peterxiazhen.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    /**
     * 获取所有友情链接
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<Link> getLinkList() {
        return linkService.getLinkList();
    }
}
