package de.frontsy.picciotto.convert.poi.cell.style;

import de.frontsy.picciotto.convert.poi.ColorConverter;
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
public class BorderRight implements PoiStyle {
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
        style.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, ColorConverter.hexToXSSFColor(color));
        style.setBorderRight(determineBorderStyle());
    }
}
