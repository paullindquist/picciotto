package de.frontsy.picciotto.structure;

import lombok.*;

import java.util.Set;

@Data
@Builder
@ToString
@EqualsAndHashCode
public class Workbook {

    public static final String DEFAULT_SHEET_NAME = "Workbook";

    private final String name;
    private final Set<Sheet> sheets;

    public Workbook(@NonNull String name, @NonNull Set<Sheet> sheets) {
        this.name = name;
        this.sheets = sheets;
    }
}
