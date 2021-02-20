package de.frontsy.picciotto.convert.poi;

import com.helger.css.utils.ECSSColor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFColor;

import java.util.Optional;

@Slf4j
public class ColorConverter {

    /**
     * @param color
     * @return a 6 character hex color
     */
    public static Optional<String> toHex(String color) {
        String hex = null;
        if (color.startsWith("#")) {
            hex = color;
        } else {
            ECSSColor ecssColor = ECSSColor.getFromNameCaseInsensitiveOrNull(color);
            if (ecssColor != null) {
                hex = ecssColor.getAsHexColorValue();
            } else {
                log.error("Could not convert: " + color);
            }
        }
        if (hex != null) {
            return Optional.of(hex);
        }
        return Optional.empty();
    }

    public static XSSFColor hexToXSSFColor(String color) {
        XSSFColor result;
        java.awt.Color decoded = java.awt.Color.decode(color);
        try {
            int red = decoded.getRed();
            int green = decoded.getGreen();
            int blue = decoded.getBlue();
            result = new XSSFColor(new java.awt.Color(red, green, blue), new DefaultIndexedColorMap());
            return result;
        } catch (Exception e) {
            log.warn(e.getMessage());
            byte[] rgb = {(byte) 255, (byte) 255, (byte) 255};
            return new XSSFColor(rgb, new DefaultIndexedColorMap());
        }
    }
}
