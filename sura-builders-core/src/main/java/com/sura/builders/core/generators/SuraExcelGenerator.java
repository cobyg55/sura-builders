package com.sura.builders.core.generators;

import com.sura.builders.common.response.InventoryResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class SuraExcelGenerator {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<InventoryResponse> inventories;

    public SuraExcelGenerator(List<InventoryResponse> inventories) {
        this.workbook = new XSSFWorkbook();
        this.inventories = inventories;
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Inventarios");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);

        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "Cantidad", style);
        createCell(row, 2, "Producto", style);
        createCell(row, 3, "Costo Unitario", style);
        createCell(row, 4, "Costo Total", style);
        createCell(row, 6, "Fecha de Creación", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Timestamp) {
            cell.setCellValue((Timestamp) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (InventoryResponse inventoryResponse : inventories) {
            Row row = sheet.createRow(rowCount++);
            createCell(row, 0, inventoryResponse.getId(), style);
            createCell(row, 1, inventoryResponse.getQuantity(), style);
            createCell(row, 3, inventoryResponse.getUnitCost(), style);
            createCell(row, 4, inventoryResponse.getTotalCost(), style);
            createCell(row, 6, deleteMiliSecond(inventoryResponse.getCreatedAt().toString()), style);

        }
    }

    public String deleteMiliSecond(String date){
        String[] dateSplit = date.split("[.]");
        return dateSplit[0];
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }


}
