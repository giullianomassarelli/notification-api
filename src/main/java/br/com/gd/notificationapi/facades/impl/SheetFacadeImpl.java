package br.com.gd.notificationapi.facades.impl;

import br.com.gd.notificationapi.dtos.responses.SheetResponseDTO;
import br.com.gd.notificationapi.entities.SheetEntity;
import br.com.gd.notificationapi.exceptions.SheetException;
import br.com.gd.notificationapi.exceptions.enums.SheetEnum;
import br.com.gd.notificationapi.facades.SheetFacade;
import br.com.gd.notificationapi.services.NotificationService;
import br.com.gd.notificationapi.services.SheetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
@Component
@Slf4j
public class SheetFacadeImpl implements SheetFacade {

    @Autowired
    private SheetService sheetService;

    @Autowired
    private NotificationService notificationService;


    @Override
    public SheetResponseDTO importSheet(MultipartFile file) {
        if (sheetService.validateExcelFile(file)) {
            return importSheetAndSendEmail(file);
        } else {
            return new SheetResponseDTO(false);
        }
    }

    @Override
    public List<SheetEntity> getAll() {
        return sheetService.getAll();
    }

    @Override
    public void delete() {
        sheetService.delete();
    }

    private SheetResponseDTO importSheetAndSendEmail (MultipartFile file) {
        SheetResponseDTO sheetResponseDTO = new SheetResponseDTO(false);

        try {
            List<SheetEntity> sheetEntityList = sheetService.importSheet(file.getInputStream());
            for (SheetEntity sheetEntity : sheetEntityList) {
                double amount = sheetEntity.getAmount().doubleValue();
                if (amount < 0) {
                    notificationService.sendEmail(sheetEntity);
                }
            }

            sheetResponseDTO.setSuccess(true);

            return sheetResponseDTO;

        } catch (IOException e) {
            throw new SheetException(SheetEnum.ERROR_WHEN_IMPORT_SHEET);
        }
    }
}
