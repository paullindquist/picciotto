package dev.paullindquist.picciotto.parse.xml;

import dev.paullindquist.picciotto.converters.WorkbookToPoi;
import dev.paullindquist.picciotto.parse.Parser;
import dev.paullindquist.picciotto.parse.css.CSSParser;
import dev.paullindquist.picciotto.structure.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

public class XmlParser implements Parser {
    final Logger logger = LoggerFactory.getLogger(XmlParser.class);
    private CSSParser cssParser;

    private XmlParser() {
    }

    public XmlParser(CSSParser cssParser) {
        this.cssParser = cssParser;
    }

    private List<Column> findColumns(XmlCursor cursor) {
        List<Column> results = new ArrayList<>();
        XmlCursor tempCursor = cursor.newCursor();
        tempCursor.selectPath("column");
        while (tempCursor.toNextSelection()) {
            Integer width = 0;
            String widthAttribute = tempCursor.getAttributeText(QNames.WIDTH);
            if (widthAttribute != null) {
                width = Integer.parseInt(widthAttribute);
            }
            Column column = Column.builder()
                .width(width).build();
            results.add(column);
        }
        tempCursor.dispose();
        return results;
    }

    private List<Row> findRows(XmlCursor cursor) {
        List<Row> results = new ArrayList<>();
        XmlCursor tempCursor = cursor.newCursor();
        tempCursor.selectPath("row");
        while (tempCursor.toNextSelection()) {
            tempCursor.push();
            List<Cell> cells = new ArrayList<>();
            if (tempCursor.toChild(QNames.CELL)) {
                CellParser cellParser = new CellParser(cssParser);
                do {
                    cells.add(cellParser.parse(tempCursor.xmlText()).orElse(CellParser.ERROR_CELL));
                } while (cursor.toNextSibling(QNames.CELL));
            }
            tempCursor.pop();
            Row row = Row.builder()
                .cells(cells)
                .build();
            results.add(row);
        }
        tempCursor.dispose();
        return results;
    }

    private Optional<Workbook> doParse(String xml) {
        XmlCursor cursor = null;
        Workbook workbook = null;
        try {
            cursor = XmlObject.Factory.parse(xml).newCursor();
        } catch (XmlException e) {
            logger.error(e.getMessage());
        }
        if (cursor != null) {
            String name;
            cursor.selectPath("/workbook");
            if (cursor.hasNextSelection()) {
                Set<Sheet> sheets = new HashSet<>();
                cursor.toNextSelection();
                cursor.push();
                name = cursor.getAttributeText(QNames.NAME);
                if (name == null) {
                    if (cursor.toChild(QNames.NAME)) {
                        name = cursor.getTextValue();
                    } else {
                        name = Workbook.DEFAULT_SHEET_NAME;
                    }
                }
                cursor.pop();
                cursor.selectPath("sheet");
                if (cursor.hasNextSelection()) {
                    while (cursor.toNextSelection()) {
                        name = cursor.getAttributeText(QNames.NAME);
                        if (name == null) {
                            if (cursor.toChild(QNames.NAME)) {
                                name = cursor.getTextValue();
                            } else {
                                name = Workbook.DEFAULT_SHEET_NAME;
                            }
                        }
                        List<Row> rows = findRows(cursor);
                        List<Column> columns = findColumns(cursor);
                        Sheet sheet = Sheet.builder()
                            .name(name)
                            .rows(rows)
                            .columns(columns)
                            .build();
                        sheets.add(sheet);
                    }
                }
                workbook = Workbook.builder()
                    .name(name)
                    .sheets(sheets)
                    .build();
            }
        }
        if (workbook != null) {
            return Optional.of(workbook);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Workbook> parse(String content) {
        return doParse(content);
    }

    public void parseToBAIS(String content, ByteArrayOutputStream stream) throws IOException {
        Optional<Workbook> parsed = doParse(content);
        if (parsed.isPresent()) {
            Workbook workbook = parsed.get();
            WorkbookToPoi workbookToPoi = new WorkbookToPoi();
            XSSFWorkbook converted = workbookToPoi.workbookToXSSFWorkbook(workbook);
            converted.write(stream);
            converted.close();
        }
    }
}
