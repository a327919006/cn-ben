package com.cn.ben.service.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 * @date 2019/5/10.
 */
@Component
@Slf4j
public class MailHandler {

    @Resource
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String mailFrom;
    @Value("${spring.mail.receiver}")
    private String mailTo;

    /**
     * 发送邮件
     *
     * @param title   标题
     * @param content 内容
     */
    public void sendMail(String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailFrom);
        message.setTo(mailTo);
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);
    }
}
