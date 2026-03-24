package ui.contents.management.student;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import controllers.AttendanceSystemController;
import controllers.ControllerBootstrapSingleton;
import repository.StudentRoster;
import ui.contents.components.Card;
import ui.contents.components.StudTableBtnEditor;
import ui.contents.components.TableBtnRenderer;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.Map;

class StudentTable extends Card {

    private final String[] rowLabels = {"Name:", "UID:", "Action:"};
    private final DefaultTableModel model = new DefaultTableModel(rowLabels,0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return  column == 2;
        }
    };
    private final JTable tableView = new JTable(model);


    StudentTable() {
        StudentRoster rosterView = ControllerBootstrapSingleton.getInstance().roster();
        rosterView.addPropertyChangeListener(evt -> refreshTableByName());

        super.padding = new FlatEmptyBorder(0,0,0,0);
        super.border = new CompoundBorder(super.line_border, super.padding);
        mainPanel.setBorder(super.border);



        initComponents();
        refreshTableByName();

    }


    private void refreshTableByName() {
        AttendanceSystemController attendanceSystemController = ControllerBootstrapSingleton.getController();
        model.setRowCount(0);
        Map<String, String> rosterMap = attendanceSystemController.getAllStudentsByName();




        for (Map.Entry<String, String> entry : rosterMap.entrySet()) {
            model.addRow(new Object[]{entry.getKey(),entry.getValue(), "Drop"});

        }
        tableView.getColumnModel().getColumn(2).setCellRenderer(new TableBtnRenderer());
        tableView.getColumnModel().getColumn(2).setCellEditor(new StudTableBtnEditor(new JCheckBox()));



    }

    private void initComponents() {
        tableView.setRowSelectionAllowed(false);
        tableView.setRowHeight(40);
        tableView.getTableHeader().setReorderingAllowed(false);
        tableView.getTableHeader().setResizingAllowed(false);
        tableView.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

        TableColumnModel columnModel = tableView.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(300);

        JScrollPane pane = new JScrollPane(tableView);

        pane.putClientProperty("FlatLaf.style", "arc: 20");
        constraints.insets = new Insets(0,0,0,0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        mainPanel.add(pane,constraints);



    }

    public JPanel getPanel() {
        return mainPanel;
    }


}