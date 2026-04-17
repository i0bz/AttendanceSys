package ui.contents.system;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import controllers.AttendanceSystemController;
import ui.contents.components.BasePanel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.Map;

class AttendanceSystemPanel extends BasePanel {
    private final AttendanceSystemController controller;

    private final String[] columnHeaders = {"Name:", "UID:", "Present:"};
    private final DefaultTableModel model = new DefaultTableModel(columnHeaders, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 2;
        }

        public Class<?> getColumnClass(int col) {
            return (col == 2) ? Boolean.class : super.getColumnClass(col);
        }
    };
    private final JTable tableView = new JTable(model);
    private final JScrollPane pane = new JScrollPane(tableView);

    String event;

    AttendanceSystemPanel(AttendanceSystemController controller) {
        this.controller = controller;
        super.padding = new FlatEmptyBorder(0, 0, 0, 0);
        super.border = new CompoundBorder(super.line_border, super.padding);
        this.setBorder(border);
        this.add(pane, constraints);

        drawComponents();
        addEventHandlers();
    }

    private void addEventHandlers() {

        model.addTableModelListener(e -> {
            if (e.getType() != TableModelEvent.UPDATE) return;
            int row = e.getFirstRow();
            int col = e.getColumn();

            if (col != 2) return;
            DefaultTableModel model = (DefaultTableModel) e.getSource();
            String uid = (String) model.getValueAt(row, 1);

            controller.toggleAttendance(uid, event);

        });
    }

    private void drawComponents()
    {

        tableView.setRowSelectionAllowed(false);
        tableView.getTableHeader().setResizingAllowed(false);
        tableView.getTableHeader().setReorderingAllowed(false);
        tableView.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);


        TableColumnModel columnModel = tableView.getColumnModel();

        int totalWidth = tableView.getWidth();
        if (totalWidth <= 0) totalWidth = 400;

        columnModel.getColumn(0).setPreferredWidth((int) (totalWidth * 0.8));
        columnModel.getColumn(1).setPreferredWidth((int) (totalWidth * 0.1));
        columnModel.getColumn(1).setMinWidth(80);
        columnModel.getColumn(2).setPreferredWidth((int) (totalWidth * 0.1));
        columnModel.getColumn(2).setMinWidth(80);

        tableView.setRowHeight(45);


        pane.putClientProperty("FlatLaf.style", "arc: 20");

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        layout.setConstraints(pane, constraints);


    }

    public void refreshTable(String date) {
        Map<String, String> students = controller.getAllStudentsById();

        if (date == null || date.equals("Select Attendance")) {
            model.setRowCount(0);
            return;
        }
        model.setRowCount(0);

        for (Map.Entry<String, String> entry : students.entrySet()) {
            String uid = entry.getKey();
            String name = entry.getValue();

            model.addRow(new Object[]{
                    name,
                    uid,
                    controller.isPresent(uid, date)
            });
        }
    }

}
