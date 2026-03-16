package ui.gui.contents;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import controllers.AttendanceSystemController;
import controllers.ControllerBootstrapSingleton;
import repository.StudentRoster;
import ui.gui.contents.components.StudTableBtnEditor;
import ui.gui.contents.components.TableBtnRenderer;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Map;




public class StudentManagementContent {
    private JPanel mainPanel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();

    public  StudentManagementContent() {
        initComponents();
    }

    private void initComponents() {

        EnrollForm enrollForm = new EnrollForm();
        StudentTable studentTable = new StudentTable();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(20,20,0,20);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        mainPanel.add(enrollForm.getPanel(), constraints);

        constraints.gridy = 1;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(20,20,20,20);
        mainPanel.add(studentTable.getPanel(), constraints);





    }

    public JPanel getPanel() {
        return mainPanel;
    }
}

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
        AttendanceSystemController attendanceSystemController = ControllerBootstrapSingleton.getInstance().getController();
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

class EnrollForm extends Card {
    
    JTextField studentIdInput = new JTextField(9);
    JTextField studentNameInput = new JTextField(9);
    JButton enrollButton = new JButton("Enroll");

    EnrollForm() {
            initComponents();
            addEventHandling();
    }


    public void addEventHandling() {

        enrollButton.addActionListener(e -> {
            enrollingStudent();
        });

        studentIdInput.addActionListener(e -> {
            enrollingStudent();
            studentIdInput.getParent().requestFocusInWindow();
        });

        studentNameInput.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (studentNameInput.getText().equals("Empty Name!!!")) {
                    studentNameInput.setText("");
                    studentNameInput.setForeground(Color.BLACK);
                }
            }
        });



        studentIdInput.setText("YY-CCXXXX");
        studentIdInput.setForeground(Color.GRAY);
        studentIdInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (studentIdInput.getText().equals("YY-CCXXXX") || studentIdInput.getText().equals("Invalid ID!")) {
                    studentIdInput.setText("");
                    studentIdInput.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (studentIdInput.getText().isEmpty()) {
                    studentIdInput.setText("YY-CCXXXX");
                    studentIdInput.setForeground(Color.GRAY);
                }
            }
        });


    }

    public void enrollingStudent() {
        AttendanceSystemController controller = ControllerBootstrapSingleton.getInstance().getController();
        String studId = studentIdInput.getText();
        String name = studentNameInput.getText();
        if (name.trim().isEmpty()) {
            studentNameInput.setText("Empty Name!!!");
            studentNameInput.setForeground(Color.RED);
            return;
        }
        try {
            controller.enrollStudent(name, studId);
        } catch (RuntimeException e) {
            studentNameInput.setText("");
            studentIdInput.setText("Invalid ID!");
            studentIdInput.setForeground(Color.RED);
            return;
        }

        studentNameInput.setText("");
        studentIdInput.setText("YY-CCXXXX");
        studentIdInput.setForeground(Color.GRAY);
    }


    public void initComponents() {

        JLabel enrollLabel = new JLabel("Enroll new student");
        JLabel studentNameLabel = new JLabel("Student Name:");
        JLabel studentIDLabel = new JLabel("Student ID:");

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        enrollLabel.setHorizontalAlignment(SwingConstants.LEFT);
        mainPanel.add(enrollLabel, constraints);

        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 1.0;
        mainPanel.add(studentNameLabel, constraints);

        constraints.gridx = 1;
        mainPanel.add(studentIDLabel, constraints);
        
        studentIdInput.putClientProperty("FlatLaf.style", "arc: 10");
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridy = 2;
        mainPanel.add(studentIdInput, constraints);

        studentNameInput.putClientProperty("FlatLaf.style", "arc: 10");
        constraints.insets = new Insets(0, 0, 0, 10);
        constraints.gridx = 0;
        mainPanel.add(studentNameInput, constraints);


        enrollButton.putClientProperty("FlatLaf.style", "arc: 10");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(10, 0, 0, 0);
        mainPanel.add(enrollButton, constraints);

    }

    public JPanel getPanel() {
        return mainPanel;
    }
}