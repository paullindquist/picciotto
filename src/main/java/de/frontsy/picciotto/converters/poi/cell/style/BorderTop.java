package de.frontsy.picciotto.converters.poi.cell.style;

import de.frontsy.picciotto.converters.poi.ColorConverter;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;

@Slf4j
@Builder
@Data
public class BorderTop implements PoiStyle {
    String color;
    String width;
    String style;

    protected BorderStyle determineBorderStyle() {
        switch (style) {
            case "thin":
                return BorderStyle.HAIR;
            case "dotted":
                return BorderStyle.DOTTED;
            case "dashed":
                return BorderStyle.DASHED;
            default:
                return BorderStyle.MEDIUM;
        }
    }

    @Override
    public void setStyle(XSSFCellStyle style, XSSFWorkbook workbook) {
        style.setBorderColor(XSSFCellBorder.BorderSide.TOP, ColorConverter.hexToXSSFColor(color));
        style.setBorderTop(determineBorderStyle());
    }
}
