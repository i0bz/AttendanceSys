package ui.gui.contents;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import controllers.ControllerBootstrapSingleton;

public class AttendanceModeContent {
    public CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    private String[] views = {"Attendance Selection", "Attendance Mode"};

    public AttendanceModeContent() {
        AttendanceMode attendanceMode = new AttendanceMode(mainPanel, cardLayout, views);
        AttendanceModeSelection attendanceModeSelection = new AttendanceModeSelection(mainPanel, cardLayout, views, attendanceMode);


        mainPanel.add(attendanceModeSelection.getPanel(), views[0]);
        mainPanel.add(attendanceMode.getPanel(), views[1]);
        cardLayout.show(mainPanel, views[0]);
    }
   
    public JPanel getPanel() {
        return mainPanel;
    }

}



class AttendanceModeSelection extends Card {

    Vector<String> dateList = new Vector<>();
    JComboBox<String> dateOptions = new JComboBox<>(dateList);
    JLabel description = new JLabel("Select Attendance Sheet");
    JPanel wrapper = new JPanel(new GridBagLayout());

    JPanel parentPanel;
    String[] parentViews;
    CardLayout parentCardLayout;

    AttendanceMode attendanceMode;



    AttendanceModeSelection(JPanel panel, CardLayout layout, String[] views, AttendanceMode attendanceMode) {
        parentCardLayout = layout;
        parentViews = views;
        parentPanel = panel;
        this.attendanceMode = attendanceMode;

        super.padding = new FlatEmptyBorder(10,0,0,0);
        super.border = new CompoundBorder(line_border, padding);

        mainPanel.setBorder(border);

        wrapper.add(mainPanel, constraints);
        refreshDates();
        initComponents();
        addEventHandlers();
    }


    
    private void initComponents() {

        mainPanel.setPreferredSize(new Dimension(700,150));

        FlatEmptyBorder padding = new FlatEmptyBorder(0,20,20,15);
        CompoundBorder descriptionBorder = new CompoundBorder(BorderFactory.createMatteBorder(0,0,1,0,borderColor), padding);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10,0,0,0);
        description.setBorder(descriptionBorder);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.ipadx = 10;
        mainPanel.add(description, constraints);



        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(10,10,10,10);
        constraints.weighty = 1.5;
        constraints.gridy = 1;
        mainPanel.add(dateOptions, constraints);

        dateOptions.setSelectedIndex(0);
        
    }
    
    private void addEventHandlers() {

        ControllerBootstrapSingleton.getInstance().registry().addPropertyChangeListener(evt -> {
            refreshDates();
        });

        dateOptions.addActionListener(evt -> {
            if (dateOptions.getSelectedItem().equals("Select Attendance")) return;
            attendanceMode.currentDate = dateOptions.getSelectedItem().toString();
            attendanceMode.activeDate.setText("Active Date: " + dateOptions.getSelectedItem());
            parentCardLayout.show(parentPanel, parentViews[1]);
            dateOptions.setSelectedIndex(0);
        });

    } 

    private void refreshDates() {
        List<String> refreshedDateList = ControllerBootstrapSingleton.getInstance().getController().attendanceDateLists();
        
        dateList.clear();
        dateList.add("Select Attendance");
        dateList.addAll(refreshedDateList);
    }


    public JPanel getPanel() {
        return wrapper;
    }
}



class AttendanceMode extends Card {
    JPanel activeDateInfo = new JPanel(new GridBagLayout());
    JPanel attendanceEntry = new JPanel(new GridBagLayout());

    JLabel activeDate = new JLabel("");
    JButton changeDateBtn = new JButton("Change");
    JLabel description = new JLabel("Quick Attendance Entry");
    JLabel inputDescription = new JLabel("Enter Student UID:");
    JTextField inputField = new JTextField();
    JButton markPresentBtn = new JButton("Toggle Attendance");

    String currentDate = "";

    JPanel parentPanel;
    String[] parentViews;
    CardLayout parentCardLayout;


    AttendanceMode(JPanel panel, CardLayout layout, String[] views) {
        parentCardLayout = layout;
        parentViews = views;
        parentPanel = panel;





        initComponents();
        addEventHandlers();
    }


    private void addEventHandlers() {

        changeDateBtn.addActionListener(evt -> {
            parentCardLayout.show(parentPanel, parentViews[0]);
        });


        inputField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inputField.getText().equals("Student not enrolled!") || inputField.getText().equals("Invalid UID!"))
                    inputField.setText("");
                inputField.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });

        markPresentBtn.addActionListener(evt -> {
            markPresent();
        });

        inputField.addActionListener(evt -> {
            markPresent();
        });


    }


    private void markPresent() {
        String uid = inputField.getText();
        try {
            ControllerBootstrapSingleton.getInstance().getController().toggleAttendance(uid, currentDate);
        } catch (NoSuchElementException e) {
            inputField.setText("Student not enrolled!");
            inputField.setForeground(Color.RED);
            return;
        } catch (RuntimeException e) {
            inputField.setText("Invalid UID!");
            inputField.setForeground(Color.RED);
            return;
        }
        JOptionPane.showMessageDialog(null ,"Welcome " + ControllerBootstrapSingleton.getInstance().getController().getStudentName(uid));
        inputField.setText("");
    }

    private void initComponents() {
        attendanceEntry.setPreferredSize(new Dimension(700, 150));
        activeDateInfo.setPreferredSize(new Dimension(700, 50));

        mainPanel.setBorder(new FlatEmptyBorder(0,0,0,0));

        attendanceEntry.setBorder(line_border);
        activeDateInfo.setBorder(line_border);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(20,20,20,20);

        mainPanel.add(activeDateInfo, constraints);
        constraints.gridy = 1;
        constraints.insets = new Insets(0,20,20,20);
        mainPanel.add(attendanceEntry, constraints);



        constraints.insets = new Insets(10,160,10,50);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridy = 0;
        activeDate.setHorizontalAlignment(SwingConstants.CENTER);
        activeDateInfo.add(activeDate, constraints);

        constraints.insets = new Insets(10,0,10,50);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 2;
        activeDateInfo.add(changeDateBtn, constraints);

        constraints.insets = new Insets(10,0,10,0);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        CompoundBorder descBorder = new CompoundBorder(BorderFactory.createMatteBorder(0,0,1,0, borderColor), new FlatEmptyBorder(0,15,5,15));
        description.setBorder(descBorder);
        constraints.weightx = 1;
        constraints.gridx = 0;
        attendanceEntry.add(description, constraints);


        inputDescription.setHorizontalAlignment(SwingConstants.CENTER);
        constraints.insets = new Insets(5,10,5,10);
        constraints.weightx = 0;
        constraints.gridy = 1;
        attendanceEntry.add(inputDescription, constraints);


        constraints.insets = new Insets(5,200,5,200);
        constraints.gridy = 2;
        attendanceEntry.add(inputField, constraints);

        constraints.insets = new Insets(5,10,5,10);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridy = 3;
        attendanceEntry.add(markPresentBtn, constraints);


    }




    public JPanel getPanel() {
        return mainPanel;
    }
}



