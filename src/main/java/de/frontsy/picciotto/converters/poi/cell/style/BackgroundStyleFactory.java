package de.frontsy.picciotto.converters.poi.cell.style;

import de.frontsy.picciotto.converters.poi.ColorConverter;
import de.frontsy.picciotto.parse.css.Rule;
import de.frontsy.picciotto.structure.Color;
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
                Optional<Color> colorAttempt = ColorConverter.toHex(properties.get("background-color"));
                if (colorAttempt.isPresent()) {
                    colorHex = colorAttempt.get().getHex();
                } else {
                    colorHex = DEFAULT_BACKGROUND_COLOR;
                }
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
