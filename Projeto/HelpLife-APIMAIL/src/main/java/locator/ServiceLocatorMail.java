
package locator;

import service.MailService;

public class ServiceLocatorMail {
    
    public static MailService getMailService(){
        return new MailService();
    }
    
}
