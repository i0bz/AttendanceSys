package ui.contents.management.attendance;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import controllers.ControllerBootstrapSingleton;
import repository.AttendanceRegistry;
import ui.contents.components.AttTableBtnEditor;
import ui.contents.components.Panel;
import ui.contents.components.TableBtnRenderer;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.List;

class AttendanceTable extends Panel {

    private String[] header = {"Date:", "Action:"};
    private DefaultTableModel model = new DefaultTableModel(header, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 1;
        }
    };
    private JTable table = new JTable(model);

    AttendanceTable() {
        AttendanceRegistry registry = ControllerBootstrapSingleton.getInstance().registry();


        registry.addPropertyChangeListener(evt -> {
            refreshTable();
        });

        super.padding = new FlatEmptyBorder(0, 0, 0, 0);
        super.border = new CompoundBorder(super.line_border, super.padding);
        mainPanel.setBorder(super.border);

        initComponents();
        refreshTable();
    }

    private void refreshTable() {
        model.setRowCount(0);
        List<String> dateList = ControllerBootstrapSingleton.getInstance().getController().attendanceDateLists();

        for (String date : dateList) {
            model.addRow(new Object[]{date, "Remove"});
        }

        table.getColumnModel().getColumn(1).setCellRenderer(new TableBtnRenderer());
        table.getColumnModel().getColumn(1).setCellEditor(new AttTableBtnEditor(new JCheckBox()));

    }

    private void initComponents() {

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setRowSelectionAllowed(false);
        table.setRowHeight(50);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(300);

        JScrollPane pane = new JScrollPane(table);
        pane.putClientProperty("FlatLaf.style", "arc: 20");

        mainPanel.add(pane, constraints);
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
