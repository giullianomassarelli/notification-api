package br.com.gd.notificationapi.services.impl;

import br.com.gd.notificationapi.exceptions.NotificationException;
import br.com.gd.notificationapi.exceptions.enums.NotificationEnum;
import br.com.gd.notificationapi.services.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Value("${email.smpt-port}")
    private int smptPort;

    @Value("${email.host-name}")
    private String hostName;

    @Value("${email.user}")
    private String user;

    @Value("${email.password}")
    private String password;

    @Value("${email.to}")
    private String to;

    @Value("${email.from}")
    private String from;

    @Value("${email.subject}")
    private String subject;

    private final String START_TEXT = "O balanço do mês de :";
    private final String END_TEXT = " ficou com saldo negativo de : ";

    @Override
    public void sendEmail(String month, BigDecimal amount) {
            SimpleEmail email = new SimpleEmail();
            email.setHostName(hostName);
            email.setSmtpPort(smptPort);
            email.setAuthenticator(new DefaultAuthenticator(user, password));
            email.setSSLOnConnect(true);

        try {
            email.addTo(to);
            email.setFrom(from);
            email.setSubject(subject + " : " + month);
            email.setMsg(START_TEXT + month + END_TEXT + amount + "R$");

            email.send();
            log.info("email successfully sent");

        } catch (EmailException e) {
            throw new NotificationException(NotificationEnum.ERROR_TO_SEND_EMAIL);
        }
    }

}

