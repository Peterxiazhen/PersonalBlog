package com.peterxiazhen.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 生成密钥的工具类
 * 盐(Salt),在密码学中,是指在散列之前将散列内容(例如:密码)的任意固定位置插入特定的字符串
 * 根据已有的密码字符串去生成一个密码+盐字符串,可以将盐的加密字符串也存放在数据库
 *
 */
public class CryptographyUtil {
    public static String md5(String str, String salt) {
        return new Md5Hash(str, salt).toString();
    }
}
