package send;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

public class SendMail {

    public void envio(Session session, String assunto, String remetentes, String mensagem) throws MessagingException {
    	

        try {
     
          Message message = new MimeMessage(session);
          message.setFrom(new InternetAddress("helplife.tcc.2020@gmail.com")); 
          //Remetente
     
          Address[] toUser = InternetAddress //Destinatário(s)
                     .parse(remetentes);  
     
          message.setRecipients(Message.RecipientType.TO, toUser);
          message.setSubject(assunto);//Assunto
          message.setText(mensagem);
          Transport transport = session.getTransport("smtps");
          transport.connect("smtp.googlemail.com", 465, "helplife.tcc.2020@gmail.com", "helplife2020");
          transport.sendMessage(message, toUser);
          transport.close();
     
          System.out.println("Feito!!!");
     
         } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}
