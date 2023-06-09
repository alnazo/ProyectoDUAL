package com.dual.proyectoDUAL.email;

import com.dual.proyectoDUAL.mail.Sender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SenderTest {

    @Mock
    private Session session;

    @InjectMocks
    private Sender sender;

    @Captor
    private ArgumentCaptor<MimeMessage> messageCaptor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSend() throws IOException, MessagingException, FileNotFoundException {
        String from = "sender@example.com";
        String to = "recipient@example.com";
        String subject = "Test Subject";
        String emailText = "Test Email";
        String content = "proyecto.pdf";

        // Mocking the Transport class
        Transport transport = mock(Transport.class);
        when(session.getTransport((String) any())).thenReturn(transport);

        // Invoke the send method
        boolean result = Boolean.parseBoolean(sender.send(from, to, subject, emailText, content));

        // Verify that the message was sent
        assertTrue(result);
        verify(transport).sendMessage(messageCaptor.capture(), any());

        // Get the captured message
        MimeMessage capturedMessage = messageCaptor.getValue();
        assertNotNull(capturedMessage);
        assertEquals(from, capturedMessage.getFrom()[0].toString());
        assertEquals(to, capturedMessage.getRecipients(Message.RecipientType.TO)[0].toString());
        assertEquals(subject, capturedMessage.getSubject());

        // Verify that the message content is correct
        assertTrue(capturedMessage.getContent() instanceof MimeMultipart);
        MimeMultipart multipart = (MimeMultipart) capturedMessage.getContent();
        assertEquals(2, multipart.getCount());

        BodyPart textPart = multipart.getBodyPart(0);
        assertEquals("text/html; charset=utf-8", textPart.getContentType());
        assertEquals(emailText, textPart.getContent());

        BodyPart filePart = multipart.getBodyPart(1);
        assertTrue(filePart.getContent() instanceof MimeBodyPart);
        MimeBodyPart fileBodyPart = (MimeBodyPart) filePart.getContent();
        assertEquals(content, fileBodyPart.getDataHandler().getContent());
        assertEquals("proyecto.pdf", fileBodyPart.getFileName());
    }

}
