package org.example;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class SendHTMLEmail {
    public static void sendEmail(List<Movie> movieList) {

        // Recipient's email ID needs to be mentioned.
        String to = "hayden@avuna.net";

        // Sender's email ID needs to be mentioned
        String from = "EricFletcher3@gmail.com";
        final String username = "EricFletcher3";//change accordingly
        String password = null;//change accordingly
        try {
            password = Files.readAllLines(Paths.get(System.getProperty("user.home"), "password.txt")).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sending email through gmail
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");


        // Get the Session object.
        String finalPassword = password;
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, finalPassword);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Spectrum Theater: Now Playing!");

            // Send the actual HTML message

            VelocityEngine ve = new VelocityEngine();
            ve.init();

            VelocityContext context = new VelocityContext();
            context.put("filmList", movieList);

            Template t = ve.getTemplate("src/main/resources/email_html.vm");

            StringWriter writer = new StringWriter();
            t.merge(context, writer);

            message.setContent(writer.toString(), "text/html");


            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}