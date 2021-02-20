package de.frontsy.picciotto.convert.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public final class BorderUtils {


    public static void setBorderTop(XSSFSheet sheet, XSSFCell originalCell, CellRangeAddress region) {
        Row row = sheet.getRow(region.getFirstRow());
        XSSFCellStyle style = sheet.getWorkbook().createCellStyle();
        style.cloneStyleFrom(originalCell.getCellStyle());
        for (int i = region.getFirstColumn(); i <= region.getLastColumn(); i++) {
            Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            style.setBorderTop(style.getBorderTop());
            style.setTopBorderColor(style.getTopBorderXSSFColor());
            cell.setCellStyle(style);
        }
    }

    public static void setBorderRight(XSSFSheet sheet, XSSFCell originalCell, CellRangeAddress region) {
        int column = region.getFirstColumn();
        int firstRow = region.getFirstRow();
        int lastRow = region.getLastRow();
        XSSFCellStyle style = sheet.getWorkbook().createCellStyle();
        style.cloneStyleFrom(originalCell.getCellStyle());
        for (int i = firstRow; i <= lastRow; i++) {
            Row row = CellUtil.getRow(i, sheet);
            Cell cell = row.getCell(column, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            style.setBorderRight(style.getBorderRight());
            style.setRightBorderColor(style.getRightBorderXSSFColor());
            cell.setCellStyle(style);
        }
    }

    public static void setBorderBottom(XSSFSheet sheet, XSSFCell originalCell, CellRangeAddress region) {
        Row row = sheet.getRow(region.getLastRow());
        XSSFCellStyle style = sheet.getWorkbook().createCellStyle();
        style.cloneStyleFrom(originalCell.getCellStyle());
        for (int i = region.getFirstColumn(); i <= region.getLastColumn(); i++) {
            Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            style.setBorderBottom(style.getBorderBottom());
            style.setBottomBorderColor(style.getBottomBorderXSSFColor());
            cell.setCellStyle(style);
        }
    }

    public static void setBorderLeft(XSSFSheet sheet, XSSFCell originalCell, CellRangeAddress region) {
        Row row = sheet.getRow(region.getLastRow());
        XSSFCellStyle style = sheet.getWorkbook().createCellStyle();
        style.cloneStyleFrom(originalCell.getCellStyle());
        for (int i = region.getFirstColumn(); i <= region.getLastColumn(); i++) {
            Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            style.setBorderLeft(style.getBorderLeft());
            style.setLeftBorderColor(style.getLeftBorderXSSFColor());
            cell.setCellStyle(style);
        }
    }


}