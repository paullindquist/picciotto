package de.frontsy.picciotto.converters.poi;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public final class BorderUtils {



    /*
    public static void setBorder(XSSFSheet sheet, BorderStyle borderStyle, CellRangeAddress region) {
        setBorderTop(sheet, borderStyle, region);
        setBorderBottom(sheet, borderStyle, region);
        setBorderLeft(sheet, borderStyle, region);
        setBorderRight(sheet, borderStyle, region);
    }

    public static void setBorderTop(XSSFSheet sheet, BorderStyle borderStyle, CellRangeAddress region) {
        Row row = sheet.getRow(region.getFirstRow());
        for (int i = region.getFirstColumn(); i <= region.getLastColumn(); i++) {
            Cell cell = row.getCell(i);
            CellStyle cellStyle = cell.getCellStyle();
            cellStyle.setBorderTop(borderStyle);
            cell.setCellStyle(cellStyle);
        }
    }
     */

    public static void setBorderBottom(XSSFSheet sheet, XSSFCell originalCell, CellRangeAddress region) {
        Row row = sheet.getRow(region.getLastRow());
        for (int i = region.getFirstColumn(); i <= region.getLastColumn(); i++) {
            Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            XSSFCellStyle style = sheet.getWorkbook().createCellStyle();
            style.cloneStyleFrom(originalCell.getCellStyle());
            style.setBorderBottom(style.getBorderBottom());
            style.setBottomBorderColor(style.getBottomBorderColor());
            style.setBorderTop(style.getBorderTop());
            style.setBottomBorderColor(style.getTopBorderColor());
            cell.setCellStyle(style);
        }
    }

    /*
    public static void setBorderLeft(XSSFSheet sheet, BorderStyle borderStyle, CellRangeAddress region) {
        for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
            Cell cell = sheet.getRow(i).getCell(region.getFirstColumn());
            CellStyle cellStyle = cell.getCellStyle();
            cellStyle.setBorderLeft(borderStyle);
            cell.setCellStyle(cellStyle);
        }
    }

    public static void setBorderRight(XSSFSheet sheet, BorderStyle borderStyle, CellRangeAddress region) {
        for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
            Cell cell = sheet.getRow(i).getCell(region.getLastColumn());
            CellStyle cellStyle = cell.getCellStyle();
            cellStyle.setBorderRight(borderStyle);
            cell.setCellStyle(cellStyle);
        }
    }
     */

}