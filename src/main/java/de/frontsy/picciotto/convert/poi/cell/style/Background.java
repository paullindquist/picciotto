package de.frontsy.picciotto.convert.poi.cell.style;

import de.frontsy.picciotto.convert.poi.ColorConverter;
import lombok.Builder;
import lombok.Data;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Data
@Builder
public class Background implements PoiStyle {
    private final String color;
    private final String backgroundImage;
    private final String backgroundPosition;
    private final String backgroundSize;
    private final String backgroundRepeat;
    private final String backgroundOrigin;
    private final String backgroundClip;
    private final String backgroundAttachment;

    @Override
    public void setStyle( XSSFCellStyle style, XSSFWorkbook workbook ) {
        XSSFColor convertedColor = ColorConverter.hexToXSSFColor(color);
        style.setFillForegroundColor(convertedColor);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }
}
