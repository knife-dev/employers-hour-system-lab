package ui.views;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import handler.MainHandler;
import net.miginfocom.swing.MigLayout;
import ui.views.bases.BaseFrameView;

public class MainView extends BaseFrameView {

    private static final long serialVersionUID = 1L;

    private MainHandler handler;

    public MainView(MainHandler handler) {
        super(true);
        this.handler = handler;
        this.onViewCreated();
    }

    /**
     * Hay un problema... los honorarios dependen del rol, y puede ser que con el
     * tiempo alguien sea ascendido, aun asi, deberían poder calcularse los
     * honorarios según el rol que haya tenido a lo largo del tiempo. Podría añadir
     * un campo "role" al modelo User, de esa forma cuando se carguen o añadan
     * Tasks, se pueda registrar "role" en la task, También, cuando se vaya a
     * iniciar sesión dependiendo del role, será la tabla en la que se busque al
     * usuario: Transportist, Sanitation, etc. ¿Qué pasa si el userId no esta en las
     * tablas Transportist/Sanitation? entonces resulta usuario NO VALIDO, pero no
     * debería suceder por el FOREIGN KEY ON DELETE CASCADE, ON UPDATE CASCADE
     * 
     * 
     * Menu del programa: Usuarios: Crear - Tipo Transportist/Sanitation Editar
     * Buscar Borrar Tareas: Añadir - incluye horas! Editar Buscar Borrar
     * Honorarios: Calcular - based on worker type
     */

    // Action commands
    public static final String USERS_COMMAND = "users";
    public static final String TASKS_COMMAND = "tasks";
    public static final String FEES_COMMAND  = "FEES";

    // Panels
    private JPanel contentPanel;

    // Buttons
    private JButton usersButton;
    private JButton tasksButton;
    private JButton feesButton;

    // Listeners

    // private ActionListener onTasksClickListener;
    // private ActionListener onFeesClickListener;

    @Override
    public void onCreate() {
        super.onCreate();
        setTitle("Main menu - Employer!");
        setResizable(false);
        setSize(250, 450);

        // Create Panel
        contentPanel = new JPanel(new MigLayout("wrap 1, align 50% 50%")); //

        // Create Buttons
        usersButton = new JButton("Usuarios");
        usersButton.setActionCommand(USERS_COMMAND);
        usersButton.addActionListener(this.getActionListener());

        tasksButton = new JButton("Tareas");
        tasksButton.setActionCommand(TASKS_COMMAND);
        tasksButton.addActionListener(this.getActionListener());

        feesButton  = new JButton("Honorarios");
        feesButton.setActionCommand(FEES_COMMAND);
        feesButton.addActionListener(this.getActionListener());
    }

    @Override
    public void onCreateView() {
        super.onCreateView();
    
        // Add Buttons to Panel
        contentPanel.add(usersButton);
        contentPanel.add(tasksButton);
        contentPanel.add(feesButton);

        // Add Panel to View
        setContentPane(new JScrollPane(contentPanel));
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
    }

}