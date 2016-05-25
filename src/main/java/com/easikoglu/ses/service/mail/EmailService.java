package com.easikoglu.ses.service.mail;


import com.easikoglu.ses.util.EmailProperties;
import com.easikoglu.ses.util.EmailType;

public interface EmailService {
    void sendEmail(EmailType emailType, EmailProperties emailProperties);

}
