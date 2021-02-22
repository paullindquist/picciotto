package de.frontsy.picciotto;

import de.frontsy.picciotto.convert.WorkbookToPoi;
import de.frontsy.picciotto.parse.css.CSSParser;
import de.frontsy.picciotto.parse.css.PHCSSParser;
import de.frontsy.picciotto.parse.xml.CellParser;
import de.frontsy.picciotto.parse.xml.RowParser;
import de.frontsy.picciotto.parse.xml.XmlParser;
import de.frontsy.picciotto.structure.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

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
        Optional<Workbook> workbook = parser.parse(xml);
        if (workbook.isPresent()) {
            XSSFWorkbook xssfWorkbook = WorkbookToPoi.workbookToXSSFWorkbook(workbook.orElseThrow());
            xssfWorkbook.write(stream);
        }
        return stream.toByteArray();
    }
}
