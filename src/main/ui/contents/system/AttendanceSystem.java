package ui.contents.system;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import controllers.AttendanceSystemController;
import controllers.ControllerBootstrapSingleton;
import ui.contents.components.Panel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

class AttendanceSystem extends Panel {


    String[] columnHeaders = {"Name:", "UID:", "Present:"};
    DefaultTableModel model = new DefaultTableModel(columnHeaders, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 2;
        }

        public Class<?> getColumnClass(int col) {
            return (col == 2) ? Boolean.class : super.getColumnClass(col);
        }
    };
    JTable tableView = new JTable(model);


    AttendanceSystem() {

        super.padding = new FlatEmptyBorder(0, 0, 0, 0);
        super.border = new CompoundBorder(super.line_border, super.padding);

        mainPanel.setBorder(border);

        initComponents();
        refreshTable();
        addEventHandlers();
    }

    private void addEventHandlers() {
        ControllerBootstrapSingleton.getController().addPropertyChangeListener(evt -> {
            refreshTable();
        });

        ControllerBootstrapSingleton.getInstance().roster().addPropertyChangeListener(evt -> {
            refreshTable();
        });


        model.addTableModelListener(e -> {
            if (e.getType() != TableModelEvent.UPDATE) return;
            int row = e.getFirstRow();
            int col = e.getColumn();

            if (col != 2) return;
            DefaultTableModel model = (DefaultTableModel) e.getSource();
            String date = AttendanceSelection.getInstance().dateOptions.getSelectedItem().toString();
            String uid = (String) model.getValueAt(row, 1);

            ControllerBootstrapSingleton.getController().toggleAttendance(uid, date);

        });
    }

    private void initComponents() {

        tableView.setRowSelectionAllowed(false);
        tableView.getTableHeader().setResizingAllowed(false);
        tableView.getTableHeader().setReorderingAllowed(false);

        tableView.getColumnModel().getColumn(2).setMaxWidth(150);
        tableView.getColumnModel().getColumn(2).setPreferredWidth(150);

        tableView.setRowHeight(45);

        JScrollPane pane = new JScrollPane(tableView);
        pane.putClientProperty("FlatLaf.style", "arc: 20");


        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;

        constraints.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(pane, constraints);


    }

    public void refreshTable() {
        AttendanceSystemController controller = ControllerBootstrapSingleton.getController();
        Map<String, String> students = controller.getAllStudentsByName();

        String date = AttendanceSelection.getInstance().dateOptions.getSelectedItem().toString();
        if (date.equals("Select Attendance")) {
            model.setRowCount(0);
            return;
        }
        model.setRowCount(0);

        for (Map.Entry<String, String> entry : students.entrySet()) {
            String name = entry.getKey();
            String uid = entry.getValue();

            model.addRow(new Object[]{
                    name,
                    uid,
                    controller.isPresent(uid, date)
            });
        }
    }

    public JPanel getPanel() {
        return mainPanel;
    }


    //TODO hude refactor for this later
    //im creating this as singleton for now


    static class SingletonHolder {
        private static final AttendanceSystem singleton = new AttendanceSystem();
    }

    static AttendanceSystem getInstance() {
        return SingletonHolder.singleton;
    }


}
