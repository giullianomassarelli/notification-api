package br.com.gd.notificationapi.facades;

import br.com.gd.notificationapi.dtos.responses.ImportResponseDTO;
import br.com.gd.notificationapi.entities.SheetEntity;
import br.com.gd.notificationapi.facades.impl.SheetFacadeImpl;
import br.com.gd.notificationapi.services.NotificationService;
import br.com.gd.notificationapi.services.SheetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SheetFacadeImplTest {

    @InjectMocks
    private SheetFacadeImpl sheetFacade;

    @Mock
    private SheetService sheetService;

    @Mock
    private NotificationService notificationService;

    @Autowired
    private WebApplicationContext wContext;

    private final String ID_SHEET = "ID";
    private final String MONTH = "Setember";
    private final BigDecimal INPUT_VALUE = new BigDecimal("10");
    private final BigDecimal OUTPUT_VALUE = new BigDecimal("5");
    private final BigDecimal AMOUNT_VALUE = new BigDecimal("5");

    private final MultipartFile FILE = new MockMultipartFile("test.json", "", "application/json", "{\"key1\": \"value1\"}".getBytes());
    private final InputStream INPUT_STREAM = new ByteArrayInputStream("test data".getBytes());
    @Before
    public void setupMock() throws IOException {
        MockitoAnnotations.openMocks(this);
        //SheetService
        when(sheetService.getAll()).thenReturn(returnListSheetEntity());
        when(sheetService.validateExcelFile(FILE)).thenReturn(true);
      //  when(sheetService.importSheet(INPUT_STREAM)).thenReturn(returnListSheetEntity());

        //NotificationService
       // when(notificationService.sendEmail(returnSheetEntityPositivyAmount())).thenReturn(true);
    }

    @Test
    public void listSheetsMustReturnOk () throws Exception {
        assertEquals(returnListSheetEntity(), sheetFacade.getAll());
    }
    @Test
    public void importSheetMustReturnOk () throws Exception {
        //assertEquals(returnSheetResponseDTOTrue(), sheetFacade.importSheet(FILE));
    }

    @Test
    public void readerSheetMustReturnOk () throws  Exception {
       // assertEquals(returnListSheetEntity(), sheetFacade.readerSheet(INPUT_STREAM));
    }

    @Test
    public void sendEmailMustReturnOk () throws Exception {
        //assertEquals(returnSheetResponseDTOTrue(), sheetFacade.sendEmail(FILE));
    }



    public ImportResponseDTO returnSheetResponseDTOTrue () {
        return new ImportResponseDTO(true);
    }

    public ImportResponseDTO returnSheetResponseDTOFalse () {
        return new ImportResponseDTO(false);
    }

    public SheetEntity returnSheetEntityPositivyAmount() {
        return new SheetEntity(
                ID_SHEET,
                MONTH,
                INPUT_VALUE,
                OUTPUT_VALUE,
                AMOUNT_VALUE
        );
    }

    public SheetEntity returnSheetEntityNegativeAmount() {

        BigDecimal negativeValue = AMOUNT_VALUE;
        negativeValue = negativeValue.negate();

        return new SheetEntity(
                ID_SHEET,
                MONTH,
                INPUT_VALUE,
                OUTPUT_VALUE,
                negativeValue
        );
    }

    public List<SheetEntity> returnListSheetEntity () {
        List<SheetEntity> list = new ArrayList<>();
        list.add(returnSheetEntityPositivyAmount());

        return list;
    }



}
