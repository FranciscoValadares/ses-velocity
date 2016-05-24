package com.easikoglu.ses.service.aws.impl.mock;


import com.easikoglu.ses.service.aws.EmailService;
import com.easikoglu.ses.util.EmailProperties;
import com.easikoglu.ses.util.EmailType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by easikoglu on 12/05/16.
 */
@Service
@Profile(value = "test")
public class MockEmailServiceImpl implements EmailService {

    private static final Log LOG = LogFactory.getLog(MockEmailServiceImpl.class);



    @Override
    public void sendEmail(EmailType emailType, EmailProperties emailProperties) {
        LOG.info("test mail service");
    }

    @Override
    public boolean sendEmail(EmailProperties emailProperties, EmailType emailType) {
        LOG.info("test mail service");
        return true;
    }


}
