package de.frontsy.picciotto.converters.poi.cell.style;

import de.frontsy.picciotto.converters.poi.ColorConverter;
import de.frontsy.picciotto.converters.poi.cell.PoiBorderStyleFactory;
import de.frontsy.picciotto.parse.css.Rule;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;

import java.util.Map;


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

    @Override
    public void setStyle( XSSFCellStyle style, XSSFWorkbook workbook ) {
        BorderStyle borderStyle =
            PoiBorderStyleFactory.getBorder(
                Rule.builder().values(
                        Map.of("border-style", this.style, "border-width", this.width)
                ).build()).orElseThrow();
        style.setBorderTop(borderStyle);
        style.setBorderRight(borderStyle);
        style.setBorderBottom(borderStyle);
        style.setBorderLeft(borderStyle);
        XSSFColor borderColor = ColorConverter.hexToXSSFColor(color);
        style.setBorderColor(XSSFCellBorder.BorderSide.TOP, borderColor);
        style.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, borderColor);
        style.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, borderColor);
        style.setBorderColor(XSSFCellBorder.BorderSide.LEFT, borderColor);
    }
}
