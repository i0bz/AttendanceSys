package ui.contents.management.student;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import controllers.AttendanceSystemController;
import controllers.ControllerBootstrapSingleton;
import ui.contents.components.BasePanel;
import ui.contents.components.StudTableBtnEditor;
import ui.contents.components.StudTableBtnRenderer;
import ui.utility.ConstraintUtils;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.Map;

class StudentTable extends BasePanel {

    private final String[] rowLabels = {"Name:", "UID:", "Action:"};
    private final DefaultTableModel model = new DefaultTableModel(rowLabels,0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return  column == 2;
        }
    };
    private final JTable tableView = new JTable(model);
    JScrollPane scrollPanel = new JScrollPane(tableView);


    StudentTable() {
        AttendanceSystemController controller = ControllerBootstrapSingleton.getController();
        controller.addPropertyChangeListener(e -> refreshTableByName());

        super.padding = new FlatEmptyBorder(0,0,0,0);
        super.border = new CompoundBorder(super.line_border, super.padding);
        mainPanel.setBorder(super.border);

        mainPanel.add(scrollPanel);

        drawComponents();
        refreshTableByName();

    }


    private void refreshTableByName() {
        Map<String, String> rosterMap = ControllerBootstrapSingleton.getController().getAllStudentsByName();

        model.setRowCount(0);
        rosterMap.forEach((key, value) -> model.addRow(new Object[]{key, value, "Drop"}));

        tableView.getColumnModel().getColumn(2).setCellRenderer(new StudTableBtnRenderer());
        tableView.getColumnModel().getColumn(2).setCellEditor(new StudTableBtnEditor(new JCheckBox()));

    }

    private void drawComponents() {
        tableView.setRowSelectionAllowed(false);
        tableView.setRowHeight(50);
        tableView.getTableHeader().setReorderingAllowed(false);
        tableView.getTableHeader().setResizingAllowed(false);
        tableView.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        TableColumnModel columnModel = tableView.getColumnModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        columnModel.getColumn(1).setCellRenderer(centerRenderer);

        int totalWidth = tableView.getWidth();
        if (totalWidth <= 0) totalWidth = 400;

        columnModel.getColumn(0).setPreferredWidth((int)(totalWidth * 0.5));
        columnModel.getColumn(1).setPreferredWidth((int)(totalWidth * 0.1));
        columnModel.getColumn(1).setMinWidth(80);
        columnModel.getColumn(2).setPreferredWidth((int)(totalWidth * 0.4));


        scrollPanel.putClientProperty("FlatLaf.style", "arc: 20");
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        layout.setConstraints(scrollPanel, constraints);
        ConstraintUtils.reset(constraints);


    }

    public JPanel getPanel() {
        return mainPanel;
    }


}