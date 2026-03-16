package ui.gui.contents;

import java.util.List;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

import controllers.ControllerFactorySingleton;

public class AttendanceModeContent {
    private JPanel mainPanel = new JPanel();
    public CardLayout cardLayout = new CardLayout();
    private String[] views = {"Attendance Selection", "Attendance Mode"};

    private AttendanceModeContent() {
        AttendanceModeSelection attendanceModeView = AttendanceModeSelection.getInstance();
        mainPanel.add(attendanceModeView.getPanel(), views[0]);
    }
   
    public JPanel getPanel() {
        return mainPanel;
    }






    static class SingletonHolder {
        private static AttendanceModeContent attendanceModeContent = new AttendanceModeContent();
    }

    public static AttendanceModeContent getInstance() {
        return SingletonHolder.attendanceModeContent;
    }
}





class AttendanceMode extends Card {
    JLabel inputLabel = new JLabel("Attendance for ")
    AttendanceMode() {
        initComponents();
    }

    private void initComponents() {
        constraints.gridx = 0;
        
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}





class AttendanceModeSelection extends Card {
    
    Vector<String> dateList = new Vector<>();
    JComboBox<String> dateOptions = new JComboBox<>(dateList);
    JLabel description = new JLabel("Select Attendance: ");

    JPanel wrapper = new JPanel(new BorderLayout());



    private AttendanceModeSelection() {

        wrapper.add(mainPanel, BorderLayout.CENTER);

        initComponents();
        refreshDates();
        addEventHandlers();
    }


    
    private void initComponents() {

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 1;
        mainPanel.add(description, constraints);

        constraints.gridy = 1;
        mainPanel.add(dateOptions, constraints);
        
        
    }
    
    private void addEventHandlers() {
        

    } 


    private void refreshDates() {
        
        List<String> refreshedDateList = ControllerFactorySingleton.getInstance().createController().attendanceDateLists();
        
        dateList.clear();
        dateList.add("Select Attendance");


        for (String date : refreshedDateList) {
            dateList.add(date);
        }
    }


    public JPanel getPanel() {
        return wrapper;
    }

    static class  SingletonHolder {
        private static final AttendanceModeSelection attendanceModeView = new AttendanceModeSelection();
    }

    public static AttendanceModeSelection getInstance() {
        return SingletonHolder.attendanceModeView;
    }
}



