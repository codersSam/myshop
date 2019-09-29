package com.senhua.my.shop.commons.Utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2019/5/29.
 */
public class EmailSendUtils {

    @Autowired
    private Email email;

    public void send(String subject,String msg,String...to) throws EmailException {
        email.setSubject(subject);
        email.setMsg(msg);
        email.addTo(to);
        email.send();
    }
}
