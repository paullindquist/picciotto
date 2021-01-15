package dev.paullindquist.picciotto.converters.poi;

import dev.paullindquist.picciotto.structure.Color;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ColorConverterTest {

    @Test
    void hexStringShouldReturnSame() {
        String red = "#fff";
        Optional<Color> converted = ColorConverter.toHex(red);
        // TODO: Try and get the color name (like red in this case) ??
        assertEquals("", converted.get().getName());
        assertEquals("fff", converted.get().getHex());
    }

    @Test
    void colorNameShouldReturn() {
        String red = "red";
        Optional<Color> converted = ColorConverter.toHex(red);
        assertEquals("ff0000", converted.get().getHex());
        assertEquals("red", converted.get().getName());
    }

    @Test
    void hexToXSSFColor() {
    }
}
