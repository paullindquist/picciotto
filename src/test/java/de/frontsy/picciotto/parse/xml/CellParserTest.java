package de.frontsy.picciotto.parse.xml;

import de.frontsy.picciotto.parse.css.CSSParser;
import de.frontsy.picciotto.parse.css.PHCSSParser;
import de.frontsy.picciotto.structure.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CellParserTest {

    private CSSParser cssParser;
    private CellParser cellParser;

    @BeforeEach
    void setUp() {
        cssParser = new PHCSSParser();
        cellParser = new CellParser(cssParser);
    }

    @Test
    void shouldParseEmptyCell() {
        String xml = "<cell></cell>";
        Optional<Cell> cell = cellParser.parse(xml);
        assertTrue(cell.isPresent());
        assertEquals("", cell.get().getValue());
    }

    @Test
    void shouldReturnEmptyOptionForInvalidCellXml() {
        String xml = "<cell>";
        Optional<Cell> cell = cellParser.parse(xml);
        assertFalse(cell.isPresent());
    }

    @Test
    void shouldFindCellValue() {
        String value = "hello world";
        String xml = "<cell>" + value + "</cell>";
        Optional<Cell> cell = cellParser.parse(xml);
        assertTrue(cell.isPresent());
        assertEquals(value, cell.get().getValue());
    }

    @Test
    void shouldFindColspanValue() {
        int colspan = 2;
        String xml = "<cell colspan='"+ colspan + "'></cell>";
        Optional<Cell> cell = cellParser.parse(xml);
        assertTrue(cell.isPresent());
        assertEquals(colspan, cell.get().getColspan());
    }
    @Test
    void shouldFindRowspanValue() {
        int rowspan = 15;
        String xml = "<cell rowspan='"+ rowspan + "'></cell>";
        Optional<Cell> cell = cellParser.parse(xml);
        assertTrue(cell.isPresent());
        assertEquals(rowspan, cell.get().getRowspan());
    }

    @Test
    void shouldDefaultFontFamilyToCalibri() {
        String calibri = "Calibri";
        String xml = "<cell style='font-weight:bold;color:green'>green text</cell>";
        Optional<Cell> cell = cellParser.parse(xml);
        assertTrue(cell.isPresent());
        // TODO: This!!
    }

}
