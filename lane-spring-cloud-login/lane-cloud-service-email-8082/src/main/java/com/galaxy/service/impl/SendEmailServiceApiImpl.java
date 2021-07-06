package com.galaxy.service.impl;

import com.galaxy.service.SendEmailServiceApi;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author lane
 * @date 2021年06月27日 上午11:19
 */
@Service
public class SendEmailServiceApiImpl implements SendEmailServiceApi {

//    /**
//     * 邮件发送器
//     */
//    @Autowired
//    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromMail;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.protocol}")
    private String protocol;
    @Value("${spring.mail.auth}")
    private String auth;
    @Value("${spring.mail.authentication}")
    private String authentication;


    /**
     * 发送邮件的方法
     *
     * @param to      接收人
     * @param content 邮件内容
     * @return
     */
    @Override
    public String sendMail(String to, String title, String content) {
        /**
         * 1、设置邮箱的一些属性，关于Properties类的介绍，见末尾博客
         * 2、创建认证对象authenticator，使用自己的邮件账号和授权码
         * 3、获得一个session对象，用来保存认证对象
         * 4、创建邮件消息对象message
         * 	4.1、设置message的发送人，这个要和认证对象的账号一致
         *  4.2、设置message的接收人
         * 	4.3、设置邮件的主题和内容
         */
//        System.out.println("mail.username:" + "ss" + fromMail);
        // 1、创建Properties属性对象，并设置一些邮件的属性
        Properties props = new Properties();
        props.setProperty("mail.host", host); // 设置邮箱服务器
        props.setProperty("mail.transport.protocol", protocol); // 设置邮箱发送的协议
        props.setProperty("mail.smtp.auth", auth); // 设置认证方式

        // 2、创建认证对象authenticator
        Authenticator authenticator = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromMail, authentication); // 邮件账号和授权码，注意不是密码。
            }
        };

        // 3、创建会话对象session
        Session session = Session.getInstance(props, authenticator);

        // 4、创建邮件消息对象，设置发送人、接收人、邮件主题、邮件内容
        MimeMessage mess = new MimeMessage(session);
        try {
            mess.setFrom(new InternetAddress(fromMail)); // 设置邮件的发件人
            mess.setRecipients(Message.RecipientType.TO, to); // 设置收件人
            mess.setSubject("注册信息"); // 设置邮件标题
            String code = "code是：" + content + "请尽快输入";
            mess.setContent(code, "text/html;charset=utf-8"); // 设置邮件内容和格式

            // 5、发送邮件
            Transport.send(mess);
        } catch (MessagingException e) {
            e.printStackTrace();
            return "发送邮件失败, 原因:" + e.getMessage();
        }
        return "发送邮件成功！接收人：" + to;

    }

    @Override
    public boolean sendMailSimple(String email, String code) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(fromMail);
        mailMessage.setTo(email);
        mailMessage.setSubject("验证码");
        mailMessage.setText(code);

        try {
//            mailSender.send(mailMessage);
        } catch (Exception ex) {
            System.out.println(String.format("Failed to send message to %s", email));
            ex.printStackTrace();
            return false;
        }
        System.out.println("mail from " + fromMail + "=======>" + email + " send code:" + code);
        return true;
    }

}