package ui.views.bases;

import javax.swing.JFrame;
import javax.swing.JPanel;

import handler.MainHandler;

public class BasePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    protected MainHandler handler;

    public BasePanel(MainHandler handler) {
        super();
        this.handler = handler;
        this.onCreate();
        this.onCreatePanel();
        this.onPanelCreated();
        // reloadView();
    }

    public void onCreate() {

    }

    public void onCreatePanel() {

    }

    public void onPanelCreated() {
        setVisible(true);
    }

    public void reloadView() {
        validate();
        revalidate();
        repaint();
    }

}