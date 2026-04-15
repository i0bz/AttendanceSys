package services;

import entity.Student;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utility.ParseUtility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ExportService {
    private final StudentService studentManagement;
    private final AttendanceService attendanceService;


    public ExportService(StudentService studentManagement, AttendanceService attendanceService) {
        this.studentManagement = studentManagement;
        this.attendanceService = attendanceService;
    }

    public void exportExcel(File file) {
        try (Workbook workbook = new XSSFWorkbook()) {
            writeStudentsExcel(workbook);
            writeEventsExcel(workbook);
            writeAttendancesExcel(workbook);


            try (FileOutputStream outputFile = new FileOutputStream(file)) {
                workbook.write(outputFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeStudentsExcel(Workbook workbook) {
        Sheet sheet = workbook.createSheet("Students List");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Name");

        Map<Integer, Student> students = studentManagement.getAllStudentsByID();
        AtomicInteger iterator = new AtomicInteger(1);

        students.forEach((id, student) -> {
            Row row = sheet.createRow(iterator.get());
            row.createCell(0).setCellValue(ParseUtility.unparseUID(id));
            row.createCell(1).setCellValue(student.name());
            iterator.getAndIncrement();
        });
    }

    private void writeEventsExcel(Workbook workbook) {
        Sheet eventsSheet = workbook.createSheet("Events List");
        CellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("yyyy-MM-dd"));

        Map<String, LocalDate> events = attendanceService.queryEvents();

        Row header = eventsSheet.createRow(0);
        header.createCell(0).setCellValue("Event Name");
        header.createCell(1).setCellValue("Date");

        AtomicInteger iterator = new AtomicInteger(1);

        events.forEach((name, date) -> {
            Row row = eventsSheet.createRow(iterator.get());
            row.createCell(0).setCellValue(name);
            row.createCell(1).setCellValue(date);
            row.getCell(1).setCellStyle(dateStyle);
            iterator.getAndIncrement();
        });
    }

    private void writeAttendancesExcel(Workbook workbook) {
        Sheet attendancesSheet = workbook.createSheet("Attendance Sheet");
        Row header = attendancesSheet.createRow(0);
        List<String> events = attendanceService.queryEvents().keySet().stream().toList();
        List<Integer> students = studentManagement.getAllStudentsByID().keySet().stream().toList();

        int iterator = 1;

        for (String event : events) {
            header.createCell(iterator++).setCellValue(event);
        }

        iterator = 1;
        for (Integer id : students) {
            Row row = attendancesSheet.createRow(iterator++);
            row.createCell(0).setCellValue(ParseUtility.unparseUID(id));
        }


        int studentIterator = 1;
        for (Integer id : students) {
            Row row = attendancesSheet.getRow(studentIterator++);
            int eventIterator = 1;
            for (String event : events) {
                Cell cell = row.createCell(eventIterator++);
                if (attendanceService.isPresent(event, id)) cell.setCellValue("Present");
                else cell.setCellValue("Absent");
            }
        }
    }

}
