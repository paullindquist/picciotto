package dev.paullindquist.picciotto.converters.poi.cell;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RangeTest {

    @Test
    public void shouldReturnDefaults() {
        Range range = Range
            .builder()
            .cellIndex(0)
            .rowIndex(0)
            .build();
        assertEquals(1, range.getColSpan());
        assertEquals(1, range.getRowSpan());
        assertEquals(0, range.getLastColumn());
        assertEquals(0, range.getLastRow());
    }

    @Test
    public void colSpanShouldAddToLastColumn() {
        Range range = Range
            .builder()
            .cellIndex(0)
            .rowIndex(0)
            .colSpan(3)
            .build();
        assertEquals(2, range.getLastColumn());
    }

    @Test
    public void rowSpanShouldAddToLastRow() {
        Range range = Range
            .builder()
            .cellIndex(0)
            .rowIndex(9)
            .rowSpan(5)
            .build();
        assertEquals( 13, range.getLastRow());
    }

}
