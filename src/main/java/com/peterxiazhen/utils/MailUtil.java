package com.peterxiazhen.utils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 发送邮件
 * 相关属性需要在resource/mail.properties配置文件中进行配置
 */
public class MailUtil {

    private static final String PROTOCOL;
    private static final String HOST;
    private static final String PORT;
    private static final String FROM;
    private static final String PASSWORD;
    private static final String TO;
    private static final String DEBUG;

    static {
        // 读取项目中后缀名为properties的配置文件
        ResourceBundle bundle = ResourceBundle.getBundle("resource/mail");
        PROTOCOL = bundle.getString("mail.protocol");
        HOST = bundle.getString("mail.host");
        PORT = bundle.getString("mail.port");
        FROM = bundle.getString("mail.from");
        PASSWORD = bundle.getString("mail.password");
        TO = bundle.getString("mail.to");
        String debug = bundle.getString("mail.debug");
        // 默认开启debug模式
        if (debug == null || debug.equals("")) {
            DEBUG = "true";
        } else {
            DEBUG = debug;
        }
    }

    /**
     * 发送邮件（同步）
     * @param subject   主题
     * @param mailContent   内容
     */
    public static void sendMail(String subject, String mailContent) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", PROTOCOL);    // 连接协议
        properties.put("mail.smtp.host", HOST); // 主机名
        properties.put("mail.smtp.port", PORT); // 端口号
        properties.put("mail.debug", DEBUG);    // 是否debug
        properties.put("mail.smtp.auth", "true");   // 身份认证
        properties.put("mail.smtp.ssl.enable", "true"); // 设置为使用ssl安全连接
        // 得到会话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        MimeMessage message = new MimeMessage(session);
        // 设置发件人的邮箱
        message.setFrom(new InternetAddress(FROM));
        // 何值收件人的邮箱（单个）
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        // 设置邮件的标题
        message.setSubject(subject);
        // 设置邮件内容
        message.setText(mailContent);
        // 得到“邮差”对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱
        transport.connect(FROM, PASSWORD);
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        // 关闭连接
        transport.close();
    }

    /**
     * 发送邮件（异步），可用于提高请求的响应速度
     * @param subject
     * @param
     */
    // 注意：这里解释一下为什么String是final类型的：因为不可变对象不能被更改，所以它们可以在多个线程之间自由地共享，避免出现线程不安全的现象
    public static void sendMailAsynchronously(final String subject, final String content) {
        new Thread(() -> {
            try {
                sendMail(subject, content);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
