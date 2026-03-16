package ui.gui.contents;


import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.ui.FlatEmptyBorder;

import controllers.AttendanceSystemController;
import controllers.ControllerFactorySingleton;

import java.awt.*;
import java.util.Vector;
import java.util.List;
import java.util.Map;

public class AttendanceSystemContent {

    JPanel mainPanel = new JPanel(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();



    public AttendanceSystemContent() {
        AttendanceSelection attendanceSelection = AttendanceSelection.getInstance();
        AttendanceSystemTable attendanceSystemTable = AttendanceSystemTable.getInstance();

        constraints.insets = new Insets(20,20,20,20);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(attendanceSelection.getPanel(), constraints);




        constraints.gridy = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(attendanceSystemTable.getPanel(), constraints);
    }

    public JPanel getPanel() {
        return mainPanel;
    }

}



class AttendanceSelection extends Card {
    JLabel description = new JLabel("Attendance Mode"); 
    Vector<String> dateList = new Vector<>();   
    JComboBox<String> dateOptions = new JComboBox<>(dateList);

    private AttendanceSelection() {
        initComponents();
        addEventHandlers();
        refreshDates();
    }


    private void initComponents() {
        dateList.add("Select Attendance");
        
        constraints.insets = new Insets(5,5,5,5);
        dateOptions.setSelectedIndex(0);
        
        description.setHorizontalAlignment(SwingConstants.LEFT);
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(description, constraints);


        constraints.gridy = 1;
        constraints.weightx = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(dateOptions, constraints);

        constraints.gridx = 1;
        constraints.weightx = 3;
        mainPanel.add(Box.createVerticalGlue(), constraints);
    }


    public void refreshDates() {
        List<String> registryDateList = ControllerFactorySingleton.getInstance().createController().attendanceDateLists();

        dateList.clear();
        dateList.add("Select Attendance");

        for (String date : registryDateList) {
            dateList.addLast(date);
        }
    }


    public void addEventHandlers() {

        ControllerFactorySingleton.getInstance().registry().addPropertyChangeListener(evt -> {
            refreshDates();
        });

        dateOptions.addActionListener(evt -> {
            AttendanceSystemTable.getInstance().refreshTable();
        });
        

    }






    public JPanel getPanel() {
        return mainPanel;
    }




    //TODO hude refactor for this later
    //im creating this as singleton for now


    static class SingletonHolder {
        private static final AttendanceSelection singleton = new AttendanceSelection();
    }

    static AttendanceSelection getInstance() {
        return SingletonHolder.singleton; 

    }

}




class AttendanceSystemTable extends Card {
    

    String[] columnHeaders = {"Name:", "UID:", "Present:"};
    DefaultTableModel model = new DefaultTableModel(columnHeaders, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    JTable tableView = new JTable(model);

    

    AttendanceSystemTable() {

        super.padding = new FlatEmptyBorder(0,0,0,0);
        super.border = new CompoundBorder(super.line_border, super.padding);

        mainPanel.setBorder(border);

        initComponents();
        refreshTable();

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

        constraints.insets = new Insets(0,0,0,0);
        mainPanel.add(pane, constraints);


    }

    public void refreshTable() {
        AttendanceSystemController controller = ControllerFactorySingleton.getInstance().createController();
        Map<String,String> students = controller.getAllStudentsByName();

        String date = AttendanceSelection.getInstance().dateOptions.getSelectedItem().toString();
        if (date.equals("Select Attendance")) {
            model.setRowCount(0);
            return;
        }
        model.setRowCount(0);

        for (Map.Entry<String, String> entry : students.entrySet()) {
            String name = entry.getKey();
            String uid = entry.getValue();
            String isPresent = String.valueOf(controller.isPresent(uid, date));

            model.addRow(new Object[] {
                name, 
                uid, 
                isPresent
            });
        }
    }

    public JPanel getPanel() {
        return mainPanel;
    }





    //TODO hude refactor for this later
    //im creating this as singleton for now


    static class SingletonHolder {
        private static final AttendanceSystemTable singleton = new AttendanceSystemTable();
    }

    static AttendanceSystemTable getInstance() {
        return SingletonHolder.singleton;   
    }


}
