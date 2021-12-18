package com.peterxiazhen.controller.home;

import com.peterxiazhen.domain.Blog;
import com.peterxiazhen.domain.Comment;
import com.peterxiazhen.entity.MyResult;
import com.peterxiazhen.service.BlogService;
import com.peterxiazhen.service.CommentService;
import com.peterxiazhen.utils.AvatarGeneratorUtil;
import com.peterxiazhen.utils.IpAddressUtil;
import com.peterxiazhen.utils.MailUtil;
import com.peterxiazhen.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Value("${UPLOAD_PATH}")
    private String UPLOAD_PATH;

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public String addComment(Comment comment,
                             @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                             HttpServletRequest request) {
        String imagePath = "";
        comment.setCommentDate(new Date()); // 设置评价日期
        comment.setState(0);    // 未审核的状态
        comment.setVisitorIp(IpAddressUtil.getIpAddress(request));

        // 读者上传了头像
        if (imageFile != null) {
            try {
                imagePath = UploadUtil.uploadFile(UPLOAD_PATH, imageFile, request);
                comment.setVisitorAvatar(imagePath);
                commentService.insert(comment);
                sendMail(comment);
                return imagePath;
            } catch (Exception e) {
                e.printStackTrace();
                return "error";
            }
        } else if (comment.getVisitorAvatar() == null || comment.getVisitorAvatar().equals("")) {    // 如果没有头像，则随机生成8-bit的头像
            imagePath = AvatarGeneratorUtil.generatorAvatar(UPLOAD_PATH, request);
            comment.setVisitorAvatar(imagePath);
            commentService.insert(comment);
            sendMail(comment);
            return imagePath;
        } else {
            commentService.insert(comment);
            sendMail(comment);
            return comment.getVisitorAvatar();
        }
    }

    /**
     * 发送邮件（ok）
     * @param comment
     */
    private void sendMail(Comment comment) {
        // 根据blogId获取博客
        Blog blog = blogService.selectByPrimaryKey(comment.getBlogId());
        String mailContent = comment.getVisitorName() + "在《" + blog.getTitle() + "》下评论：\n" +
                comment.getContent() + "\n请您前去审查!";
        MailUtil.sendMailAsynchronously("博客评论审核", mailContent);
    }

    /**
     * 查询评论（ok）
     * @param page
     * @param rows
     * @param blogId
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public MyResult getCommentList(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                   @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows,
                                   @RequestParam(value = "blogId") Integer blogId) {
        MyResult myResult = new MyResult();
        myResult.setRows(commentService.getCommentListByPageAndBlogId(rows * (page - 1), rows, blogId));
        myResult.setTotal(commentService.getPassedCommentCountByBlogId(blogId));
        return myResult;
    }
}
