package com.easikoglu.ses.service.template.impl;


import com.easikoglu.ses.model.EmailTemplate;
import com.easikoglu.ses.repository.EmailTemplateRepository;
import com.easikoglu.ses.service.template.EmailTemplateService;
import com.easikoglu.ses.util.EmailProperties;
import com.easikoglu.ses.util.EmailUtil;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by easikoglu on 04/05/16.
 */
@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {


    @Autowired
    private VelocityEngine engine;
    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    @Override
    public String getTranslatedTemplate(EmailTemplate emailTemplate, EmailProperties emailProperties) throws InvocationTargetException, IllegalAccessException {


        final Map<String, Object> stringObjectMap = EmailUtil.convertObjectToMap(emailProperties);

        return VelocityEngineUtils.mergeTemplateIntoString(this.engine,
                emailTemplate.getFileName(), "UTF-8", stringObjectMap);
    }

    @Override
    public EmailTemplate findByEmailType(String emailType) {
        return emailTemplateRepository.findByEmailType(emailType);
    }


}
