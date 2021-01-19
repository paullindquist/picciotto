package dev.paullindquist.picciotto.converters;

import dev.paullindquist.picciotto.converters.poi.cell.Range;
import dev.paullindquist.picciotto.converters.poi.cell.style.PoiStyle;
import dev.paullindquist.picciotto.structure.Cell;
import dev.paullindquist.picciotto.structure.Row;
import dev.paullindquist.picciotto.structure.Sheet;
import dev.paullindquist.picciotto.structure.Workbook;
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
            Integer rowIndex = 0;
            for (Row row : sheet.getRows()) {
                XSSFRow xssfRow = xssfSheet.createRow(rowIndex);
                Integer cellIndex = 0;
                for (Cell cell : row.getCells()) {
                    XSSFCell xssfCell = xssfRow.createCell(cellIndex);
                    xssfCell.setCellValue(cell.getValue());
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
                            xssfSheet.addMergedRegion(new CellRangeAddress(rowIndex, range.getLastRow(), cellIndex, range.getLastColumn()));
                        } catch (Exception e) {
                            log.error(e.getMessage());
                        }
                    }
                    Map<String, PoiStyle> styles = cell.getStyles();
                    if (!styles.isEmpty()) {
                        XSSFCellStyle cellStyle = xssfWorkbook.createCellStyle();
                        for (PoiStyle style : styles.values()) {
                            try {
                                style.setStyle(cellStyle);
                            } catch (Exception e) {
                                log.error(e.getMessage());
                            }
                        }
                        xssfCell.setCellStyle(cellStyle);
                    }
                    cellIndex += 1;
                }
                rowIndex += 1;
            }
        }
        return xssfWorkbook;
    }
}
