/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class ConnectionMailGmail {

    public Session conexao() {
        Properties props = new Properties();

        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", 
        "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.user", "helplife.tcc.2020@gmail.com");
        props.put("mail.smtp.host", "smtp.googlemail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.fallback", "false");
        
        /*SimpleAuth auth = null;
		auth = new SimpleAuth ("nandosrs56@gmail.com", "nando03070");*/
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("helplife.tcc.2020@gmail.com", "helplife2020");
                    }
                });

        session.setDebug(false);
        return session;
    }

}
