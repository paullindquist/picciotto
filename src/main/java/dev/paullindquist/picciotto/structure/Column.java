package dev.paullindquist.picciotto.structure;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Column {
    private final Integer width;
}
