package dev.paullindquist.picciotto.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@Builder
public class Sheet {
    private final String name;
    private final List<Column> columns;
    private final List<Row> rows;

    public Sheet(@NonNull String name, @NonNull List<Column> columns, @NonNull List<Row> rows) {
        this.name = name;
        this.columns = columns;
        this.rows = rows;
    }
}
