package de.frontsy.picciotto.convert;

import de.frontsy.picciotto.convert.poi.BorderUtils;
import de.frontsy.picciotto.convert.poi.cell.Range;
import de.frontsy.picciotto.convert.poi.cell.style.PoiStyle;
import de.frontsy.picciotto.structure.Cell;
import de.frontsy.picciotto.structure.Row;
import de.frontsy.picciotto.structure.Sheet;
import de.frontsy.picciotto.structure.Workbook;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.util.Map;


@Slf4j
public class WorkbookToPoi {
    public XSSFWorkbook workbookToXSSFWorkbook(Workbook workbook) {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        for (Sheet sheet : workbook.getSheets()) {
            XSSFSheet xssfSheet = xssfWorkbook.createSheet(sheet.getName());
            int rowIndex = 0;
            for (Row row : sheet.getRows()) {
                XSSFRow xssfRow = xssfSheet.createRow(rowIndex);
                int cellIndex = 0;
                for (Cell cell : row.getCells()) {
                    XSSFCell xssfCell = xssfRow.createCell(cellIndex);
                    xssfCell.setCellValue(cell.getValue());
                    Map<String, PoiStyle> styles = cell.getStyles();
                    XSSFCellStyle cellStyle;
                    if (!styles.isEmpty()) {
                        cellStyle = xssfWorkbook.createCellStyle();
                        for (PoiStyle style : styles.values()) {
                            try {
                                style.setStyle(cellStyle, xssfWorkbook);
                                xssfCell.setCellStyle(cellStyle);
                            } catch (Exception e) {
                                log.error(e.getMessage());
                            }
                        }
                    }
                    doSpans(xssfSheet, rowIndex, cellIndex, cell, xssfCell);
                    cellIndex += 1;
                }
                rowIndex += 1;
            }
        }
        return xssfWorkbook;
    }

    private void doSpans(XSSFSheet xssfSheet, int rowIndex, int cellIndex, Cell cell, XSSFCell xssfCell) {
        int rowSpan = cell.getRowspan();
        int colSpan = cell.getColspan();
        if (rowSpan > 0 || colSpan > 0) {
            Range range = Range
                    .builder()
                    .cellIndex(cellIndex)
                    .rowIndex(rowIndex)
                    .colSpan(colSpan)
                    .rowSpan(rowSpan)
                    .build();
            try {
                if (xssfCell != null) {
                    CellRangeAddress mergedRegion = new CellRangeAddress(rowIndex, range.getLastRow(), cellIndex, range.getLastColumn());
                    xssfSheet.addMergedRegion(mergedRegion);
                    BorderUtils.setBorderTop(xssfSheet, xssfCell, mergedRegion);
                    BorderUtils.setBorderRight(xssfSheet, xssfCell, mergedRegion);
                    /*
                    BorderUtils.setBorderBottom(xssfSheet, xssfCell, mergedRegion);
                    BorderUtils.setBorderLeft(xssfSheet, xssfCell, mergedRegion);
                     */
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }
}
