package com.raptor.services.core;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.raptor.properties.Log;
import com.raptor.properties.RobotProperties;

/**
 * Service use to manage and send/recieve emails
 * @author erik
 *
 */
public class EmailService {
	
	private final static String MAILER_VERSION = "Java";
	
	private static EmailService INSTANCE;
	
	private EmailService(){
	
	}
	
	
	public static EmailService getInstance(){
		if(INSTANCE==null){
			INSTANCE = new EmailService();
		}
		return INSTANCE;
	}
	
	
   public boolean sendMailSMTP(List<String> emails, String object, String content,boolean debug) throws Exception {
	   String serveur = RobotProperties.getInstance().getEmailSMTP();
	   String emailRobot = RobotProperties.getInstance().getEmail();
         boolean result = false;
         try {
              Properties prop = System.getProperties();
              prop.put("mail.smtp.host", serveur);
              Session session = Session.getDefaultInstance(prop,null);
              Message message = new MimeMessage(session);
              message.setFrom(new InternetAddress(emailRobot));
              InternetAddress[] internetAddresses = new InternetAddress[emails.size()];
              int i =0;
              for(String email : emails){
            	  internetAddresses[i] = new InternetAddress(email);
            	  i++;
              }
              message.setRecipients(Message.RecipientType.TO,internetAddresses);
              message.setSubject(object);
              MimeMultipart parts = new MimeMultipart("alternative");
              MimeBodyPart text = new MimeBodyPart();
              MimeBodyPart html = new MimeBodyPart();
              text.setText(content);
              html.setContent(content, "text/html");
              parts.addBodyPart(text);
              parts.addBodyPart(html);
              message.setContent(parts);
              message.setHeader("X-Mailer", MAILER_VERSION);
              message.setSentDate(new Date());
              session.setDebug(debug);
              Transport.send(message);
              result = true;
              Log.getInstance().info("Sending email "+object+" to "+emails.size()+" emails!");
         } catch (AddressException e) {
            Log.getInstance().error("An error occured while sending the mail", e);
             throw e;
         } catch (MessagingException e) {
             Log.getInstance().error("Impossible to send the mail "+object+ " to "+emails.size()+" emails", e);
             throw e;
         }
         return result;
   }
}
