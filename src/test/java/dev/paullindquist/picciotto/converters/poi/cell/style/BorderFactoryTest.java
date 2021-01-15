package dev.paullindquist.picciotto.converters.poi.cell.style;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BorderFactoryTest {

    private BorderStyleFactory borderStyleFactory;

    @BeforeEach
    void setUp() {
        borderStyleFactory = new BorderStyleFactory();
    }

    @Test
    void shouldMakeBorderStyle() {
        /*
        BorderStyle borderStyle = (BorderStyle) borderStyleFactory.getStyle(rule);

        XSSFCellStyle mockedCellStyle = mock(XSSFCellStyle.class);
        borderStyle.setStyle(mockedCellStyle);

        ArgumentCaptor<XSSFColor> argument = ArgumentCaptor.forClass(XSSFColor.class);

        verify(mockedCellStyle, times(1)).setBorderColor(eq(XSSFCellBorder.BorderSide.BOTTOM), argument.capture());
        System.out.println(argument.getValue().getRGB());
         */
    }
}
