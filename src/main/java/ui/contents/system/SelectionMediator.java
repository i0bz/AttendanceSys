package ui.contents.system;

class SelectionMediator {
    AttendanceSelection attendanceSelection;
    AttendanceSystem attendanceSystem;

    SelectionMediator(AttendanceSelection attendanceSelection, AttendanceSystem attendanceSystem) {
        this.attendanceSelection = attendanceSelection;
        this.attendanceSystem = attendanceSystem;

        attendanceSelection.onDateSelected(e -> {
            attendanceSystem.event = attendanceSelection.getSelectedEvent();
            attendanceSystem.refreshTable(attendanceSystem.event);
        });
    }
}
