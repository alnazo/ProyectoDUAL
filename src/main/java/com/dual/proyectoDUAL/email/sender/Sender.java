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

    public File [] archivosAdjuntos;
    private String nombreArchivos;

    public Sender() {
        try {
            mailProp.load(getClass().getClassLoader().getResourceAsStream("mailProperties/mail.properties"));
            credentialProp.load(getClass().getClassLoader().getResourceAsStream("mailProperties/credentials.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String send(String from, String to, String subject, String emailText, File[] adjuntos) throws FileNotFoundException, IOException {

        Session session = createSession();
        String msg;

        try {

            // Create the email body
            MimeMultipart multipart = new MimeMultipart();
            BodyPart text = new MimeBodyPart();
            text.setContent(emailText, "text/html; charset=utf-8");
            multipart.addBodyPart(text);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(multipart);

            // Agregar archivos adjuntos
            if (adjuntos != null && adjuntos.length > 0) {
                for (File adjunto : adjuntos) {
                    if (adjunto.exists()) {
                        BodyPart adjuntoBodyPart = new MimeBodyPart();
                        adjuntoBodyPart.setDataHandler(new DataHandler(new FileDataSource(adjunto.getAbsolutePath())));
                        adjuntoBodyPart.setFileName(adjunto.getName());
                        multipart.addBodyPart(adjuntoBodyPart);
                    }
                }
            }

            message.setContent(multipart);


            Transport.send(message);

            return msg = "Email enviado correctamente";

        } catch (MessagingException mex) {
            return msg = "Error al enviar el mensaje: " + mex.getMessage();
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