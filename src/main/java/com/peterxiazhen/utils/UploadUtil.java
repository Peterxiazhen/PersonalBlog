package com.peterxiazhen.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UploadUtil {

    /**
     * @param uploadPath 上传路径
     * @param file  上传的文件
     * @param request   HttpServletRequest 用于获取虚拟路径
     * @return 生成头像所在的url路径
     * @throws Exception
     */
    public static String uploadFile(String uploadPath, MultipartFile file, HttpServletRequest request) throws Exception {
        if (file == null) {
            throw new Exception("文件不存在，无法上传");
        }

        String realName = UUID.randomUUID().toString() + "." + file.getOriginalFilename().split("\\.")[1];
        String realPath = request.getServletContext().getRealPath(uploadPath);
        String pathDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        String path = realPath + "/" + pathDate;

        File f = new File(path);
        if (!f.exists()) f.mkdirs();

        try {
            file.transferTo(new File(path + "/" + realName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return request.getContextPath() + uploadPath + "/" + pathDate + "/" + realName;
    }
}
