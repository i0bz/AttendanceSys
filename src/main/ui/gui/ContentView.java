package ui.gui;

import ui.gui.contents.AttendanceManagementContent;
import ui.gui.contents.StudentManagementContent;

import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;

public class ContentView {
    StudentManagementContent studentManagementContent = new StudentManagementContent();
    AttendanceManagementContent attendanceManagementContent = new AttendanceManagementContent();


    public CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);


    public String[] views = {"Student Management", "Attendance Management", "Attendance System", "Attendance Mode"};



    ContentView() {

        mainPanel.add(studentManagementContent.getPanel(), views[0]);
        mainPanel.add(studentManagementContent.getPanel(), views[0]);
        mainPanel.add(attendanceManagementContent.getPanel(), views[1]);


        cardLayout.show(mainPanel, views[1]);
    }



    public JPanel getPanel() {
        return  mainPanel;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public String[] getCards() {
        return views;
    }
}
