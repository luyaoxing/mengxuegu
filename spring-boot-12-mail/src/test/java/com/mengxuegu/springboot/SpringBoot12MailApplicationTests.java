package com.mengxuegu.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot12MailApplicationTests {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Test
    public void testSimpleMail() {
        //封装简单的邮件内容
        SimpleMailMessage message = new SimpleMailMessage();
        //标题
        message.setSubject("放假通知");
        //正文
        message.setText("春节，路遥要给自己的心灵放个7天假期");

        //发件人
        message.setFrom("353005246@qq.com");
        //收件人
        message.setTo("CF_JS_SX_SX@163.com");
        javaMailSender.send(message);
    }

    //发送复杂邮件
    @Test
    public void testMimeMail() throws MessagingException {
        //封装简单的邮件内容
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        //提供了一个复杂邮件的帮助类
        //通过消息帮助对象来设置发送的内容，第二个参数为true标识可以发送附件
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        //标题
        messageHelper.setSubject("放假通知");
        //正文
        //第2个参数为true，才可以发送html代码
        messageHelper.setText("<h2 style='color:red'>春节，路遥要给自己的心灵放个7天假期</h2>", true);

        //发送附件
//        messageHelper.addAttachment("1.jpg", new File());   复制路径就行。第一个参数是发送出去，接受的文件名，第二个参数是本地路径
        //发件人
        messageHelper.setFrom("353005246@qq.com");
        //收件人
        messageHelper.setTo("CF_JS_SX_SX@163.com");
        javaMailSender.send(mimeMessage);
    }

}
