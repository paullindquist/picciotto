package dev.paullindquist.picciotto.converters.poi.cell.style;

import lombok.Data;

@Data
public class FontFamily implements Style {
    private final String value;
    private final String name;

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getName() {
        return name;
    }
}
