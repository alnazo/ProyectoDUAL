package com.dual.proyectoDUAL.email.sender;

import com.dual.proyectoDUAL.email.sender.enums.MailProperties;
import lombok.Getter;
import lombok.Setter;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Sender {

    @Setter
    @Getter
    Properties mailProp = new Properties();

    @Setter
    @Getter
    Properties credentialProp = new Properties();

    public Sender() {
        try {
            mailProp.load(getClass().getClassLoader().getResourceAsStream("mailProperties/mail.properties"));
            credentialProp.load(getClass().getClassLoader().getResourceAsStream("mailProperties/credentials.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean send(String from, String to, String subject, String emailText, String content) throws FileNotFoundException, IOException {

        Session session = createSession();

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);

            // Create the email body
            BodyPart text = new MimeBodyPart();
            text.setContent(emailText, "text/html; charset=utf-8");

            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(content)));
            String[] particiones = content.split("/");
            String adjuntoNombre = particiones[particiones.length - 1];
            adjunto.setFileName(adjuntoNombre);

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(text);
            multipart.addBodyPart(adjunto);

            message.setContent(multipart);
            System.out.println("Enviando...");
            Transport.send(message);
            System.out.println("E-mail enviado correctamente");

            return true;

        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
    }



    private Session createSession() {
        Session session = Session.getInstance(mailProp, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(credentialProp.getProperty(MailProperties.USER.getName()),
                        credentialProp.getProperty(MailProperties.PASSWORD.getName()));
            }
        });

        session.setDebug(true);
        return session;
    }



}