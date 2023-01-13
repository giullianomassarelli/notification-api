package br.com.gd.notificationapi.services.impl;

import br.com.gd.notificationapi.entities.SheetEntity;
import br.com.gd.notificationapi.exceptions.SheetException;
import br.com.gd.notificationapi.exceptions.enums.SheetEnum;
import br.com.gd.notificationapi.repositories.SheetRepository;
import br.com.gd.notificationapi.services.SheetService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class SheetServiceImpl implements SheetService {

    @Autowired
    private SheetRepository sheetRepository;

    @Override
    public List<SheetEntity> importSheet(InputStream inputStream)  {
        log.info("import new excel file : {}", inputStream.toString());
        List<SheetEntity> sheetEntityList = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("balance"); //sheet name in Excel file

            int rowIndex = 0;

            for (Row row : sheet){
                if (rowIndex == 0) {
                    rowIndex ++;
                    continue;
                }

                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                SheetEntity sheetEntity = new SheetEntity();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case 0 -> sheetEntity.setMonth(cell.getStringCellValue());
                        case 1 -> sheetEntity.setInput(BigDecimal.valueOf(cell.getNumericCellValue()));
                        case 2 -> sheetEntity.setOutput(BigDecimal.valueOf(cell.getNumericCellValue()));
                        case 3 -> sheetEntity.setAmount(BigDecimal.valueOf(cell.getNumericCellValue()));
                        default -> {
                        }
                    }
                    cellIndex ++;
                }
                sheetEntityList.add(sheetRepository.save(sheetEntity));
            }
        } catch (IOException e) {
            throw new SheetException(SheetEnum.ERROR_WHEN_IMPORT_SHEET);
        }
        return sheetEntityList;
    }

    @Override
    public boolean validateExcelFile(MultipartFile file) {
        log.info("verify if file is a valid file: {}", file.toString());
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
    }

    @Override
    public SheetEntity save(SheetEntity sheetEntity) {
        log.info("save informations in DB : {}", sheetEntity);
        return sheetRepository.save(sheetEntity);
    }

    @Override
    public List<SheetEntity> getAll() {
        log.info("find all informations in DB");
        return sheetRepository.findAll();
    }

    @Override
    public void delete() {
        log.info("delete all informations in DB");
        sheetRepository.deleteAll();
    }

}
