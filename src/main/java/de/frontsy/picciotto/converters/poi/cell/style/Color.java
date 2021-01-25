package de.frontsy.picciotto.converters.poi.cell.style;

import de.frontsy.picciotto.converters.poi.ColorConverter;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
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
