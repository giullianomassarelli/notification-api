package br.com.gd.notificationapi.controllers;

import br.com.gd.notificationapi.dtos.responses.ImportResponseDTO;
import br.com.gd.notificationapi.dtos.responses.SheetResponseDTO;
import br.com.gd.notificationapi.facades.SheetFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = SheetController.class)
@AutoConfigureMockMvc
public class SheetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SheetFacade sheetFacade;

    private final String SHEET_ROUTE = "/api/v1/sheets";
    private final String WRONG_SHEET_ROUTE = "/api/v1/sheeeeeeets";
    private final String ID_SHEET = "ID";
    private final String MONTH = "Setember";
    private final BigDecimal INPUT_VALUE = new BigDecimal("10");
    private final BigDecimal OUTPUT_VALUE = new BigDecimal("5");
    private final BigDecimal AMOUNT_VALUE = new BigDecimal("5");

    @Before
    public void setupMock () throws IOException {
        MockitoAnnotations.openMocks(this);
        when(sheetFacade.getAll()).thenReturn(returnListSheetResponseDTO());
        when(sheetFacade.importFile(any(MultipartFile.class))).thenReturn(returnImportSheetResponseDTOTrue());
    }

    @Test
    public void getAllMustReturnAccepted () throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(get(SHEET_ROUTE))
                .andExpect(status().isAccepted())
                .andExpect(content().json(ow.writeValueAsString(returnListSheetResponseDTO())));
    }

    @Test
    public void getAllWithWrongRouteMustReturnNotFound () throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(get(WRONG_SHEET_ROUTE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void importFileMustReturnIsCreated () throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(multipart(SHEET_ROUTE).file(returnValidFile()))
                .andExpect(status().isCreated())
                .andExpect(content().json(ow.writeValueAsString(returnImportSheetResponseDTOTrue())));
    }

    @Test
    public void importFileWithInvalidFileMustReturnBadRequest () throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(multipart(SHEET_ROUTE).file(returnInvalidFile()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void importFileWithIWrongRouteMustReturnNotFound () throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(multipart(WRONG_SHEET_ROUTE).file(returnValidFile()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteAllMustReturnNoContest () throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(delete(SHEET_ROUTE))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteAllWithWrongRouteMustReturnNotFound () throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(delete(WRONG_SHEET_ROUTE))
                .andExpect(status().isNotFound());
    }

    public List<SheetResponseDTO> returnListSheetResponseDTO () {
        List<SheetResponseDTO> list = new ArrayList<>();
        list.add(returnSheetEResponseDTO());

        return list;
    }

    public SheetResponseDTO returnSheetEResponseDTO() {
        return new SheetResponseDTO(
                MONTH,
                INPUT_VALUE,
                OUTPUT_VALUE,
                AMOUNT_VALUE
        );
    }

    public ImportResponseDTO returnImportSheetResponseDTOTrue () {
        return new ImportResponseDTO(true);
    }

    public ImportResponseDTO returnImportSheetResponseDTOFalse () {
        return new ImportResponseDTO(false);
    }

    public MockMultipartFile returnValidFile () throws IOException {

        FileInputStream file = new FileInputStream("src/test/resources/test.xls");
        return new MockMultipartFile(
                "file",
                "test.xls",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                file);
    }

    public MockMultipartFile returnInvalidFile () throws IOException {
        return new MockMultipartFile(
                "data",
                "filename.txt",
                "text/plain",
                "some xml".getBytes());

    }


}
