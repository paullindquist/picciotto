package de.frontsy.picciotto.converters;

import de.frontsy.picciotto.converters.poi.cell.style.PoiStyle;
import de.frontsy.picciotto.converters.poi.cell.Range;
import de.frontsy.picciotto.structure.Cell;
import de.frontsy.picciotto.structure.Row;
import de.frontsy.picciotto.structure.Sheet;
import de.frontsy.picciotto.structure.Workbook;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
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
                    XSSFCellStyle cellStyle = null;
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
                                // FIXME: There seems to be a bug in POI..
                                // Just doing the left and right border works, but anytime a top or bottom is applied,
                                // weird stuff happens :(
                                CellRangeAddress mergedRegion = new CellRangeAddress(rowIndex, range.getLastRow(), cellIndex, range.getLastColumn());
                                RegionUtil.setBorderTop(xssfCell.getCellStyle().getBorderTop(), mergedRegion, xssfSheet);
                                RegionUtil.setBorderRight(xssfCell.getCellStyle().getBorderRight(), mergedRegion, xssfSheet);
                                RegionUtil.setBorderBottom(xssfCell.getCellStyle().getBorderBottom(), mergedRegion, xssfSheet);
                                RegionUtil.setBorderLeft(xssfCell.getCellStyle().getBorderLeft(), mergedRegion, xssfSheet);
                                RegionUtil.setTopBorderColor(xssfCell.getCellStyle().getTopBorderColor(), mergedRegion, xssfSheet);
                                RegionUtil.setRightBorderColor(xssfCell.getCellStyle().getRightBorderColor(), mergedRegion, xssfSheet);
                                RegionUtil.setBottomBorderColor(xssfCell.getCellStyle().getBottomBorderColor(), mergedRegion, xssfSheet);
                                RegionUtil.setLeftBorderColor(xssfCell.getCellStyle().getLeftBorderColor(), mergedRegion, xssfSheet);
                                xssfSheet.addMergedRegion(mergedRegion);
                            }
                        } catch (Exception e) {
                            log.error(e.getMessage());
                        }
                    }
                    cellIndex += 1;
                }
                rowIndex += 1;
            }
        }
        return xssfWorkbook;
    }
}
