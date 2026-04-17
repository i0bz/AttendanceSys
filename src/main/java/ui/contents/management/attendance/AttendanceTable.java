package ui.contents.management.attendance;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import controllers.AttendanceSystemController;
import controllers.ControllerBootstrapSingleton;
import ui.contents.components.AttTableBtnEditor;
import ui.contents.components.AttTableBtnRenderer;
import ui.contents.components.BasePanel;
import ui.utility.ConstraintUtils;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.List;

class AttendanceTable extends BasePanel {

    private final String[] header = {"Event:","Date:", "Action:"};
    private final DefaultTableModel model = new DefaultTableModel(header, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 2;
        }
    };
    private final JTable table = new JTable(model);
    private final JScrollPane pane = new JScrollPane(table);

    AttendanceTable() {
        AttendanceSystemController controller = ControllerBootstrapSingleton.getController();
        controller.addPropertyChangeListener(e -> refreshTable());

        super.padding = new FlatEmptyBorder(0, 0, 0, 0);
        super.border = new CompoundBorder(super.line_border, super.padding);
        this.setBorder(super.border);

        this.add(pane, constraints);

        drawComponents();
        refreshTable();
    }

    private void refreshTable() {
        model.setRowCount(0);
        List<String> eventList = ControllerBootstrapSingleton.getController().attendanceEventList();
        List<String> dateList = ControllerBootstrapSingleton.getController().attendanceDateList();

        for (int i = 0; i < eventList.size(); i++) {
            model.addRow(new Object[]{eventList.get(i), dateList.get(i), "Remove"});
        }

        table.getColumnModel().getColumn(2).setCellRenderer(new AttTableBtnRenderer());
        table.getColumnModel().getColumn(2).setCellEditor(new AttTableBtnEditor(new JCheckBox()));

    }

    private void drawComponents() {

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setRowSelectionAllowed(false);
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        TableColumnModel columnModel = table.getColumnModel();

        DefaultTableCellRenderer centeredRendered = new DefaultTableCellRenderer();
        centeredRendered.setHorizontalAlignment(JLabel.CENTER);
        columnModel.getColumn(0).setCellRenderer(centeredRendered);

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
