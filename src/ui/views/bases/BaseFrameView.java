package ui.views.bases;

import javax.swing.JFrame;

import ui.listeners.OnClickListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BaseFrameView extends JFrame {
    private static final long serialVersionUID = 1L;
    private ActionListener  actionListener;
    private OnClickListener clickListener;

    public BaseFrameView() {
        this.onCreate();
        this.onCreateView();
        this.onViewCreated();
        reloadView();
    }

    
    public BaseFrameView(boolean constructOnly) {
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
        setVisible(false);
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clickListener != null) {
                    clickListener.OnClick(e, e.getActionCommand());
                }
            }
        };

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

    public void setOnClickListener(OnClickListener listener) {
        this.clickListener = listener;
    }

    protected ActionListener getActionListener() {
        return this.actionListener;
    }

}