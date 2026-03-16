package ui.gui.contents;


import java.util.List;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.formdev.flatlaf.ui.FlatEmptyBorder;

import controllers.AttendanceSystemController;
import controllers.ControllerBootstrapSingleton;
import repository.AttendanceRegistry;

import ui.gui.contents.components.TableBtnRenderer; 
import ui.gui.contents.components.AttTableBtnEditor;

public class AttendanceManagementContent {
    private JPanel mainPanel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();

    public AttendanceManagementContent() {
        initComponents();
        
    
    }

    private void initComponents() {
        AttendanceCreationView attendanceCreationView = new AttendanceCreationView();
        AttendanceTable attendanceTable = new AttendanceTable();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(20,20,20,20);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        mainPanel.add(attendanceCreationView.getPanel(), constraints);
        
        
        constraints.gridy = 1;
        constraints.insets = new Insets(20,20,20,20);
        constraints.weighty = 1.0;
        mainPanel.add(attendanceTable.getPanel(), constraints);
        


    }

    public JPanel getPanel() {
        return mainPanel;
    }
}

class AttendanceTable extends Card {

    private String[] header = {"Date:", "Action:"};
    private DefaultTableModel model = new DefaultTableModel(header, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return  column == 1;
        }
    };
    private JTable table = new JTable(model);

    AttendanceTable() {
        AttendanceRegistry registry = ControllerBootstrapSingleton.getInstance().registry();


        registry.addPropertyChangeListener(evt -> {
            refreshTable();
        });

        super.padding = new FlatEmptyBorder(0,0,0,0);
        super.border = new CompoundBorder(super.line_border, super.padding);
        mainPanel.setBorder(super.border);
        
        initComponents();
        refreshTable();
    }

    private void refreshTable() {
        model.setRowCount(0);
        List<String> dateList  = ControllerBootstrapSingleton.getInstance().getController().attendanceDateLists();

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

class AttendanceCreationView extends Card {

    private JLabel descriptionLabel = new JLabel("Add New Attendance Date:");
    private JLabel dateLabel = new JLabel("Date:");
    private JTextField dateInput = new JTextField();
    private JButton addButton = new JButton("Add Date");


    AttendanceCreationView() {
        initComponents();
        addEventHandlers();

    }

    public void addEventHandlers() {
        dateInput.setText("YYYY-MM-DD");
        dateInput.setForeground(Color.GRAY);

        dateInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (dateInput.getText().equals("YYYY-MM-DD") || dateInput.getText().equals("Invalid Date Format!")){
                    dateInput.setText("");
                    dateInput.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (dateInput.getText().isEmpty()) {
                    dateInput.setText("YYYY-MM-DD");
                    dateInput.setForeground(Color.GRAY);
                }
            }
        });

        addButton.addActionListener(e -> {
            createAttendance();
        });

        dateInput.addActionListener(e -> {
            createAttendance();
            dateInput.getParent().requestFocusInWindow();
        });

    }

    private void createAttendance() {
        AttendanceSystemController controller = ControllerBootstrapSingleton.getInstance().getController();
        String input = dateInput.getText();
        try{
            controller.createAttendance(input);
        } catch(RuntimeException e) {
            dateInput.setText("Invalid Date Format!");
            dateInput.setForeground(Color.RED);
            return;
        }
        dateInput.setText("YYYY-MM-DD");
        dateInput.setForeground(Color.GRAY);
    }

//TODO create helper functions for each
    private void initComponents() {
        
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.gridwidth = 2;
        descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
        mainPanel.add(descriptionLabel, constraints);


        constraints.insets = new Insets(0,0,0,0);
        constraints.gridy = 1;
        mainPanel.add(dateLabel, constraints);


        dateInput.putClientProperty("FlatLaf.style", "arc: 10");
        constraints.insets = new Insets(0, 0, 0, 10);
        constraints.gridwidth = 1;
        constraints.gridy = 2;
        constraints.weightx = 2;
        mainPanel.add(dateInput, constraints);


        addButton.putClientProperty("FlatLaf.style", "arc: 10");
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridx = 1;
        constraints.weightx = 0.1;
        mainPanel.add(addButton, constraints);


        constraints.gridx = 2;
        constraints.weightx = 10;
        mainPanel.add(Box.createVerticalGlue(), constraints);
    

    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
