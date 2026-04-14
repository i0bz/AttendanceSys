package services;

import org.apache.poi.ss.usermodel.*;
import utility.ParseUtility;

import java.io.File;
import java.io.IOException;

public class ImportService {
    private final StudentService studentManagement;
    private final AttendanceService attendanceService;
    private final DataFormatter dataFormatter = new DataFormatter();

    public ImportService(StudentService studentManagement, AttendanceService attendanceService) {
        this.studentManagement = studentManagement;
        this.attendanceService = attendanceService;
    }

    public void importStudentsExcel(File file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file);
        Sheet studentSheet = workbook.getSheet("Students List");
        if (studentSheet == null) throw new IOException("Students List not found");

        for (Row row : studentSheet) {
            if (row.equals(studentSheet.getRow(0))) continue;
            String name = dataFormatter.formatCellValue(row.getCell(1));
            String uid = dataFormatter.formatCellValue(row.getCell(0));
            studentManagement.enroll(name, ParseUtility.parseUID(uid));
        }
    }

    public void importEventsExcel(File file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file);
        Sheet attendanceSheet = workbook.getSheet("Events List");
        if (attendanceSheet == null) throw new IOException("Events List not found");

        for (Row row : attendanceSheet) {
            if (row.equals(attendanceSheet.getRow(0))) continue;
            String event = dataFormatter.formatCellValue(row.getCell(0));
            String date = dataFormatter.formatCellValue(row.getCell(1));
            attendanceService.createAttendance(event, ParseUtility.parseDate(date));
        }
    }

    public void importAttendancesExcel(File file) throws IOException {
        importStudentsExcel(file);
        importEventsExcel(file);

        Workbook workbook = WorkbookFactory.create(file);
        Sheet attendanceSheet = workbook.getSheet("Attendance Sheet");
        if (attendanceSheet == null) throw new IOException("Attendance Sheet not found");
        Row headerRow = attendanceSheet.getRow(0);

        int i = 0;

        for (Row row : attendanceSheet) {
            if (row.equals(headerRow)) continue;
            Cell uidCell = row.getCell(0);
            int uid = ParseUtility.parseUID(dataFormatter.formatCellValue(uidCell));

            for (Cell cell : row) {
                i++;
                if (cell.equals(uidCell)) continue;
                if (dataFormatter.formatCellValue(cell).equals("Present")) {
                    String event = dataFormatter.formatCellValue(headerRow.getCell(i));
                    attendanceService.markPresent(event, uid);
                }
            }
        }

    }
}
