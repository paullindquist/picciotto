package de.frontsy.picciotto.converters.poi.cell.style;

import de.frontsy.picciotto.converters.poi.ColorConverter;
import de.frontsy.picciotto.parse.css.Rule;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class BorderTopStyleFactory extends AbstractStyleFactory {

    @Override
    public PoiStyle getStyle(Rule rule) {
        String color;
        Map<String, String> properties = rule.getValues();
        if (properties.containsKey("border-top-color")) {
            String colorProperty = properties.get("border-top-color");
            try {
                color = ColorConverter.toHex(colorProperty).orElse( Border.DEFAULT_COLOR);
            } catch (IllegalArgumentException iae) {
                log.warn("Was unable to decode: " + colorProperty);
                color = Border.DEFAULT_COLOR;
            }
        } else {
            color = Border.DEFAULT_COLOR;
        }
        // FIXME: This isn't right?
        String width = properties.getOrDefault("border-top-width",Border.DEFAULT_STYLE);
        String style = properties.getOrDefault("border-top-style", Border.DEFAULT_STYLE);
        return BorderTop.builder()
            .color(color)
            .width(width)
            .style(style)
            .build();
    }
}
