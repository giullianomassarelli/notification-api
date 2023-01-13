package br.com.gd.notificationapi.facades;

import br.com.gd.notificationapi.dtos.responses.SheetResponseDTO;
import br.com.gd.notificationapi.entities.SheetEntity;
import br.com.gd.notificationapi.facades.impl.SheetFacadeImpl;
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

    private final String ID_SHEET = "ID";
    private final String MONTH = "Setember";
    private final BigDecimal INPUT_VALUE = new BigDecimal("10");
    private final BigDecimal OUTPUT_VALUE = new BigDecimal("5");
    private final BigDecimal AMOUNT_VALUE = new BigDecimal("5");

    @Before
    public void setupMock() {
        MockitoAnnotations.openMocks(this);
        when(sheetService.getAll()).thenReturn(returnListSheetEntity());
    }

    @Test
    public void listSheetsMustReturnOk () throws Exception {
        assertEquals(returnListSheetEntity(), sheetFacade.getAll());
    }

    public SheetResponseDTO returnSheetResponseDTOTrue () {
        return new SheetResponseDTO(true);
    }

    public SheetResponseDTO returnSheetResponseDTOFalse () {
        return new SheetResponseDTO(false);
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
