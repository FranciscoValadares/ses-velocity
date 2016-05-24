package com.easikoglu.ses.repository;


import com.easikoglu.ses.model.EmailTemplate;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by easikoglu on 05/05/16.
 */
public interface EmailTemplateRepository extends PagingAndSortingRepository<EmailTemplate, String> {
    EmailTemplate findByEmailType(String emailType);
}
