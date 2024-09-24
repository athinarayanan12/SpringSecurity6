package com.example;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.util.Map;

public class EmailSenderLambda implements RequestHandler<Map<String, String>, String> {

    @Override
    public String handleRequest(Map<String, String> event, Context context) {
        // Extract email details from the event (input to the Lambda)
        String to = event.get("to");
        String subject = event.get("subject");
        String body = event.get("body");

        // Setting up properties for Gmail SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Your Gmail credentials (Use environment variables for security reasons)
        final String myAccountEmail = "athinarayanang12@gmail.com";  // Set in Lambda environment variables
        final String password = "wzrewejdzyqcmjdj";  // Set in Lambda environment variables

        // Authenticating
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        try {
            // Creating a MimeMessage object
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(body);

            // Sending the email
            Transport.send(message);
            return "Email sent successfully!";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Failed to send email: " + e.getMessage();
        }
    }
}
