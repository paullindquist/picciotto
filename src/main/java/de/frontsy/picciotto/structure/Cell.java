package de.frontsy.picciotto.structure;

import de.frontsy.picciotto.converters.poi.cell.style.PoiStyle;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class Cell {
    private final int rowspan;
    private final int colspan;
    @Builder.Default
    private final Map<String, PoiStyle> styles = new HashMap<>();
    private final String value;
    public static final String NAME = "cell"; // I don't think this is necessary..
}
