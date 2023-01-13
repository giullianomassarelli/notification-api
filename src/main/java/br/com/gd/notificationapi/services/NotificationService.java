package br.com.gd.notificationapi.services;

import br.com.gd.notificationapi.entities.SheetEntity;

public interface NotificationService {

    void sendEmail(SheetEntity sheetEntity);
}
