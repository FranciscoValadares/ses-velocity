package com.easikoglu.ses.service.aws;


import com.easikoglu.ses.util.EmailProperties;
import com.easikoglu.ses.util.EmailType;

public interface EmailService {
    void sendEmail(EmailType emailType, EmailProperties emailProperties);

    boolean sendEmail(EmailProperties emailProperties, EmailType emailType);


}
