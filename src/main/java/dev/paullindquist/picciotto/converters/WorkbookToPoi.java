package dev.paullindquist.picciotto.converters;

import dev.paullindquist.picciotto.structure.Cell;
import dev.paullindquist.picciotto.structure.Row;
import dev.paullindquist.picciotto.structure.Sheet;
import dev.paullindquist.picciotto.structure.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
                    xssfCell.setCellValue(123);
                    cellIndex += 1;
                }
            }
        }
        return xssfWorkbook;
    }
}
