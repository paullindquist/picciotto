package de.frontsy.picciotto.converters.poi.cell.style;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface PoiStyle {
    public void setStyle( XSSFCellStyle style, XSSFWorkbook workbook );
}
