package de.frontsy.picciotto.parse;

import de.frontsy.picciotto.structure.Workbook;

import java.util.Optional;

public interface Parser {
    public Optional<Workbook> parse(String content);
}
