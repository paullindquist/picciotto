package dev.paullindquist.picciotto.converters.poi.cell.style;

import dev.paullindquist.picciotto.converters.poi.ColorConverter;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;


@Slf4j
@Builder
@Data
public class Border implements PoiStyle {
    private String borderTop;
    private String borderRight;
    private String borderBottom;
    private String borderLeft;
    private String borderStyle;
    private String borderWidth;
    private String borderColor;

    private BorderStyle determineBorderStyle() {
        switch (borderStyle) {
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
    public void setStyle(XSSFCellStyle style) {
        style.setBorderColor(XSSFCellBorder.BorderSide.TOP, ColorConverter.hexToXSSFColor("#" + borderColor));
        style.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, ColorConverter.hexToXSSFColor("#" + borderColor));
        style.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, ColorConverter.hexToXSSFColor("#" + borderColor));
        style.setBorderColor(XSSFCellBorder.BorderSide.LEFT, ColorConverter.hexToXSSFColor("#" + borderColor));

        BorderStyle borderStyle = determineBorderStyle();


        style.setBorderTop(borderStyle);
        style.setBorderRight(borderStyle);
        style.setBorderBottom(borderStyle);
        style.setBorderLeft(borderStyle);
    }
}
