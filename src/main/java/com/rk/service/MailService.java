package com.rk.service;

import com.rk.exception.SpringStoreException;
import com.rk.model.NotificationMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;


@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailContentBuilder mailContentBuilder;

    @Async
    public void sendMail( String recipient,String msg){
        MimeMessagePreparator mimeMessagePreparator= mimeMessage -> {
            MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("amazon@email.com");
            messageHelper.setTo(recipient);
            messageHelper.setSubject("Account activation");
            messageHelper.setText(mailContentBuilder.build(msg));
        };

        try{
            javaMailSender.send(mimeMessagePreparator);
        }catch(MailException ex){
            throw new SpringStoreException("Error while sending mail");
        }
    }
}
