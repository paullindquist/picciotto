package de.frontsy.picciotto.convert.poi.cell.style;

import de.frontsy.picciotto.convert.poi.ColorConverter;
import de.frontsy.picciotto.parse.css.Rule;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

@Slf4j
public class BackgroundStyleFactory extends AbstractStyleFactory {
    public static final String DEFAULT_BACKGROUND_COLOR = "ffffff";

    public static final PoiStyle DEFAULT_BACKGROUND_STYLE = Background.builder().build();

    @Override
    public PoiStyle getStyle(Rule rule) {
        String colorHex;
        Map<String, String> properties = rule.getValues();

        if (properties != null) {
            if (properties.containsKey("background-color")) {
                Optional<String> colorAttempt = ColorConverter.toHex(properties.get("background-color"));
                colorHex = colorAttempt.orElse(DEFAULT_BACKGROUND_COLOR);
            } else {
                colorHex = DEFAULT_BACKGROUND_COLOR;
            }
            return Background
                .builder()
                .color(colorHex)
                .build();
        } else {
            return DEFAULT_BACKGROUND_STYLE;
        }
    }
}
