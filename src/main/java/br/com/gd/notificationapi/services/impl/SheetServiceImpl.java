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

    private final String VALIDATOR = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";


    @Override
    public boolean validateExcelFile(MultipartFile file) {
        log.info("verify if file is a valid file");
        return Objects.equals(file.getContentType(), VALIDATOR);
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
