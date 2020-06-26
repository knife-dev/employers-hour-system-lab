package ui.views.bases;

import javax.swing.JFrame;

public class BaseFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public BaseFrame() {
        this.onCreate();
        this.onCreateView();
        this.onViewCreated();
        reloadView();
    }

    
    public BaseFrame(boolean constructOnly) {
        this.onCreate();
        this.onCreateView();
        if(!constructOnly) {
            this.onViewCreated();
            reloadView();
        }
    }

    public void onCreate() {
        setSize(450, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
    }

    public void onCreateView() {

    }

    public void onViewCreated() {
        setVisible(true);
    }

    public void reloadView() {
        validate();
        revalidate();
        repaint();
    }

    public JFrame getFrame() {
        return this;
    }

}