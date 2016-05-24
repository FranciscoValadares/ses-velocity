package com.easikoglu.ses.service.template;



import com.easikoglu.ses.model.EmailTemplate;
import com.easikoglu.ses.util.EmailProperties;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by easikoglu on 04/05/16.
 */
public interface EmailTemplateService {

    String getTranslatedTemplate(EmailTemplate emailTemplate, EmailProperties emailProperties) throws InvocationTargetException, IllegalAccessException;

    EmailTemplate findByEmailType(String emailType);
}
