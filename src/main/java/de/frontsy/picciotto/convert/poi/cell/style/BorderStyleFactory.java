package de.frontsy.picciotto.convert.poi.cell.style;

import de.frontsy.picciotto.convert.poi.ColorConverter;
import de.frontsy.picciotto.parse.css.Rule;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

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
            .color(color)
            .style(properties.getOrDefault("border-style", ""))
            .top(properties.getOrDefault("border-top", ""))
            .right(properties.getOrDefault("border-right", ""))
            .bottom(properties.getOrDefault("border-bottom", ""))
            .left(properties.getOrDefault("border-left", ""))
            .width(properties.getOrDefault("border-width", ""))
            .build();
    }
}
