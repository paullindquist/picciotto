package dev.paullindquist.picciotto.converters;

import dev.paullindquist.picciotto.converters.poi.cell.style.PoiStyle;
import dev.paullindquist.picciotto.structure.Cell;
import dev.paullindquist.picciotto.structure.Row;
import dev.paullindquist.picciotto.structure.Sheet;
import dev.paullindquist.picciotto.structure.Workbook;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.*;

import java.util.Map;

@Slf4j
public class WorkbookToPoi {
    public XSSFWorkbook workbookToXSSFWorkbook(Workbook workbook) {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        for (Sheet sheet : workbook.getSheets()) {
            XSSFSheet xssfSheet = xssfWorkbook.createSheet();
            Integer rowIndex = 0;
            for (Row row : sheet.getRows()) {
                XSSFRow xssfRow = xssfSheet.createRow(rowIndex);
                rowIndex += 1;
                Integer cellIndex = 0;
                for (Cell cell : row.getCells()) {
                    XSSFCell xssfCell = xssfRow.createCell(cellIndex);
                    xssfCell.setCellValue(cell.getValue());
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
            }
        }
        return xssfWorkbook;
    }
}
