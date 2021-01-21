package de.frontsy.picciotto.converters.poi.cell.style;

import de.frontsy.picciotto.parse.css.Rule;

import java.util.Map;

public class ColorStyleFactory extends AbstractStyleFactory {
    @Override
    public PoiStyle getStyle(Rule rule) {
        Map<String, String> properties = rule.getValues();
        String colorValue = properties.getOrDefault("color", Color.DEFAULT_VALUE);
        return Color.builder().value(colorValue).build();
    }
}
