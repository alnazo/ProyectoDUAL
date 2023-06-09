package com.dual.proyectoDUAL.mail;

import lombok.Getter;
import lombok.Setter;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Sender {

    @Getter
    @Setter
    Properties mailProp = new Properties();

    public Sender() {
        try {
            mailProp.load(getClass().getClassLoader().getResourceAsStream("mailProperties/mail.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Session createSession() {
        Session session = Session.getInstance(mailProp, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailProp.getProperty(MailConstants.MAIL_USER), mailProp.getProperty(MailConstants.MAIL_PASS));
            }
        });

        return session;
    }

    public String send(String from, String to, String subject, String emailText, String content) throws FileNotFoundException, IOException {

        Session session = createSession();

        try {

            MimeMultipart multipart = new MimeMultipart();
            BodyPart text = new MimeBodyPart();
            text.setContent(emailText, "text/html; charset=utf-8");
            multipart.addBodyPart(text);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(multipart);

            message.setContent(multipart);

            Transport.send(message);

            return "Email enviado correctamente";

        } catch (MessagingException mex) {
            return "Error al enviar el email: " + mex.getMessage();
        }
    }

}
