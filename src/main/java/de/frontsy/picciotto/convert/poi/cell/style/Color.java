package de.frontsy.picciotto.convert.poi.cell.style;

import de.frontsy.picciotto.convert.poi.ColorConverter;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Optional;

@Slf4j
@Builder
@Data
public class Color implements PoiStyle {
	public static final String DEFAULT_VALUE = "#000000";
	String value;

	@Override
	public void setStyle(XSSFCellStyle style, XSSFWorkbook workbook) {
		XSSFFont font = workbook.createFont();
		Optional<String> colorAttempt = ColorConverter.toHex(value);
		String color;
		if (colorAttempt.isPresent()) {
			color = colorAttempt.get();
		} else {
			color = DEFAULT_VALUE;
			log.warn("Couldn't find hex value for: " + value);
		}
		font.setColor(ColorConverter.hexToXSSFColor(color));
		style.setFont(font);
	}
}
