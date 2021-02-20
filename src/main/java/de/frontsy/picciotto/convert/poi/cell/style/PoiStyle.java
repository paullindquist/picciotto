package de.frontsy.picciotto.convert.poi.cell.style;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface PoiStyle {
    public void setStyle( XSSFCellStyle style, XSSFWorkbook workbook );
}
