package de.frontsy.picciotto.convert.poi.cell.style;

import de.frontsy.picciotto.parse.css.Rule;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class TextAlignStyleFactory extends AbstractStyleFactory {
    public static final PoiStyle DEFAULT_TEXT_ALIGN_STYLE = TextAlign.builder().build();

    @Override
    public PoiStyle getStyle(Rule rule) {
        Map<String, String> properties = rule.getValues();
        if (properties.containsKey("text-align")) {
            return TextAlign
                    .builder()
                    .value(properties.get("text-align"))
                    .build();
        }
        return DEFAULT_TEXT_ALIGN_STYLE;
    }
}
