package dev.paullindquist.picciotto.parse;

import dev.paullindquist.picciotto.structure.Cell;
import dev.paullindquist.picciotto.structure.Row;
import dev.paullindquist.picciotto.structure.Sheet;
import dev.paullindquist.picciotto.structure.Workbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class XmlParserTest {

    private XmlParser parser;
    private final String HELLO_WORLD = "hello world";
    private final String FOO = "foo";
    private final String BAR = "bar";

    @BeforeEach
    private void before() {
        parser = new XmlParser();
    }

    @Test
    public void shouldFailWhenRootIsNotWorkbook() {
        Optional<Workbook> parsed = parser.parse("<html></html>");
        assertEquals(Optional.empty(), parsed);
    }

    @Test
    public void shouldReturnWorkbookWhenRootIsWorkbook() {
        Optional<Workbook> parsed = parser.parse("<workbook/>");
        assertEquals(false, parsed.isEmpty());
    }

    @Test
    public void shouldFindWorkbookNameInNameAttribute() {
        Optional<Workbook> parsed = parser.parse("<workbook name=\"" + HELLO_WORLD + "\"/>");
        assertEquals(HELLO_WORLD, parsed.get().getName());
    }

    @Test
    public void shouldFindWorkbookNameInNameTag() {
        Optional<Workbook> parsed = parser.parse("<workbook><name>" + HELLO_WORLD + "</name></workbook>");
        assertEquals(HELLO_WORLD, parsed.get().getName());
    }

    @Test
    public void shouldFindASheet() {
        Optional<Workbook> parsed = parser.parse("<workbook><name>" + HELLO_WORLD + "</name><sheet name=\"" + FOO + "\"/></workbook>");
        Set<Sheet> sheets = parsed.get().getSheets();
        assertEquals(1, sheets.size());
    }

    @Test
    public void shouldFindMultipleSheets() {
        Optional<Workbook> parsed = parser.parse("<workbook><name>" + HELLO_WORLD + "</name>" +
            "<sheet name=\"" + FOO + "\"/>" +
            "<sheet name=\"" + BAR + "\"/></workbook>");
        Set<Sheet> sheets = parsed.get().getSheets();
        assertEquals(2, sheets.size());
    }

    @Test
    public void shouldFindSheetRows() {
        Optional<Workbook> parsed = parser.parse("<workbook><name>" + HELLO_WORLD + "</name>" +
            "<sheet name=\"" + FOO + "\">" +
            "<row></row>" +
            "<row></row>" +
            "<row></row>" +
            "</sheet></workbook>");
        Set<Sheet> sheets = parsed.get().getSheets();
        List<Row> rows = new ArrayList<>();
        for (Sheet sheet : sheets) {
            rows = sheet.getRows();
        }
        assertEquals(3, rows.size());

    }

    @Test
    public void shouldFindCellValues() {
        Optional<Workbook> parsed = parser.parse("<workbook><name>" + HELLO_WORLD + "</name>" +
            "<sheet name=\"" + FOO + "\">" +
            "<row><cell>" + BAR + "</cell></row>" +
            "</sheet></workbook>");
        String content = "";
        if (parsed.isPresent()) {
            Set<Sheet> sheets = parsed.get().getSheets();
            for (Sheet sheet : sheets) {
                for (Row row : sheet.getRows()) {
                    for (Cell cell : row.getCells()) {
                        content = cell.getValue();
                    }
                }
            }
        }
        assertEquals(BAR, content);
    }
}
