package de.frontsy.picciotto.converters.poi.cell.style;

import de.frontsy.picciotto.converters.poi.ColorConverter;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Slf4j
@Builder
@Data
public class Font implements PoiStyle
	{
	public static final String DEFAULT_COLOR = "#000000";
	public static final String DEFAULT_FAMILY = XSSFFont.DEFAULT_FONT_NAME;
	public static final String DEFAULT_WEIGHT = "normal";
	private String fontFamily;
	private String fontSize;
	private String fontStretch;
	private String fontStyle;
	private String fontVariant;
	private String fontWeight;
	private String lineHeight;
	private String color;

	private boolean isFontWeightBold( )
		{
		switch ( fontWeight )
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
		style.getFont().setBold( isFontWeightBold());
		}
	}
