package ui.contents.system;

import controllers.AttendanceSystemController;

class SelectionMediator {
    AttendanceSelectionPanel attendanceSelection;
    AttendanceSystemPanel attendanceSystem;

    SelectionMediator(AttendanceSelectionPanel attendanceSelection, AttendanceSystemPanel attendanceSystem, AttendanceSystemController controller) {
        this.attendanceSelection = attendanceSelection;
        this.attendanceSystem = attendanceSystem;


        controller.addPropertyChangeListener(e -> {
            if (!controller.attendanceEventList().contains(attendanceSystem.event)) {
                attendanceSelection.eventOptions.setSelectedIndex(0);
            }
            attendanceSystem.refreshTable(attendanceSystem.event);
        });

        attendanceSelection.onDateSelected(e -> {
            attendanceSystem.event = attendanceSelection.getSelectedEvent();
            attendanceSystem.refreshTable(attendanceSystem.event);
        });
    }
}
