package de.frontsy.picciotto.convert.poi.cell.style;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Slf4j
@Builder
@Data
public class FontWeight implements PoiStyle {
public static final String DEFAULT_WEIGHT = "normal";
private final String name;

private boolean isFontWeightBold( )
		{
		switch ( name )
			{
			case "bold":
			case "700":
				return true;
			default:
				return false;
			}
		}

@Override
public void setStyle( XSSFCellStyle style, XSSFWorkbook workbook )
    {
    XSSFFont font = style.getFont( );
    font.setBold( isFontWeightBold());
    }
}
