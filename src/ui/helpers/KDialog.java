package ui.helpers;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class KDialog extends JOptionPane {
    /**
	 * Created by soknife.dev
	 */
    private static final long serialVersionUID = -3089811120117799389L;
    private JDialog dialog;

    public enum dialogType {
        INFO,
        WARNING,
        QUESTION,
        ERROR,
        MESSAGE
    }

    private int getInternalDialogType(dialogType type) {
        int dtype = JOptionPane.PLAIN_MESSAGE; // plain as default
        if(type == null) return dtype;

        switch(type) {
            case INFO:
                dtype = JOptionPane.INFORMATION_MESSAGE;
            case WARNING:
                dtype = JOptionPane.WARNING_MESSAGE;
            case QUESTION:
                dtype = JOptionPane.QUESTION_MESSAGE;
            case ERROR:
                dtype = JOptionPane.ERROR_MESSAGE;
            break;
            case MESSAGE:
                dtype = JOptionPane.PLAIN_MESSAGE;
            break;
        }
        return dtype;
    }


    public KDialog (String title, String message, dialogType type) {

        super(message);
        setMessageType(getInternalDialogType(type));
        dialog = this.createDialog(title);
        initUI();
    }

    public KDialog (String title, String message) {

        super(message);
        dialog = this.createDialog(title);
        initUI();
    }

    private void initUI() {
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
}