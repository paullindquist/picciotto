package de.frontsy.picciotto.convert.poi.cell.style;

import de.frontsy.picciotto.convert.poi.ColorConverter;
import lombok.Builder;
import lombok.Data;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Data
@Builder
public class TextAlign implements PoiStyle {
    private final String value;

    @Override
    public void setStyle( XSSFCellStyle style, XSSFWorkbook workbook ) {
        HorizontalAlignment alignment;
        switch (value) {
            case "end":
            case "right":
                alignment = HorizontalAlignment.RIGHT;
                break;
            case "center":
                alignment = HorizontalAlignment.CENTER;
                break;
            case "justify":
                alignment = HorizontalAlignment.JUSTIFY;
                break;
            default:
                alignment = HorizontalAlignment.LEFT;
        }
        style.setAlignment(alignment);
    }
}
