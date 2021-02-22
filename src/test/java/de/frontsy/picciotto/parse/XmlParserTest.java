package de.frontsy.picciotto.parse;

import de.frontsy.picciotto.convert.poi.cell.style.Background;
import de.frontsy.picciotto.convert.poi.cell.style.Border;
import de.frontsy.picciotto.convert.poi.cell.style.PoiStyle;
import de.frontsy.picciotto.parse.css.PHCSSParser;
import de.frontsy.picciotto.parse.xml.CellParser;
import de.frontsy.picciotto.parse.xml.RowParser;
import de.frontsy.picciotto.parse.xml.XmlParser;
import de.frontsy.picciotto.structure.Cell;
import de.frontsy.picciotto.structure.Row;
import de.frontsy.picciotto.structure.Sheet;
import de.frontsy.picciotto.structure.Workbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class XmlParserTest {

    private XmlParser parser;
    private final String HELLO_WORLD = "hello world";
    private final String FOO = "foo";
    private final String BAR = "bar";

    @BeforeEach
    private void before() {
        CellParser cellParser = CellParser
                .builder()
                .cssParser(new PHCSSParser())
                .build();
        RowParser rowParser = RowParser
                .builder()
                .cellParser(cellParser)
                .build();
        parser = XmlParser
                .builder()
                .rowParser(rowParser)
                .build();
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

    @Test
    public void shouldFindCellWidthStyle() {
        Optional<Workbook> parsed = parser.parse("<workbook><name>" + HELLO_WORLD + "</name>" +
                "<sheet name=\"" + FOO + "\">" +
                "<row><cell style='background:#dedede'>" + BAR + "</cell></row>" +
                "</sheet></workbook>");
        String found;
        if (parsed.isPresent()) {
            Set<Sheet> sheets = parsed.get().getSheets();
            for (Sheet sheet : sheets) {
                for (Row row : sheet.getRows()) {
                    for (Cell cell : row.getCells()) {
                        for (PoiStyle style : cell.getStyles().values()) {
                            if (style instanceof Background) {
                                found = ((Background) style).getColor();
                                assertEquals(found, "#dedede");
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    public void shouldFindCellStyle() {
        Optional<Workbook> parsed = parser.parse("<workbook><name>" + HELLO_WORLD + "</name>" +
                "<sheet name=\"" + FOO + "\">" +
                "<row><cell style='background:#dedede;border: solid thin red'>" + BAR + "</cell></row>" +
                "</sheet></workbook>");
        String found;
        if (parsed.isPresent()) {
            Set<Sheet> sheets = parsed.get().getSheets();
            for (Sheet sheet : sheets) {
                for (Row row : sheet.getRows()) {
                    for (Cell cell : row.getCells()) {
                        for (PoiStyle style : cell.getStyles().values()) {
                            if (style instanceof Background) {
                                found = ((Background) style).getColor();
                                assertEquals(found, "#dedede");
                            }
                            if (style instanceof Border) {
                                found = ((Border) style).getColor();
                                assertEquals(found, "#ff0000");
                            }
                        }
                    }
                }
            }
        }
    }
}
