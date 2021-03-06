package de.frontsy.picciotto.convert.poi.cell.style;

import de.frontsy.picciotto.convert.poi.ColorConverter;
import de.frontsy.picciotto.parse.css.Rule;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class BorderBottomStyleFactory extends AbstractStyleFactory {

    @Override
    public PoiStyle getStyle(Rule rule) {
        String color;
        Map<String, String> properties = rule.getValues();
        if (properties.containsKey("border-bottom-color")) {
            String colorProperty = properties.get("border-bottom-color");
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
        String width = properties.getOrDefault("border-bottom-width",Border.DEFAULT_STYLE);
        String style = properties.getOrDefault("border-bottom-style", Border.DEFAULT_STYLE);
        return BorderBottom.builder()
            .color(color)
            .width(width)
            .style(style)
            .build();
    }
}
