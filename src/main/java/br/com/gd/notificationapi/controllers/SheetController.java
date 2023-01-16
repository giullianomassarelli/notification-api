package br.com.gd.notificationapi.controllers;

import br.com.gd.notificationapi.dtos.responses.ImportResponseDTO;
import br.com.gd.notificationapi.dtos.responses.SheetResponseDTO;
import br.com.gd.notificationapi.facades.SheetFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sheets")
public class SheetController {

    @Autowired
    private SheetFacade sheetFacade;

    @PostMapping
    public ResponseEntity<ImportResponseDTO> importSheet (@RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(sheetFacade.importFile(file), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SheetResponseDTO>> getAll (){
        return new ResponseEntity<>(sheetFacade.getAll(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll () {
        sheetFacade.delete();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
