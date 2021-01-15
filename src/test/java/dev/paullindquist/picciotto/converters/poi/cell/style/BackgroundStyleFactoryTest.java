package dev.paullindquist.picciotto.converters.poi.cell.style;

import dev.paullindquist.picciotto.parse.css.Rule;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BackgroundStyleFactoryTest {

    AbstractStyleFactory backgroundStyleFactory;

    @BeforeEach
    void setup() {
        backgroundStyleFactory = BackgroundStyleFactory.getStyleFactory("background").get();
    }

    @Test
    void shouldReturnFactoryForBackground() {
        assertNotNull(backgroundStyleFactory);
    }

    @Test
    void shouldReturnCorrectColorWithValidWebHexString() {
        String backgroundColor = "#dedede";
        Map<String, String> values = Map.of("background-color", backgroundColor);
        Rule rule = Rule
            .builder()
            .values(values)
            .build();
        BackgroundStyle style = (BackgroundStyle) backgroundStyleFactory.getStyle(rule);
        assertEquals("dedede", style.getColor());
    }

    @Test
    void shouldReturnDefaultColorWithInvalidWebHexString() {
        String backgroundColor = "xxxxx";
        Map<String, String> values = Map.of("background-color", backgroundColor);
        Rule rule = Rule
            .builder()
            .values(values)
            .build();
        BackgroundStyle style = (BackgroundStyle) backgroundStyleFactory.getStyle(rule);
        assertEquals(BackgroundStyleFactory.DEFAULT_BACKGROUND_COLOR, style.getColor());
    }
}
