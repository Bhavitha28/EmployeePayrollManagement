package com.employee.payroll.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.employee.payroll.entities.model.EmployeeDetails;
import com.employee.payroll.entities.model.EmployeeWorkdays;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class ExcelToWorkdays {

    @Autowired
    private EmployeeService empService;
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "EMPID", "TOTAL_DAYS", "LEAVES_BAL", "LEAVES_APPLIED","TIME_FROM","TIME_TO" };
    static String SHEET = "workdays";
    public  boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public  List<EmployeeWorkdays> excelToworkdays(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<EmployeeWorkdays> workdays = new ArrayList<EmployeeWorkdays>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                EmployeeWorkdays workday = new EmployeeWorkdays();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {

                        case 0:
                            EmployeeDetails emp = empService.getEmployeeByEmployeeId(currentCell.getStringCellValue());
                            workday.setEmployeeId(emp);
                            break;
                        case 1:
                            workday.setTotalDays((int) currentCell.getNumericCellValue());
                            break;

                        case 2:
                            workday.setLeaveBal((float) currentCell.getNumericCellValue());
                            break;
                        case 3:
                            workday.setLeavesApplied((float) currentCell.getNumericCellValue());
                            break;
                        case 4:
                            workday.setTimeFrom(currentCell.getDateCellValue());
                            break;
                        case 5:
                            workday.setTimeTo(currentCell.getDateCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                workdays.add(workday);
            }
            workbook.close();
            return workdays;
        } catch (Exception e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}

