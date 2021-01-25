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
    public static final String DEFAULT_STYLE = "normal";
    private String top;
    private String right;
    private String bottom;
    private String left;
    private String width;
    @Builder.Default
    private String color = DEFAULT_COLOR;
    @Builder.Default
    private String style = DEFAULT_STYLE;

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
    public void setStyle( XSSFCellStyle style, XSSFWorkbook workbook ) {
        // TODO: This..
        style.setBorderColor(XSSFCellBorder.BorderSide.TOP, ColorConverter.hexToXSSFColor(color));
        style.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, ColorConverter.hexToXSSFColor(color));
        style.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, ColorConverter.hexToXSSFColor(color));
        style.setBorderColor(XSSFCellBorder.BorderSide.LEFT, ColorConverter.hexToXSSFColor(color));

        BorderStyle borderStyle = determineBorderStyle();


        style.setBorderTop(borderStyle);
        style.setBorderRight(borderStyle);
        style.setBorderBottom(borderStyle);
        style.setBorderLeft(borderStyle);
    }
}
