package ui.views.bases;

import javax.swing.JFrame;

public class BaseFrameView extends JFrame {
    private static final long serialVersionUID = 1L;

    public BaseFrameView() {
        this.onCreate();
        this.onCreateView();
        this.onViewCreated();
    }

    public void onCreate() {
        // setTitle("Untitled View"); 
        setSize(450, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setResizable(true); 

    }

    public void onCreateView() {

    }

    public void onViewCreated() {
        reloadView();
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