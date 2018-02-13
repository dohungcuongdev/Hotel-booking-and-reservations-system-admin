/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statics.helper;

import java.util.Date;
import java.util.Properties;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import statics.constant.AppData;

/**
 *
 * @author Do Hung Cuong
 */
public class EmailSender {

    public static String sendEmail(String message, String sendto, String subject) {
        if(message == null || message.equals("") || subject == null || subject.equals("")) {
            return AppData.EMAIL_NO_SUBJECT_MES;
        }
        String from = AppData.EMAIL;
        String login = AppData.EMAIL;
        String authentication = AppData.AUTHENTICATION;
        try {
            Properties props = new Properties();
            props.setProperty("mail.host", AppData.MAIL_HOST);
            props.setProperty("mail.smtp.port", AppData.MAIL_SMTP_PORT);
            props.setProperty("mail.smtp.auth", AppData.MAIL_SMTP_AUTH);
            props.setProperty("mail.smtp.starttls.enable", AppData.MAIL_SMTP_STARTTLS_ENABLE);
            props.put("mail.smtp.ssl.trust", AppData.MAIL_SMTP_SSS_TRUST);
            Authenticator auth = new SMTPAuthenticator(login, authentication);
            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);
            msg.setText(message);
            msg.setSubject(subject);
            msg.setFrom(new InternetAddress(from));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(sendto));
            Transport.send(msg);
        } catch (AuthenticationFailedException ex) {
            return AppData.AUTH_FAIL;
        } catch (AddressException ex) {
            return AppData.WRONG_EMAIL_ADDRESS;
        } catch (MessagingException ex) {
            return AppData.ERROR_MESSAGE;
        }
        return AppData.EMAIL_SENT;
    }
    
    public static void sendHTMLEmail(String message, String sendto, String subject) {
        if(message == null || message.equals("") || subject == null || subject.equals("")) {
            System.out.println("not enough information to send email");
        } else {
            String from = AppData.EMAIL;
            String login = AppData.EMAIL;
            String authentication = AppData.AUTHENTICATION;
            try {
                Properties props = new Properties();
                props.setProperty("mail.host", AppData.MAIL_HOST);
                props.setProperty("mail.smtp.port", AppData.MAIL_SMTP_PORT);
                props.setProperty("mail.smtp.auth", AppData.MAIL_SMTP_AUTH);
                props.setProperty("mail.smtp.starttls.enable", AppData.MAIL_SMTP_STARTTLS_ENABLE);
                props.put("mail.smtp.ssl.trust", AppData.MAIL_SMTP_SSS_TRUST);
                Authenticator auth = new SMTPAuthenticator(login, authentication);
                Session session = Session.getInstance(props, auth);
                MimeMessage msg = new MimeMessage(session);
                msg.setContent(message, "text/html; charset=utf-8");
                msg.setSentDate(new Date());
                msg.setSubject(subject);
                msg.setFrom(new InternetAddress(from));
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(sendto));
                Transport.send(msg);
            } catch (AuthenticationFailedException ex) {
            	ex.printStackTrace();
            } catch (AddressException ex) {
            	ex.printStackTrace();
            } catch (MessagingException ex) {
            	ex.printStackTrace();
            }
        }
    }

    private static class SMTPAuthenticator extends Authenticator {

        private final PasswordAuthentication authentication;

        public SMTPAuthenticator(String login, String password) {
            authentication = new PasswordAuthentication(login, password);
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return authentication;
        }
    }
}
