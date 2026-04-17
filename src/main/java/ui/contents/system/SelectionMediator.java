package ui.contents.system;

class SelectionMediator {
    AttendanceSelectionPanel attendanceSelection;
    AttendanceSystemPanel attendanceSystem;

    SelectionMediator(AttendanceSelectionPanel attendanceSelection, AttendanceSystemPanel attendanceSystem) {
        this.attendanceSelection = attendanceSelection;
        this.attendanceSystem = attendanceSystem;

        attendanceSelection.onDateSelected(e -> {
            attendanceSystem.event = attendanceSelection.getSelectedEvent();
            attendanceSystem.refreshTable(attendanceSystem.event);
        });
    }
}
