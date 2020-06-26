package ui.views;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import handler.MainHandler;
import net.miginfocom.swing.MigLayout;
import ui.views.bases.BaseFrame;

public class MainFrame extends BaseFrame {

    private static final long serialVersionUID = 1L;

    private MainHandler handler;
    private MenuBar mb;

    public MainFrame(MainHandler handler) {
        super(true);
        this.handler = handler;
        this.mb = new MenuBar(handler);
        setJMenuBar(mb);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setTitle("Employer Manager S.A");
        setSize(600, 400);
        setLayout(new MigLayout("wrap 1, align 50% 50%"));
    }

    @Override
    public void onCreateView() {
        super.onCreateView();
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        this.mb.setVisible(true);
    }

}