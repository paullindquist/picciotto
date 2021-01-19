package dev.paullindquist.picciotto.converters.poi.cell.style;

import dev.paullindquist.picciotto.converters.poi.ColorConverter;
import lombok.Builder;
import lombok.Data;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

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
    public void setStyle(XSSFCellStyle style) {
        XSSFColor convertedColor = ColorConverter.hexToXSSFColor("#" + color);
        style.setFillForegroundColor(convertedColor);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }
}
