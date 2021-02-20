package de.frontsy.picciotto.parse.xml;

import de.frontsy.picciotto.structure.Cell;
import de.frontsy.picciotto.structure.Row;
import de.frontsy.picciotto.structure.Workbook;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Builder
@Slf4j
public class RowParser {
    private CellParser cellParser;

    public Optional<Row> parse(String text) {
        Row row = null;
        XmlCursor cursor = null;
        try {
            cursor = XmlObject.Factory.parse(text).newCursor();
        } catch (XmlException e) {
            log.error(e.getMessage());
        }
        cursor.selectPath("//" + Row.NAME);
        int rowIndex = 0;
        while (cursor.toNextSelection()) {
            List<Cell> cells = new ArrayList<>();
            if (cursor.toChild(QNames.CELL)) {
                do {
                    Optional<Cell> cell = cellParser.parse(cursor.xmlText());
                    if (cell.isPresent()) {
                        cells.add(cell.orElseThrow());
                    }
                } while (cursor.toNextSibling(QNames.CELL));
            }
            row = Row
                .builder()
                .cells(cells)
                .build();
        }
        if (row != null) {
            return Optional.of(row);
        }
        return Optional.empty();
    }
}