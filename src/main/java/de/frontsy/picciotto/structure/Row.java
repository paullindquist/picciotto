package de.frontsy.picciotto.structure;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class Row {
    private final List<Cell> cells;
}
