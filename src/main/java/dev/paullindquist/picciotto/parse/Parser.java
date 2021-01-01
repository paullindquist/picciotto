package dev.paullindquist.picciotto.parse;

import dev.paullindquist.picciotto.structure.Workbook;

import java.util.Optional;

public interface Parser {
    public Optional<Workbook> parse(String content);
}
