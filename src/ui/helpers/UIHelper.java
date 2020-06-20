package ui.helpers;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class UIHelper {
    

    public static JLabel createLabel(String name) {
        JLabel label = new JLabel();
        label.setText(name);
        return label;
    }

    public static JPanel createCenteredPanel() {
        return new JPanel(new MigLayout("wrap 2, align 50% 50%"));
    }

}