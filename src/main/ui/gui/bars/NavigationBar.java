package ui.gui.bars;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import ui.gui.ContentView;

import java.awt.*;
import java.util.ArrayList;

public class NavigationBar {
    //Panel and Layout
    private final JPanel mainPanel = new JPanel(new GridBagLayout());
    private final GridBagConstraints constraints = new GridBagConstraints();

    //Buttons
    private String[] labels; 
    private final ArrayList<JButton> buttons = new ArrayList<>();





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
        labels = contents.getCards();
        initComponents();
    }

    private void initComponents() {

        mainPanel.setBorder(border);


        createButtons();
        initListeneters();


        //Glue buttons to the top
        setConstraintCoords(0, 100);
        constraints.weighty = 15.0;
        constraints.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(Box.createVerticalGlue(), constraints);

    }

    private void createButtons() {
        for(String label : labels) {
            buttons.add(new JButton(label));
        }

        int y = 0;
        for (JButton button : buttons) {
            constraints.insets = buttonGaps;
            button.putClientProperty("FlatLaf.style", buttonArc);

            if (y == 1) constraints.weighty = 1.5;
            else constraints.weighty = 1.0;

            setConstraintCoords(0, y++);
            constraints.fill = GridBagConstraints.BOTH;
            mainPanel.add(button, constraints);
        }



    }


    private void swapCards(int x) {
        contents.getCardLayout().show(contents.getPanel(), labels[x]);
    }


    private void initListeneters() {

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