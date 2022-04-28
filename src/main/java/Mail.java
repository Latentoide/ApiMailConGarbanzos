import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Logger;

public class Mail {
    public static void senMail(String recepient) throws Exception{
        System.out.println("Preparandome para enviar el mensaje");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        final String myAcEmail = "alejandro.frndo@gmail.com";
        final String password = "Seventyn_7";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAcEmail, password);
            }
        });

        Message message = prepareMessage(session, myAcEmail, recepient);

        Transport.send(message);
        System.out.println("Mensaje mandado perfectamente");
    }

    private static Message prepareMessage(Session session, String myAcEmail, String recipient){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAcEmail));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
            message.setSubject("Mi primer email desde java app");
            message.setText("Buenos dias, \n Este es mi email de prueba!");
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
