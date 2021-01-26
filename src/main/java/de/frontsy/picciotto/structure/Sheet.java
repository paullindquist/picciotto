package de.frontsy.picciotto.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@Builder
public class Sheet {
    private final String name;
    private final List<Row> rows;

    public Sheet(@NonNull String name, @NonNull List<Row> rows) {
        this.name = name;
        this.rows = rows;
    }
}
