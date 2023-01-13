package br.com.gd.notificationapi.services.impl;

import br.com.gd.notificationapi.entities.SheetEntity;
import br.com.gd.notificationapi.exceptions.NotificationException;
import br.com.gd.notificationapi.exceptions.enums.NotificationEnum;
import br.com.gd.notificationapi.services.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void sendEmail(SheetEntity sheetEntity) {

            SimpleEmail email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator("gm.dev2022@gmail.com", "jgntapazcnvswuwe"));
            email.setSSLOnConnect(true);

        try {
            email.addTo("massarelli47@gmail.com");
            email.setFrom("gm.dev2002@gmail.com");
            email.setSubject("Balanço anual - Gestão do mês : " + sheetEntity.getMonth());
            email.setMsg("O balanço do mês de " + sheetEntity.getMonth() + " ficou com saldo negativo de : " + sheetEntity.getAmount() + " R$");

            email.send();
            log.info("email successfully sent");

        } catch (EmailException e) {
            throw new NotificationException(NotificationEnum.ERROR_TO_SEND_EMAIL);
        }
    }

}

