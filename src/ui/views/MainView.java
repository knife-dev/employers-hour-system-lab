package ui.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import ui.helpers.UIHelper;
import ui.views.bases.BaseFrameView;

public class MainView extends BaseFrameView {

    private static final long serialVersionUID = 1L;

    // Panels
    private JPanel contentPanel;

    // Buttons
    private JButton dummyButton;

    // Listeners

    @Override
    public void onCreate() {
        super.onCreate();
        setTitle("Main menu - Employer!");
        setResizable(false); 
        setSize(250, 450);


        // Create Panel
        contentPanel = new JPanel(new MigLayout("wrap 1, align 50% 50%")); //

        // Create Buttons

    }

    @Override
    public void onCreateView() {
        super.onCreateView();
        
        // Add Elements to Panel
        String [] menuOptionStrings = {
            "Alta usuario", 
            "Editar usuario", 
            "Borrar usuario", 
            "Ver usuarios", 
            "Cargar horas", 
            "Borrar horas (Todos)", 
            "Obtener Tasks por userId", 
            "Calcular Honorarios (Todos)", 
            "Cancelar"
        };
        // Add Buttons to Panel
        for (String btext : menuOptionStrings) {
            JButton b = new JButton(btext);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // validate login
                    System.out.println("button clicked");
                }
            });
            contentPanel.add(b);
        }
        // Add Panel to View

        setContentPane(new JScrollPane(contentPanel));

    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
    }
}