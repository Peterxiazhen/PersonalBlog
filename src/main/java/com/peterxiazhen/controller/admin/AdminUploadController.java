package com.peterxiazhen.controller.admin;

import com.peterxiazhen.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminUploadController {

    @Value("${UPLOAD_PATH}")
    private String UPLOAD_PATH;

    @RequestMapping("uploadImg")
    @ResponseBody
    public Map uploadImage(@RequestParam(value = "editormd-image-file", required = true) MultipartFile myFileName, HttpServletRequest request) {
        String imgUrl = "";
        Map res = new HashMap();    // 相当于Map<String, Object> map
        try {
            imgUrl = UploadUtil.uploadFile(UPLOAD_PATH, myFileName, request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        res.put("url", imgUrl);
        res.put("success", 1);
        res.put("message", "upload success!");

        return res;
    }
}
