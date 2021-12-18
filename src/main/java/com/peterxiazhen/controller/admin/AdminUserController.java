package com.peterxiazhen.controller.admin;

import com.peterxiazhen.domain.User;
import com.peterxiazhen.domain.UserExample;
import com.peterxiazhen.service.UserService;
import com.peterxiazhen.utils.CryptographyUtil;
import com.peterxiazhen.utils.UploadUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 管理用户登录设置
 */
@Controller
public class AdminUserController {

    @Autowired
    private UserService userService;

    private final String SALT = "blog";

    @Value("${UPLOAD_PATH}")
    private String UPLOAD_PATH;

    @RequestMapping(value = "/loginUser")
    public String login(User user, HttpSession session, Model model) {
        Subject subject = SecurityUtils.getSubject();
        // 这里使用了md5码转换名
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), CryptographyUtil.md5(user.getPassword(), SALT));
        // 这里的user是login页面输入的，只有username和password属性，avator和profile是空的
        try {
            subject.login(token);
            UserExample example = new UserExample();
            UserExample.Criteria criteria = example.createCriteria();
            criteria.andUsernameEqualTo(user.getUsername());
            List<User> userList = userService.selectByExampleWithBlobs(example); // 能登陆后台的只有一个
            if (userList != null && !userList.isEmpty()) {
                User user1 = userList.get(0);
                //session.setAttribute("user", user1);
                model.addAttribute("user", user1);
            }

            // 重定向实际上是客户端再次向服务器发送请求，所以无法访问WEB-INF目录
            return "admin/main";   // WEB-INF目录:是Java的Web应用的安全目录。所谓安全就是客户端无法访问，只有服务端可以访问的目录。页面放在WEB-INF目录下面,这样可以限制访问,提高安全性.如JSP,html
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorInfo", "用户名或密码输入错误");
        }


        return "admin/login";
    }

    @RequestMapping(value = "/admin/user/update", method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(User userParam,
                             @RequestParam(value = "avatarFile", required = false) MultipartFile avatarFile,
                             HttpServletRequest request,
                             HttpSession session) {
        User user = userService.selectByPrimaryKey(userParam.getId());
        if (avatarFile != null && !avatarFile.isEmpty()) {
            String imageName = null;
            try {
                imageName = UploadUtil.uploadFile(UPLOAD_PATH, avatarFile, request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            user.setAvatar(imageName);  // 修改用户的头像
        }
        user.setProfile(userParam.getProfile());    // 修改用户的摘要
        userService.updateByPrimaryKeyWithBLOBs(user);
        session.setAttribute("user", user); // 更新session
        return "success";
    }

    @RequestMapping(value = "/admin/user/updatePassword/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String updatePassword(@PathVariable int id, @RequestParam(value = "newPassword") String password) {
        User user = userService.selectByPrimaryKey(id);
        user.setPassword(CryptographyUtil.md5(password, SALT));
        userService.updateByPrimaryKeyWithBLOBs(user);
        return "success";
    }

    @RequestMapping("/admin/user/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/index";
    }

    /**
     * 获取用户的个人简介，防止中文乱码，使用了produces
     * @param id
     * @return
     */
    @RequestMapping(value = "admin/user/{id}", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    @ResponseBody
    public String getUserProfile(@PathVariable Integer id) {
        return userService.selectByPrimaryKey(id).getProfile();
    }
}
