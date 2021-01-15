package dev.paullindquist.picciotto.structure;

import dev.paullindquist.picciotto.converters.poi.cell.style.PoiStyle;
import lombok.Builder;
import lombok.Data;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Map;

@Data
@Builder
public class Cell {
    private final int rowspan;
    private final int colspan;
    private final Map<String, PoiStyle> styles;
    private final String value;
    public static final String NAME = "cell";
}
