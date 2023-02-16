import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class Send {
    public Send() {}

    public Session emailsettings(){
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("freezgeXd@gmail.com", "utceurugzgsizpvx");
            }

        });
        session.setDebug(false);
        return session;
    }
    public void emailsender(Session session, Account c){
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("freezgeXd@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(c.getLogin()));
            message.setSubject("2Factor Auth!");
            message.setText("Your code is " + c.getCode());
            System.out.println("sending message...");
            Transport.send(message);
            System.out.println("Message was sent successfully....");
        } catch (MessagingException mex) {
            System.out.println(" ");
        }
    }

}
