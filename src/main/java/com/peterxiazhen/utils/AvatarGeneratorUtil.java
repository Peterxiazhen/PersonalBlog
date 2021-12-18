package com.peterxiazhen.utils;

import com.talanlabs.avatargenerator.Avatar;
import com.talanlabs.avatargenerator.eightbit.EightBitAvatar;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class AvatarGeneratorUtil {

    private static final String SUFFIX = ".png";

    private static Random random = new Random();

    private static Avatar avatar = EightBitAvatar.newMaleAvatarBuilder().build();   // 生成8bit的头像

    public static String generatorAvatar(String uploadPath, HttpServletRequest request) {
        String realName = UUID.randomUUID().toString() + SUFFIX;    // uuid生成头像名称
        String realPath = request.getServletContext().getRealPath(uploadPath);
        String pathDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

        String path = realPath + "/" + pathDate;
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs(); // mkdirs用于创建多层目录结构
        }

        // 随机生成8bit的头像
        avatar.createAsPngToFile(random.nextLong(), new File(path + "/" + realName));
        return request.getContextPath() + uploadPath + "/" + pathDate + "/" + realName;
    }

}
