package de.frontsy.picciotto.converters.poi.cell.style;

import de.frontsy.picciotto.converters.poi.ColorConverter;
import de.frontsy.picciotto.parse.css.Rule;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

@Slf4j
public class BorderStyleFactory extends AbstractStyleFactory {
    public static final String DEFAULT_BORDER_COLOR = "000000";

    @Override
    public PoiStyle getStyle(Rule rule) {
        String color;
        Map<String, String> properties = rule.getValues();
        if (properties.containsKey("border-color")) {
            String colorProperty = properties.get("border-color");
            try {
                color = ColorConverter.toHex(colorProperty).orElse( DEFAULT_BORDER_COLOR);
            } catch (IllegalArgumentException iae) {
                log.warn("Was unable to decode: " + colorProperty);
                color = DEFAULT_BORDER_COLOR;
            }
        } else {
            color = DEFAULT_BORDER_COLOR;
        }
        return Border.builder()
            .borderColor(color)
            .borderStyle(properties.getOrDefault("border-style", ""))
            .borderTop(properties.getOrDefault("border-top", ""))
            .borderRight(properties.getOrDefault("border-right", ""))
            .borderBottom(properties.getOrDefault("border-bottom", ""))
            .borderLeft(properties.getOrDefault("border-left", ""))
            .build();
    }
}
