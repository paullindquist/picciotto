package de.frontsy.picciotto.convert;

import de.frontsy.picciotto.structure.Cell;
import de.frontsy.picciotto.structure.Row;
import de.frontsy.picciotto.structure.Sheet;
import de.frontsy.picciotto.structure.Workbook;

public interface Visitor {
    public void visit(Workbook workbook);
    public void visit(Sheet sheet);
    public void visit(Row row);
    public void visit(Cell cell);
}
