package de.frontsy.picciotto.converters.poi.cell.style;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Slf4j
@Builder
@Data
public class FontVariant implements PoiStyle {
public static final String DEFAULT_WEIGHT = "normal";
private final String name;

@Override
public void setStyle( XSSFCellStyle style, XSSFWorkbook workbook )
    {
    // Does this even make sense for a spreadsheet?
    }
}
