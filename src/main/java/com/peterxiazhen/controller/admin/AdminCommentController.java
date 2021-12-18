package com.peterxiazhen.controller.admin;

import com.peterxiazhen.domain.Blog;
import com.peterxiazhen.domain.Comment;
import com.peterxiazhen.domain.custom.CommentCustom;
import com.peterxiazhen.entity.MyResult;
import com.peterxiazhen.service.BlogService;
import com.peterxiazhen.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/comment", method = RequestMethod.POST)
public class AdminCommentController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    /**
     * 评论信息管理
     * 显示全部评论（state = 0 or state = 1）
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public MyResult getCommentListByPage(@RequestParam(value = "page", required = false) Integer page,
                                         @RequestParam(value = "rows", required = false) Integer rows) {
        MyResult result = new MyResult();
        List<CommentCustom> resultList = new LinkedList<>();
        List<Comment> commentList = commentService.getCommentListByPage(rows * (page - 1), rows);
        if (commentList != null && !commentList.isEmpty()) {
            for (Comment comment: commentList) {
                CommentCustom commentCustom = new CommentCustom();
                BeanUtils.copyProperties(comment, commentCustom);
                commentCustom.setBlogTitle(blogService.selectByPrimaryKey(comment.getBlogId()).getTitle());
                resultList.add(commentCustom);
            }
        }

        result.setRows(resultList);
        result.setTotal(commentService.getCommentCount());
        return result;
    }

    // 评论审核列表
    @RequestMapping("/unreviewedlist")
    @ResponseBody
    public MyResult getUnreviewedCommentListByPage(@RequestParam(value = "page", required = false) Integer page,
                                                   @RequestParam(value = "rows", required = false) Integer rows) {
        MyResult result = new MyResult();
        List<CommentCustom> resultList = new LinkedList<>();
        List<Comment> unreviewedCommentList = commentService.getUnreviewedCommentListByPage(rows * (page -1), rows);
        if (unreviewedCommentList != null && !unreviewedCommentList.isEmpty()) {
            for (Comment comment : unreviewedCommentList) {
                CommentCustom commentCustom = new CommentCustom();
                BeanUtils.copyProperties(comment, commentCustom);
                commentCustom.setBlogTitle(blogService.selectByPrimaryKey(comment.getBlogId()).getTitle());
                resultList.add(commentCustom);
            }
        }
        result.setRows(resultList); // 获取某一页的未评审的评论
        result.setTotal(commentService.getCommentCountByState(0));   // 获取未评审的评论的总数
        return result;
    }

    // 审核评论
    @RequestMapping("/review/{ids}/{state}")
    @ResponseBody
    public String setCommentState(@PathVariable String ids, @PathVariable int state) {
        String[] strings = ids.split(",");
        for (String string : strings) {
            int id = Integer.parseInt(string);
            Comment comment = commentService.selectByPrimaryKey(id);
            comment.setState(state);
            commentService.updateByPrimaryKey(comment);

            Blog blog = blogService.selectByPrimaryKey(comment.getBlogId());
            blog.setReplyCount((int) commentService.getPassedCommentCountByBlogId(blog.getId()));   // 更新回复次数
            blogService.updateBlog(blog);
        }

        return "success";
    }

    // 删除评论
    @RequestMapping("/delete/{ids}")
    @ResponseBody
    public String deleteComment(@PathVariable String ids) {
        String[] strings = ids.split(",");

        for (String string : strings) {
            int id = Integer.parseInt(string);
            Comment comment = commentService.selectByPrimaryKey(id);
            // 如果审核评论是通过的，删除时应该从相应的文章评论总数中减去
            if (comment.getState() == 1) {
                Blog blog = blogService.selectByPrimaryKey(comment.getBlogId());
                blog.setReplyCount(blog.getReplyCount() - 1);
                blogService.updateBlog(blog);
            }
            commentService.deleteByPrimaryKey(id);
        }

        return "success";
    }
}
