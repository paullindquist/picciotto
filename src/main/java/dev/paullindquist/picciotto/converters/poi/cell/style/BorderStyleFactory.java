package dev.paullindquist.picciotto.converters.poi.cell.style;

import dev.paullindquist.picciotto.converters.poi.ColorConverter;
import dev.paullindquist.picciotto.parse.css.Rule;
import dev.paullindquist.picciotto.structure.Color;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

@Slf4j
public class BorderStyleFactory extends AbstractStyleFactory {
    public static final String DEFAULT_BORDER_COLOR = "000000";

    @Override
    public PoiStyle getStyle(Rule rule) {
        Map<String, String> properties = rule.getValues();
        String color;
        if (properties.containsKey("border-color")) {
            color = properties.get("border-color");
            try {
                Optional<Color> test = ColorConverter.toHex(color);
                if (test.isPresent()) {
                    color = test.get().getHex();
                } else {
                    color = DEFAULT_BORDER_COLOR;
                }
            } catch (IllegalArgumentException iae) {
                log.warn("Was unable to decode: " + color);
                color = DEFAULT_BORDER_COLOR;
            }
        } else {
            color = DEFAULT_BORDER_COLOR;
        }
        return Border.builder()
            .borderColor(color)
            .borderStyle(properties.get("border-style"))
            .borderTop(properties.get("border-top"))
            .borderRight(properties.get("border-right"))
            .borderBottom(properties.get("border-bottom"))
            .borderLeft(properties.get("border-left"))
            .build();
    }
}
