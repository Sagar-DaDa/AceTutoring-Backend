package com.acetutoring.api.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@NoArgsConstructor
public class EmailSender {
    @Autowired
    private JavaMailSender mailSender;

    public String sendMail(String toEmail, String subject, String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("sagardada.info@gmail.com");
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
        System.out.println("Email sent successfully.");
        return "Email Sent.";
    }

    public void sendEmailWithAttachment(
            String toEmail,
            String subject,
            String text,
            MultipartFile attachmentFile) {

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(text);

            // Add attachment
//            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            if (attachmentFile != null && !attachmentFile.isEmpty()) {
                helper.addAttachment(
                        attachmentFile.getOriginalFilename()
                        , attachmentFile
                );
            }


            mailSender.send(message);
        } catch (MessagingException | MailException e) {
            e.printStackTrace();
        }
    }
}
