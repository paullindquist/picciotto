package de.frontsy.picciotto.converters.poi.cell.style;

import de.frontsy.picciotto.converters.poi.ColorConverter;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Optional;

@Slf4j
@Builder
@Data
public class Color implements PoiStyle
	{
	public static final String DEFAULT_VALUE = "000000";
	String value;

	@Override
	public void setStyle( XSSFCellStyle style, XSSFWorkbook workbook ) {
		String color = ColorConverter.toHex( value ).orElse(DEFAULT_VALUE);
		style.getFont().setColor( ColorConverter.hexToXSSFColor( color ));
		}
	}
