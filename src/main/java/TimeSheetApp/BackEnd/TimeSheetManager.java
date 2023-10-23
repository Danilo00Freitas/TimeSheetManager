package TimeSheetApp.BackEnd;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TimeSheetManager {
    private List<String> timeSheetRecorderList;
    private Sheet sheet;
    private String excelDirPath;
    private String fileName = "PontoEletrônico.xlsx";
    private String filePath;
    private Workbook workbook;

    public TimeSheetManager() {
        timeSheetRecorderList = new ArrayList<>();
        excelDirPath = System.getProperty("user.dir");
        filePath = excelDirPath + File.separator + fileName;

        File file = new File(filePath);
        if (file.exists()) {
            try (FileInputStream inputStream = new FileInputStream(file)) {
                this.workbook = new XSSFWorkbook(inputStream);
                this.sheet = workbook.getSheetAt(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            this.sheet = createWorkbookAndSheet();
        }
    }

    public Sheet createWorkbookAndSheet() {
        String[] titles = {"DATA", "ENTRADA", "INTERVALO", "RETORNO INTERVALO", "SAÍDA"};
        this.workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(fileName);
        Row row = sheet.createRow(0);
        int titleColum = 0;

        for (int i = 0; i < 5; i++) {
            Cell cell = row.createCell(titleColum);
            cell.setCellValue(titles[i]);
            sheet.autoSizeColumn(i);
            titleColum++;
        }
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheet;
    }

    public void exportToTable(ArrayList<String> timeSheetRecorderList) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        int rowIndex;

        if (verifyLastEntry(timeSheetRecorderList)) {
            rowIndex = this.sheet.getLastRowNum();
        } else {
            rowIndex = this.sheet.getLastRowNum() + 1;
        }

        Row row = sheet.createRow(rowIndex);
        int cellIndex = 0;
        for (String item : timeSheetRecorderList) {
            row.createCell(cellIndex).setCellValue(item);
            cellIndex++;
        }

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean verifyLastEntry(ArrayList<String> timeSheetRecorderList){
        int lasWrittenRow = this.sheet.getLastRowNum();
        Row lastRow = this.sheet.getRow(lasWrittenRow);
        Cell firstCell = lastRow.getCell(0);
        String cellValue = firstCell.getStringCellValue();
        return cellValue.equals(timeSheetRecorderList.get(0));
    }

    public void changeEntry(String date, int index, String value){
        for (Row row : this.sheet){
            if (row.getCell(0).toString().equals(date)){
                row.createCell(index).setCellValue(value);
                try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                    workbook.write(outputStream);
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}


