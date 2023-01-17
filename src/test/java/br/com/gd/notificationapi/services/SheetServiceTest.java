package br.com.gd.notificationapi.services;

import br.com.gd.notificationapi.entities.SheetEntity;
import br.com.gd.notificationapi.repositories.SheetRepository;
import br.com.gd.notificationapi.services.impl.SheetServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SheetServiceTest {

    @InjectMocks
    private SheetServiceImpl sheetService;

    @Mock
    private SheetRepository sheetRepository;


    private final String ID_SHEET = "ID";
    private final String MONTH = "Setember";
    private final BigDecimal INPUT_VALUE = new BigDecimal("10");
    private final BigDecimal OUTPUT_VALUE = new BigDecimal("5");
    private final BigDecimal AMOUNT_VALUE = new BigDecimal("5");

    @Before
    public void setupMock () {
        MockitoAnnotations.openMocks(this);
        when(sheetRepository.findAll()).thenReturn(returnListSheetEntity());
        when(sheetRepository.save(returnSheetEntityPositivyAmount())).thenReturn(returnSheetEntityPositivyAmount());
    }

    @Test
    public void listAllSheetsMustReturnOk () throws Exception {
        assertEquals(returnListSheetEntity(), sheetService.getAll());
    }

    @Test
    public void saveSheetMustReturnOk () throws Exception {
        assertEquals(returnSheetEntityPositivyAmount(), sheetService.save(returnSheetEntityPositivyAmount()));
    }

    @Test
    public void validadeExcelFileMustReturnOk () throws Exception {
        assertTrue(sheetService.validateExcelFile(returnValidFile()));
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

    public MockMultipartFile returnValidFile () throws IOException {

        FileInputStream file = new FileInputStream("src/test/resources/test.xls");
        return new MockMultipartFile(
                "test",
                "test.xls",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                file);
    }
}
