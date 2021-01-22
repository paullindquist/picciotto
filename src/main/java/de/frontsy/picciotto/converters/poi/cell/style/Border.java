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
public class Border implements PoiStyle {
    public static final String DEFAULT_COLOR = "#000000";
    private String borderTop;
    private String borderRight;
    private String borderBottom;
    private String borderLeft;
    private String borderWidth;
    @Builder.Default
    private String borderColor = DEFAULT_COLOR;
    @Builder.Default
    private String borderStyle = "solid";

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
    public void setStyle( XSSFCellStyle style, XSSFWorkbook workbook ) {
        // TODO: This..
        style.setBorderColor(XSSFCellBorder.BorderSide.TOP, ColorConverter.hexToXSSFColor(borderColor));
        style.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, ColorConverter.hexToXSSFColor(borderColor));
        style.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, ColorConverter.hexToXSSFColor(borderColor));
        style.setBorderColor(XSSFCellBorder.BorderSide.LEFT, ColorConverter.hexToXSSFColor(borderColor));

        BorderStyle borderStyle = determineBorderStyle();


        style.setBorderTop(borderStyle);
        style.setBorderRight(borderStyle);
        style.setBorderBottom(borderStyle);
        style.setBorderLeft(borderStyle);
    }
}
