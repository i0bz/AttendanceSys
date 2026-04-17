package ui.bars;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.formdev.flatlaf.ui.FlatLineBorder;
import controllers.AttendanceSystemController;
import ui.contents.exporter.ExporterDialog;
import ui.contents.importer.ImporterDialog;
import ui.utility.ConstraintUtils;
import ui.wrappers.ContentView;

import java.awt.*;
import java.util.ArrayList;

public class NavigationBar {
    private AttendanceSystemController controller;


    //Panel and Layout
    private final GridBagLayout layout = new GridBagLayout();
    private final JPanel mainPanel = new JPanel(layout);
    private final GridBagConstraints constraints = new GridBagConstraints();

    //Border
    private final Color borderColor = Color.decode("#cecfd1");
    private final MatteBorder lineBorder = BorderFactory.createMatteBorder(0,0,0,1, borderColor);
    private final EmptyBorder padding = new EmptyBorder(10,10,10,10);
    private final CompoundBorder border = new CompoundBorder(lineBorder, padding);


    //Buttons and configs
    private final String[] labels;
    private final ArrayList<JButton> buttons = new ArrayList<>();
    private final FlatLineBorder buttonBorders = new FlatLineBorder(new Insets(10,10,10,10), null, 0, 15);

    private final Insets buttonGaps = new Insets(0,0,10,0);
    private final String buttonArc = "arc: 15";
    private JButton latestClickedBtn;

    //button icons
    int iconSize = 35;
    private final FlatSVGIcon studentManagementIcon = new FlatSVGIcon("images/calendar-user-svgrepo-com.svg", iconSize, iconSize);
    private final FlatSVGIcon attendanceManagementIcon = new FlatSVGIcon("images/calendar-plus-alt-svgrepo-com.svg", iconSize, iconSize);
    private final FlatSVGIcon systemIcon = new FlatSVGIcon("images/calendar-lines-pen-svgrepo-com.svg", iconSize, iconSize);
    private final FlatSVGIcon quickAttendanceIcon = new FlatSVGIcon("images/calendar-check-svgrepo-com.svg", iconSize, iconSize);
    private final FlatSVGIcon importIcon = new FlatSVGIcon("images/import-svgrepo-com.svg", iconSize, iconSize);
    private final FlatSVGIcon exportIcon = new FlatSVGIcon("images/export-svgrepo-com.svg", iconSize, iconSize);
    private final FlatSVGIcon[] icons = {studentManagementIcon, attendanceManagementIcon, systemIcon, quickAttendanceIcon, importIcon, exportIcon};


    //Dependency Injected shits
    private final ContentView contents;


    //Export Import dialogs
    private final ExporterDialog exporterDialog = new ExporterDialog(controller);
    private final ImporterDialog importerDialog = new ImporterDialog(controller);

    public NavigationBar(AttendanceSystemController controller, ContentView contents) {
        this.controller = controller;
        this.contents = contents;
        labels = contents.getContainerNames();
        drawComponents();
    }



    //Component creation
    private void createButtons() {
        for(String label : labels) {
            buttons.add(new JButton(label));
        }
        for (JButton button : buttons) {
            button.putClientProperty("FlatLaf.styleClass", "h3");
            button.putClientProperty("FlatLaf.style", buttonArc);
            button.setHorizontalAlignment(SwingConstants.LEFT);
            button.setBorder(buttonBorders);
            button.setFocusPainted(false);
            mainPanel.add(button, constraints);
        }
        buttons.getFirst().setForeground(Color.WHITE);
        buttons.getFirst().setBackground(Color.decode("#006B3C"));
        icons[0].setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.WHITE));
    }
    private void initBtnListeners() {
        int iterator = 0;
        for (JButton button : buttons) {
            int index = iterator;
            if (iterator == 4) continue;
            button.addActionListener( e -> {
                swapCards(index);
                recolorButtons((JButton) e.getSource());
            });
            iterator++;
        }

        buttons.get(4).addActionListener(e -> importerDialog.showImportUI());
        buttons.get(5).addActionListener(e -> exporterDialog.showExportUI());
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
        ConstraintUtils.setCoords(constraints,0, 4);
        layout.setConstraints(buttons.get(4), constraints);
        ConstraintUtils.setCoords(constraints,0, 5);
        layout.setConstraints(buttons.get(5), constraints);


        //Glue buttons to the top
        ConstraintUtils.setCoords(constraints,0, 100);
        constraints.weighty = 15.0;
        constraints.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(Box.createVerticalGlue(), constraints);

        ConstraintUtils.reset(constraints);




        buttons.get(0).setIcon(studentManagementIcon);
        buttons.get(1).setIcon(attendanceManagementIcon);
        buttons.get(2).setIcon(systemIcon);
        buttons.get(3).setIcon(quickAttendanceIcon);
        buttons.get(4).setIcon(importIcon);
        buttons.get(5).setIcon(exportIcon);

    }


    //Button events actions
    private void swapCards(int iterator) {
        contents.getCardLayout().show(contents.getPanel(), buttons.get(iterator).getText());
    }
    private void recolorButtons(JButton inputSource){
        if (latestClickedBtn == null || !(latestClickedBtn.equals(inputSource))) {
            latestClickedBtn = inputSource;

            for (FlatSVGIcon icon : icons) {
                icon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.BLACK));
            }
            int i = 0;
            for (JButton button : buttons) {
                if (button.equals(inputSource))
                    icons[i].setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.WHITE));
                button.setForeground(Color.BLACK);
                button.setBackground(Color.decode("#f2f2f2"));

                i++;
            }

            inputSource.setForeground(Color.WHITE);
            inputSource.setBackground(Color.decode("#006B3C"));

            mainPanel.validate();
            mainPanel.repaint();
        }


    }


    //getter
    public JPanel getPanel() {
        return  mainPanel;
    }

}