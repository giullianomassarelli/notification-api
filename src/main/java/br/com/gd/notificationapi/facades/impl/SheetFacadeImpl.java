package br.com.gd.notificationapi.facades.impl;

import br.com.gd.notificationapi.dtos.responses.ImportResponseDTO;
import br.com.gd.notificationapi.dtos.responses.SheetResponseDTO;
import br.com.gd.notificationapi.entities.SheetEntity;
import br.com.gd.notificationapi.exceptions.SheetException;
import br.com.gd.notificationapi.exceptions.enums.SheetEnum;
import br.com.gd.notificationapi.facades.SheetFacade;
import br.com.gd.notificationapi.mappers.SheetMapper;
import br.com.gd.notificationapi.services.NotificationService;
import br.com.gd.notificationapi.services.SheetService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
@Component
@Slf4j
public class SheetFacadeImpl implements SheetFacade {

    @Autowired
    private SheetService sheetService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SheetMapper sheetMapper;

    private final String SHEET_NAME = "balance" ; //sheet name in Excel file

    @Override
    public ImportResponseDTO importFile (MultipartFile file) throws IOException {
        log.info("try import file");
        verifyFile(file);
        return new ImportResponseDTO(importSheet(file.getInputStream()));
    }

    @Override
    public List<SheetResponseDTO> getAll() {
        return sheetMapper.toDtoList(sheetService.getAll());
    }

    @Override
    public void delete() {
        sheetService.delete();
    }

    public void verifyFile(MultipartFile file) {
        if(sheetService.validateExcelFile(file)) {
            log.info("correct file");
        }else {
            log.info("incorrect file");
        }
    }

    public boolean importSheet(InputStream inputStream) {
        log.info("import new sheet");
        boolean status = false;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(SHEET_NAME);

            int rowIndex = 0;

            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }

                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                SheetEntity sheetEntity = new SheetEntity();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 -> sheetEntity.setMonth(cell.getStringCellValue());
                        case 1 -> sheetEntity.setInput(BigDecimal.valueOf(cell.getNumericCellValue()));
                        case 2 -> sheetEntity.setOutput(BigDecimal.valueOf(cell.getNumericCellValue()));
                        case 3 -> sheetEntity.setAmount(BigDecimal.valueOf(cell.getNumericCellValue()));
                        default -> {
                        }
                    }
                    cellIndex++;
                }
                sheetService.save(sheetEntity);
                status = true;
                sendEmail(sheetEntity.getMonth(), sheetEntity.getAmount());

            }

        } catch (IOException e) {
            throw new SheetException(SheetEnum.ERROR_WHEN_IMPORT_SHEET);
        }
        return status;
    }

    public void sendEmail (String month, BigDecimal amount) {
        if (amount.doubleValue() < 0){
            log.info("in mounth: {} had a negative balance, sending email", month);
            notificationService.sendEmail(month, amount);
        }
    }

}


