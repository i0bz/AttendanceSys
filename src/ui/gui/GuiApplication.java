package ui.gui;

import javax.swing.*;
import java.awt.*;

public class GuiApplication {

    private final JFrame window = new JFrame("Attendance");
    private final BorderLayout borderLayout = new BorderLayout();
    private final JPanel mainContainer = new JPanel(borderLayout);

    public GuiApplication() {

        window.setSize(CONFIG.width, CONFIG.height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(mainContainer);
        window.setVisible(true);

        ContentPanel contentPanel = new ContentPanel(borderLayout, mainContainer);
        NavigationMenu navBar = new NavigationMenu(borderLayout, mainContainer);
        TitleBar titleBar = new TitleBar();

        mainContainer.add(titleBar, BorderLayout.NORTH);
        mainContainer.add(contentPanel, BorderLayout.CENTER);
        mainContainer.add(navBar, BorderLayout.WEST);




    }
}

class TitleBar extends JPanel {
    TitleBar() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new FlowLayout());
        JLabel title = new JLabel("Attendance System");

        add(title);
    }
}


class ContentPanel extends JPanel {

    ContentPanel(BorderLayout layout, JPanel mainContainer) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new CardLayout());

    }
}









class NavigationMenu extends JPanel {


    NavigationMenu(BorderLayout layout, JPanel container) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new GridLayout(9, 1));

        JButton studentManagementMenu = new JButton("Student Management");
        JButton attendanceManagementMenu = new JButton("Attendance Management");
        JButton attendanceSystemMenu = new JButton("Attendance System");

        add(studentManagementMenu);
        add(attendanceManagementMenu);
        add(attendanceSystemMenu);


    }
}
