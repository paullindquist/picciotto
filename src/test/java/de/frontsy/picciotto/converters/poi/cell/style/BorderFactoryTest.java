package de.frontsy.picciotto.converters.poi.cell.style;

import de.frontsy.picciotto.parse.css.Rule;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Map;

import static org.mockito.Mockito.*;

class BorderFactoryTest {

    private BorderStyleFactory borderStyleFactory;

    @BeforeEach
    void setUp() {
        borderStyleFactory = new BorderStyleFactory();
    }

    @Test
    void shouldMakeBorderStyle() {
        Map<String, String> values = Map.of( "border-color", "green");
        Rule rule = Rule
            .builder()
            .property( "border")
            .values( values )
            .build();

        Border borderStyle = (Border) borderStyleFactory.getStyle(rule);

        XSSFCellStyle mockedCellStyle = mock(XSSFCellStyle.class);
        XSSFWorkbook mockedWorkbook = mock(XSSFWorkbook.class);
        borderStyle.setStyle(mockedCellStyle, mockedWorkbook);

        ArgumentCaptor<XSSFColor> argument = ArgumentCaptor.forClass(XSSFColor.class);

        verify(mockedCellStyle, times(1)).setBorderColor(eq( XSSFCellBorder.BorderSide.BOTTOM), argument.capture());
        System.out.println(argument.getValue().getRGB());
    }
}
