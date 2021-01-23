package de.frontsy.picciotto.converters.poi.cell.style;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Optional;

@Slf4j
@Builder
@Data
public class FontStyle implements PoiStyle {
	public static final String DEFAULT_STYLE = "normal";
	private final String name;

	private Optional<FontFamily> determineFontFamily( String family) {
		 switch(family) {
		 case DEFAULT_STYLE:
			  return Optional.of(FontFamily.NOT_APPLICABLE);
		 case "cursive":
			  return Optional.of(FontFamily.DECORATIVE);
		 default:
		 }
		 return Optional.empty( );
	}

	@Override
	public void setStyle( XSSFCellStyle style, XSSFWorkbook workbook ) {
		 XSSFFont font = style.getFont( );
		Optional<FontFamily> attempt = determineFontFamily( name );
		 if (attempt.isPresent()) {
			  font.setFamily( attempt.get() );
		 }
	 }
}
