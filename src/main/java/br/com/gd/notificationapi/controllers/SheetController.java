package br.com.gd.notificationapi.controllers;

import br.com.gd.notificationapi.dtos.responses.SheetResponseDTO;
import br.com.gd.notificationapi.entities.SheetEntity;
import br.com.gd.notificationapi.facades.SheetFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sheets")
public class SheetController {

    @Autowired
    private SheetFacade sheetFacade;

    @PostMapping
    public ResponseEntity<SheetResponseDTO> importSheet (@RequestParam("file") MultipartFile file){
        return new ResponseEntity<SheetResponseDTO>(sheetFacade.importSheet(file), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SheetEntity>> getAll (){
        return new ResponseEntity<>(sheetFacade.getAll(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll () {
        sheetFacade.delete();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
