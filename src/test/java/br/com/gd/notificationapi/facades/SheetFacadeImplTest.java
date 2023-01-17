package br.com.gd.notificationapi.facades;

import br.com.gd.notificationapi.dtos.responses.ImportResponseDTO;
import br.com.gd.notificationapi.dtos.responses.SheetResponseDTO;
import br.com.gd.notificationapi.entities.SheetEntity;
import br.com.gd.notificationapi.facades.impl.SheetFacadeImpl;
import br.com.gd.notificationapi.mappers.SheetMapper;
import br.com.gd.notificationapi.services.NotificationService;
import br.com.gd.notificationapi.services.SheetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SheetFacadeImplTest {

    @InjectMocks
    private SheetFacadeImpl sheetFacade;

    @Mock
    private SheetService sheetService;

    @Mock
    private NotificationService notificationService;

    @Mock
    private SheetMapper sheetMapper;

    private final String ID_SHEET = "ID";
    private final String MONTH = "Setember";
    private final BigDecimal INPUT_VALUE = new BigDecimal("10");
    private final BigDecimal OUTPUT_VALUE = new BigDecimal("5");
    private final BigDecimal AMOUNT_VALUE = new BigDecimal("5");

    @Before
    public void setupMock() throws IOException {
        MockitoAnnotations.openMocks(this);
        when(sheetService.getAll()).thenReturn(returnListSheetEntity());
        when(sheetMapper.toDtoList(returnListSheetEntity())).thenReturn(returnListSheetResponseDTO());
    }

    @Test
    public void listSheetsMustReturnOk () throws Exception {
        assertEquals(returnListSheetResponseDTO(), sheetFacade.getAll());
    }

    @Test
    public void importFileMustReturnOk () throws Exception {
        sendEmail();
        assertEquals(returnImportSheetResponseDTOTrue(), sheetFacade.importFile(returnValidFile()));
    }

    @Test
    public void sendEmailMustReturnOk () throws Exception {
        sendEmail();
    }

    @Test
    public void importSheetMustReturnOk () throws Exception {
        assertTrue(sheetFacade.importSheet(returnValidFile().getInputStream()));
    }


    @Test
    public void deleteMustReturnOk () throws Exception {
        delete();

    }


    public ImportResponseDTO returnImportSheetResponseDTOTrue () {
        return new ImportResponseDTO(true);
    }

    public ImportResponseDTO returnImportSheetResponseDTOFalse () {
        return new ImportResponseDTO(false);
    }

    public SheetEntity returnSheetEntity() {
        return new SheetEntity(
                ID_SHEET,
                MONTH,
                INPUT_VALUE,
                OUTPUT_VALUE,
                AMOUNT_VALUE
        );
    }

    public SheetResponseDTO returnSheetEResponseDTO() {
        return new SheetResponseDTO(
                MONTH,
                INPUT_VALUE,
                OUTPUT_VALUE,
                AMOUNT_VALUE
        );
    }

    public List<SheetEntity> returnListSheetEntity () {
        List<SheetEntity> list = new ArrayList<>();
        list.add(returnSheetEntity());

        return list;
    }

    public List<SheetResponseDTO> returnListSheetResponseDTO () {
        List<SheetResponseDTO> list = new ArrayList<>();
        list.add(returnSheetEResponseDTO());

        return list;
    }

    public MockMultipartFile returnValidFile () throws IOException {

        FileInputStream file = new FileInputStream("src/test/resources/test.xls");
        return new MockMultipartFile(
                "test",
                "test.xls",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                file);
    }

    private void sendEmail () {
        notificationService.sendEmail(MONTH, AMOUNT_VALUE);
        verify(notificationService, timeout(1)).sendEmail(MONTH, AMOUNT_VALUE);
    }

    private void delete () {
        sheetService.delete();
        verify(sheetService, timeout(1)).delete();
    }


}
