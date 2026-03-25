package ui.utility;

import java.awt.*;

public class ConstraintUtils {
    public static void setCoords(GridBagConstraints constraints, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
    }
    public static void setWidth(GridBagConstraints constraints, int width) {
        constraints.gridwidth = width;
    }
    public static void setHeight(GridBagConstraints constraints, int height) {
        constraints.gridheight = height;
    }

    public static void reset(GridBagConstraints constraints) {
        setCoords(constraints, 0, 0);
        setWidth(constraints, 1);
        setHeight(constraints, 1);
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.insets = new Insets(0,0,0,0);
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
    }
}
