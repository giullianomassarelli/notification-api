package br.com.gd.notificationapi.services;

import java.math.BigDecimal;

public interface NotificationService {

    void sendEmail(String month, BigDecimal amount);
}
