package ui.bars;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import ui.utility.ConstraintUtils;
import ui.wrappers.ContentView;

import java.awt.*;
import java.util.ArrayList;

public class NavigationBar {
    //Panel and Layout
    private final GridBagLayout layout = new GridBagLayout();
    private final JPanel mainPanel = new JPanel(layout);
    private final GridBagConstraints constraints = new GridBagConstraints();

    //Buttons
    private String[] labels; 
    private final ArrayList<JButton> buttons = new ArrayList<>();

    //button icons
    FlatSVGIcon studentManagementIcon = new FlatSVGIcon("images/calendar-user-svgrepo-com.svg", 24, 24);
    FlatSVGIcon attendanceManagementIcon = new FlatSVGIcon("images/calendar-plus-alt-svgrepo-com.svg", 24, 24);
    FlatSVGIcon systemIcon = new FlatSVGIcon("images/calendar-lines-pen-svgrepo-com.svg", 24, 24);
    FlatSVGIcon quickAttendanceIcon = new FlatSVGIcon("images/calendar-check-svgrepo-com.svg", 24, 24);


    //Border
    private final Color borderColor = Color.decode("#cecfd1");
    private final MatteBorder lineBorder = BorderFactory.createMatteBorder(0,0,0,1, borderColor);
    private final EmptyBorder padding = new EmptyBorder(10,10,10,10);
    private final CompoundBorder border = new CompoundBorder(lineBorder, padding);

    //Button
    private final Insets buttonGaps = new Insets(0,0,10,0);
    private final String buttonArc = "arc: 15";
    
    //External Shits
    private ContentView contents;



    public NavigationBar(ContentView contents) {
        this.contents = contents;
        labels = contents.getContainerNames();
        drawComponents();
    }

    private void drawComponents() {

        mainPanel.setBorder(border);
        createButtons();
        initBtnListeners();

        ConstraintUtils.setCoords(constraints,0, 0);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 1.0;
        constraints.weightx = 1.0;
        constraints.insets = buttonGaps;
        layout.setConstraints(buttons.get(0), constraints);
        constraints.weighty = 1.5;
        ConstraintUtils.setCoords(constraints,0, 1);
        layout.setConstraints(buttons.get(1), constraints);
        ConstraintUtils.setCoords(constraints,0, 2);
        layout.setConstraints(buttons.get(2), constraints);
        ConstraintUtils.setCoords(constraints,0, 3);
        layout.setConstraints(buttons.get(3), constraints);


        //Glue buttons to the top
        setConstraintCoords(0, 100);
        constraints.weighty = 15.0;
        constraints.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(Box.createVerticalGlue(), constraints);

        ConstraintUtils.reset(constraints);




        buttons.get(0).setIcon(studentManagementIcon);
        buttons.get(1).setIcon(attendanceManagementIcon);
        buttons.get(2).setIcon(systemIcon);
        buttons.get(3).setIcon(quickAttendanceIcon);

    }

    private void createButtons() {
        for(String label : labels) {
            buttons.add(new JButton(label));
        }
        for (JButton button : buttons) {
            button.putClientProperty("FlatLaf.style", buttonArc);
            button.setHorizontalAlignment(SwingConstants.LEFT);
            mainPanel.add(button, constraints);
        }
    }


    private void swapCards(int iterator) {
        contents.getCardLayout().show(contents.getPanel(), buttons.get(iterator).getText());
    }




    //  TODO Find a way to to use for loops here
    private void initBtnListeners() {
        buttons.get(0).addActionListener(e -> {
            swapCards(0);
        });
        buttons.get(1).addActionListener(e -> {
            swapCards(1);
        });
        buttons.get(2).addActionListener(e -> {
            swapCards(2);
        });
        buttons.get(3).addActionListener(e -> {
            swapCards(3);
        });
    }









    public JPanel getPanel() {
        return  mainPanel;
    }

    private void setConstraintCoords(int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
    }
}