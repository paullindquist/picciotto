package de.frontsy.picciotto.parse.xml;

import de.frontsy.picciotto.parse.css.CSSParser;
import de.frontsy.picciotto.parse.css.PHCSSParser;
import de.frontsy.picciotto.structure.Row;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RowParserTest {

    private RowParser rowParser;


    @BeforeEach
    void setUp() {
        CSSParser cssParser = new PHCSSParser();
        CellParser cellParser =
            CellParser.builder()
            .cssParser(cssParser)
            .build();

        rowParser = RowParser
            .builder()
            .cellParser(cellParser)
            .build();
    }

    @Test
    void parse() {
        String rowString = "<row><cell></cell></row>";
        Optional<Row> row = rowParser.parse(rowString);
        assertTrue(row.isPresent());
    }
    @Test
    void parseShouldFindACell() {
        String rowString = "<row><cell></cell></row>";
        Row row = rowParser.parse(rowString).orElseThrow();
        assertEquals(1, row.getCells().size());
    }
    @Test
    void parseShouldFindTwoCells() {
        String rowString = "<row><cell></cell><cell></cell></row>";
        Row row = rowParser.parse(rowString).orElseThrow();
        assertEquals(2, row.getCells().size());
    }
    @Test
    void parseShouldFindCellIndexZeroForFirstCell() {
        String rowString = "<row><cell></cell></row>";
        Row row = rowParser.parse(rowString).orElseThrow();
        assertEquals(0, row.getCells().get(0).getIndex());
    }
    @Test
    void parseShouldFindCellIndexOneForSecondCell() {
        String rowString = "<row><cell></cell><cell></cell></row>";
        Row row = rowParser.parse(rowString).orElseThrow();
        assertEquals(1, row.getCells().get(1).getIndex());
    }
}