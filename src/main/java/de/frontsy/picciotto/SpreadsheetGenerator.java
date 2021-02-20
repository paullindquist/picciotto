package de.frontsy.picciotto;

import de.frontsy.picciotto.parse.css.CSSParser;
import de.frontsy.picciotto.parse.css.PHCSSParser;
import de.frontsy.picciotto.parse.xml.CellParser;
import de.frontsy.picciotto.parse.xml.RowParser;
import de.frontsy.picciotto.parse.xml.XmlParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * The type Spreadsheet generator.
 * Attempts to create an Excel Spreadsheet from XML
 */
public class SpreadsheetGenerator {
    /**
     * Generate byte [ ].
     *
     * @param xml the xml
     * @return the byte [ ]
     * @throws IOException the io exception
     */
    public static byte[] generate(String xml) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        CSSParser cssParser = new PHCSSParser();
        CellParser cellParser = CellParser
                .builder()
                .cssParser(cssParser)
                .build();
        RowParser rowParser = RowParser
                .builder()
                .cellParser(cellParser)
                .build();
        XmlParser parser = XmlParser
                .builder()
                .rowParser(rowParser)
                .build();
        parser.parseToBAIS(xml, stream);
        return stream.toByteArray();
    }
}
