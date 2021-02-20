package de.frontsy.picciotto.structure;

import de.frontsy.picciotto.convert.Visitor;

public interface SpreadSheetElement {
    public void accept(Visitor visitor);
}
