
package service;

import connection.ConnectionMailGmail;
import javax.mail.MessagingException;
import javax.mail.Session;
import send.SendMail;

public class MailService {
    
    public void prepararEnvioMail(String assunto, String remetentes, String mensagem) throws MessagingException{
     
        SendMail send = new SendMail();
        ConnectionMailGmail conn = new ConnectionMailGmail();
        Session session = conn.conexao();
        send.envio(session,assunto,remetentes,mensagem);
    }
}
