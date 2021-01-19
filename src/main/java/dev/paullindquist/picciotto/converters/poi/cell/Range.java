package dev.paullindquist.picciotto.converters.poi.cell;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
public class Range {
    private int rowIndex;
    private int cellIndex;
    @Builder.Default
    private int rowSpan = 1;
    @Builder.Default
    private int colSpan = 1;

    public Integer getLastRow() {
        int lastRow;
        if (rowSpan > 1) {
            lastRow = rowIndex + (rowSpan - 1);
        } else {
            lastRow = rowIndex;
        }
        return lastRow;
    }

    public Integer getLastColumn() {
        int lastColumn;
        if (colSpan > 1) {
            lastColumn = cellIndex + (colSpan - 1);
        } else {
            lastColumn = cellIndex;
        }
        return lastColumn;
    }
}
