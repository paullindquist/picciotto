package de.frontsy.picciotto.converters.poi;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ColorConverterTest {

    @Test
    void hexStringShouldReturnSame() {
        String red = "#fff";
        Optional<String> converted = ColorConverter.toHex(red);
        // TODO: Try and get the color name (like red in this case) ??
        assertEquals("#fff", converted.orElseThrow());
    }

    @Test
    void colorNameShouldReturn() {
        String red = "red";
        Optional<String> converted = ColorConverter.toHex(red);
        assertEquals("#ff0000", converted.orElseThrow());
    }

    @Test
    void hexToXSSFColor() {
    }
}
