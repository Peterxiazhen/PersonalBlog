package com.peterxiazhen.controller.admin;

import com.peterxiazhen.domain.Link;
import com.peterxiazhen.entity.MyResult;
import com.peterxiazhen.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/link", method = RequestMethod.POST)
public class AdminLinkController {
    @Autowired
    private LinkService linkService;

    // 增加友情链接
    @RequestMapping("/insert")
    @ResponseBody
    public String insertLink(Link link) {
        linkService.insert(link);
        return "success";
    }

    // 删除友情链接
    @RequestMapping("/delete/{ids}")
    @ResponseBody
    public String deleteLink(@PathVariable String ids) {
        String[] strings = ids.split(",");
        for (String string : strings) {
            int id = Integer.parseInt(string);
            linkService.deleteByPrimaryKey(id);
        }
        return "success";
    }

    // 修改友情链接
    @RequestMapping("/update/{id}")
    @ResponseBody
    public String updateLink(@PathVariable int id, Link link) {
        link.setId(id);
        linkService.updateByPrimaryKey(link);
        return "success";
    }

    // 展示友情链接
    @RequestMapping("/list")
    @ResponseBody
    public MyResult getLinkListByPage(@RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "rows", required = false) Integer rows) {
        MyResult result = new MyResult();
        List<Link> list = linkService.getLinkListByPage(rows * (page - 1), rows);
        result.setRows(list);
        result.setTotal(linkService.getLinkCount());
        return result;
    }
}
