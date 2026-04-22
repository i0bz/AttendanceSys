package ui.contents.management.attendance;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import controllers.AttendanceSystemController;
import ui.contents.components.AttTableBtnEditor;
import ui.contents.components.AttTableBtnRenderer;
import ui.contents.components.BasePanel;
import ui.utility.ConstraintUtils;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

class AttendanceTablePanel extends BasePanel {
    private final AttendanceSystemController controller;
    private final String[] header = {"Event:","Date:", "Action:"};
    private final DefaultTableModel model = new DefaultTableModel(header, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 2;
        }
    };
    private final JTable table = new JTable(model);
    private final JScrollPane pane = new JScrollPane(table);

    AttendanceTablePanel(AttendanceSystemController controller) {
        this.controller = controller;
        controller.addPropertyChangeListener(_ -> refreshTable());

        super.padding = new FlatEmptyBorder(0, 0, 0, 0);
        super.border = new CompoundBorder(super.line_border, super.padding);
        this.setBorder(super.border);

        this.add(pane, constraints);

        drawComponents();
        refreshTable();
    }

    private void refreshTable() {

        if (table.isEditing()) table.getCellEditor().stopCellEditing();

        model.setRowCount(0);
        List<String> eventList = controller.attendanceEventList();
        List<String> dateList = controller.attendanceDateList();

        for (int i = 0; i < eventList.size(); i++) {
            model.addRow(new Object[]{eventList.get(i), dateList.get(i), "Remove"});
        }


    }

    private void drawComponents() {

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        sorter.setSortable(0, true);
        sorter.setSortable(1, true);
        sorter.setSortable(2, false);
        table.setRowSorter(sorter);

        table.getTableHeader().putClientProperty("FlatLaf.styleClass", "h2");
        table.putClientProperty("FlatLaf.styleClass", "h3");

        table.setRowSelectionAllowed(false);
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        table.getColumnModel().getColumn(2).setCellRenderer(new AttTableBtnRenderer());
        table.getColumnModel().getColumn(2).setCellEditor(new AttTableBtnEditor(new JCheckBox(), controller));

        TableColumnModel columnModel = table.getColumnModel();

        DefaultTableCellRenderer centeredRendered = new DefaultTableCellRenderer();
        centeredRendered.setHorizontalAlignment(JLabel.CENTER);
        columnModel.getColumn(1).setCellRenderer(centeredRendered);

        int totalWidth = table.getWidth();
        if (totalWidth <= 0) totalWidth = 400;

        columnModel.getColumn(0).setPreferredWidth((int)(totalWidth * 0.5));
        columnModel.getColumn(1).setPreferredWidth((int)(totalWidth * 0.25));
        columnModel.getColumn(2).setPreferredWidth((int)(totalWidth * 0.25));



        ConstraintUtils.setCoords(constraints, 0 ,0);
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        layout.setConstraints(pane, constraints);

        pane.putClientProperty("FlatLaf.style", "arc: 20");

        ConstraintUtils.reset(constraints);
    }
}
