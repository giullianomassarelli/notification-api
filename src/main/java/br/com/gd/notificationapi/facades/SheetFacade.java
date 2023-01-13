package br.com.gd.notificationapi.facades;

import br.com.gd.notificationapi.dtos.responses.SheetResponseDTO;
import br.com.gd.notificationapi.entities.SheetEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SheetFacade {

    SheetResponseDTO importSheet (MultipartFile file);
    List<SheetEntity> getAll ();

    void delete();
}
