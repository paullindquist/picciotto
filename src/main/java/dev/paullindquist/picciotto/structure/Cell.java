package dev.paullindquist.picciotto.structure;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cell {
    private final String style;
    private final String value;
}
