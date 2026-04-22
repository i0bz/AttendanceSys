package ui.contents.management.student;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import controllers.AttendanceSystemController;
import ui.contents.components.BasePanel;
import ui.contents.components.StudTableBtnEditor;
import ui.contents.components.StudTableBtnRenderer;
import ui.utility.ConstraintUtils;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.table.*;
import java.awt.*;
import java.util.Map;

class StudentTablePanel extends BasePanel {
    private final AttendanceSystemController controller;


    private final String[] rowLabels = {"Name:", "UID:", "Action:"};
    private final DefaultTableModel model = new DefaultTableModel(rowLabels,0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return  column == 2;
        }
    };
    private final JTable tableView = new JTable(model);
    JScrollPane scrollPanel = new JScrollPane(tableView);


    StudentTablePanel(AttendanceSystemController controller) {
        this.controller = controller;
        controller.addPropertyChangeListener(_ -> refreshTableByName());

        super.padding = new FlatEmptyBorder(0,0,0,0);
        super.border = new CompoundBorder(super.line_border, super.padding);
        this.setBorder(super.border);

        this.add(scrollPanel);

        drawComponents();
        refreshTableByName();

    }


    private void refreshTableByName() {
        Map<String, String> rosterMap = controller.getAllStudentsById();
        if (tableView.isEditing()) tableView.getCellEditor().stopCellEditing();
        model.setRowCount(0);
        rosterMap.forEach((key, value) -> model.addRow(new Object[]{value, key, "Drop"}));


    }

    private void drawComponents() {

        tableView.setRowSelectionAllowed(false);
        tableView.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableView.setRowHeight(50);

        tableView.getTableHeader().setReorderingAllowed(false);
        tableView.getTableHeader().setResizingAllowed(false);

        tableView.getTableHeader().putClientProperty("FlatLaf.styleClass", "h2");
        tableView.putClientProperty("FlatLaf.styleClass", "h3");

        tableView.getColumnModel().getColumn(2).setCellRenderer(new StudTableBtnRenderer());
        tableView.getColumnModel().getColumn(2).setCellEditor(new StudTableBtnEditor(new JCheckBox(), controller));

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableView.getModel());
        sorter.setSortable(0, true);
        sorter.setSortable(1, true);
        sorter.setSortable(2, false);
        tableView.setRowSorter(sorter);

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

}