package de.frontsy.picciotto.convert.poi.cell.style;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Slf4j
@Builder
@Data
public class FontFamily implements PoiStyle {
public static final String DEFAULT_FAMILY = "normal";
private final String name;

@Override
public void setStyle( XSSFCellStyle style, XSSFWorkbook workbook )
    {
    XSSFFont font = style.getFont( );
    font.setFamily( org.apache.poi.ss.usermodel.FontFamily.SCRIPT );
    }
}
