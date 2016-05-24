package com.easikoglu.ses.service.aws.impl;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;
import com.easikoglu.ses.model.EmailTemplate;
import com.easikoglu.ses.service.aws.EmailService;
import com.easikoglu.ses.service.template.EmailTemplateService;
import com.easikoglu.ses.util.AwsPropertiesReader;
import com.easikoglu.ses.util.EmailProperties;
import com.easikoglu.ses.util.EmailType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.Properties;

/**
 * Created by easikoglu on 04/05/16.
 */
@Service
@Profile("prod")
public class AWSEmailServiceImpl implements EmailService {

    @Autowired
    private EmailTemplateService emailTemplateService;



    private static Regions AWS_REGION = Regions.EU_WEST_1;
    private static InputStream stream;
    private static PropertiesCredentials credentials;
    private static AmazonSimpleEmailServiceClient awsClient;

    private static Region awsRegion;


    static {

        stream = AwsPropertiesReader.getInstance().getStream();

        try {
            credentials = new PropertiesCredentials(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Instantiate an Amazon SES client, which will make the service call with the supplied AWS credentials.
        awsClient = new AmazonSimpleEmailServiceClient(credentials);
        awsRegion = Region.getRegion(AWS_REGION);
        awsClient.setRegion(awsRegion);
    }


    private Boolean sendEmail(EmailTemplate template, EmailProperties emailProperties) {

        Session session = Session.getDefaultInstance(new Properties());

        try {
            MimeMessage message = new MimeMessage(session);

            message.setSubject(template.getMailSubject(), "UTF-8");


            message.setFrom(new InternetAddress(template.getMailFrom(),template.getCompanyName()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailProperties.getToEmail()));
            // Cover wrap
            MimeBodyPart wrap = new MimeBodyPart();

            // Alternative TEXT/HTML content
            MimeMultipart cover = new MimeMultipart("alternative");
            MimeBodyPart html = new MimeBodyPart();
            cover.addBodyPart(html);

            wrap.setContent(cover);

            MimeMultipart content = new MimeMultipart("related");
            message.setContent(content);
            content.addBodyPart(wrap);

            final String translatedTemplate = emailTemplateService.getTranslatedTemplate(template, emailProperties);


            html.setContent(translatedTemplate, "text/html; charset=UTF-8");


            try {
                System.out.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");

                // Send the email.
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                message.writeTo(outputStream);
                RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));

                SendRawEmailRequest rawEmailRequest = new SendRawEmailRequest(rawMessage);
                awsClient.sendRawEmail(rawEmailRequest);
                System.out.println("Email sent!");
                return true;
            } catch (Exception ex) {
                //try again
                ex.printStackTrace();

            }
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;


    }

    @Override
    @Async
    public void sendEmail(EmailType emailType, EmailProperties emailProperties) {
        sendEmail(emailTemplateService.findByEmailType(emailType.toString()), emailProperties);
    }

    @Override
    public boolean sendEmail(EmailProperties emailProperties, EmailType emailType) {
        return sendEmail(emailTemplateService.findByEmailType(emailType.toString()), emailProperties);
    }

}
