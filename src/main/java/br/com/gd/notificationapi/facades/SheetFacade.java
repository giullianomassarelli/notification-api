package br.com.gd.notificationapi.facades;

import br.com.gd.notificationapi.dtos.responses.ImportResponseDTO;
import br.com.gd.notificationapi.dtos.responses.SheetResponseDTO;
import br.com.gd.notificationapi.entities.SheetEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SheetFacade {

    ImportResponseDTO importFile (MultipartFile file) throws IOException;
    List<SheetResponseDTO> getAll ();

    void delete();
}
