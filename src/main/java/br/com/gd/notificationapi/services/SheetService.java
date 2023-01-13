package br.com.gd.notificationapi.services;

import br.com.gd.notificationapi.entities.SheetEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface SheetService {

    List<SheetEntity> importSheet (InputStream inputStream);

    boolean validateExcelFile (MultipartFile file);
    SheetEntity save (SheetEntity sheetEntity);

    List<SheetEntity> getAll ();

    void delete ();

}
