package de.frontsy.picciotto.structure;

import de.frontsy.picciotto.convert.Visitor;
import de.frontsy.picciotto.convert.poi.cell.style.PoiStyle;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class Cell implements SpreadSheetElement{
    private final int rowspan;
    private final int colspan;
    private int index;
    @Builder.Default
    private final Map<String, PoiStyle> styles = new HashMap<>();
    private final String value;

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
