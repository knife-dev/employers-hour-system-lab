package ui.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

import dao.daoimpl.UserDaoImpl;
import entities.User;
import exceptions.EmployerException;
import net.miginfocom.swing.MigLayout;
import ui.helpers.UIHelper;
import ui.models.UserTableModel;
import ui.views.bases.BaseFrameView;

public class UsersControlView extends BaseFrameView {

    private static final long serialVersionUID = 1L;

    /*
     * Menu del programa: Usuarios: Crear - Tipo Transportist/Sanitation Editar
     * Buscar Borrar Tareas: Añadir - incluye horas! Editar Buscar Borrar
     * Honorarios: Calcular - based on worker type
     */

    // DAO
    private UserDaoImpl userDaoImpl;

    // Panels
    private JPanel contentPanel;
    private JPanel editorPanel;
    private JPanel crudPanel;
    private JPanel tablePanel;

    // Labels
    private JLabel emailLabel; // editorPanel
    private JLabel passwordLabel; // editorPanel
    private JLabel roleLabel; // editorPanel

    // TextFields
    private JTextField emailTextField; // editorPanel
    private JTextField passwordTextField; // editorPanel
    private JTextField roleTextField; // editorPanel

    // Buttons
    private JButton addButton; // crudPanel
    private JButton updateButton; // crudPanel
    private JButton deleteButton; // crudPanel

    // Tables
    private JTable usersTable;
    private JScrollPane usersTableScroll;

    // Listeners

    @Override
    public void onCreate() {
        super.onCreate();
        setTitle("Users Control - Employer!");
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
        setSize(550, 500);

        userDaoImpl = new UserDaoImpl();

        // Create Panel
        contentPanel = new JPanel(new MigLayout("wrap 1, align 50% 50%")); //
        editorPanel = new JPanel(new MigLayout("wrap 1, align 50% 50%")); //
        crudPanel = new JPanel(new MigLayout("wrap 3, align 50% 50%")); //
        tablePanel = new JPanel(new MigLayout("align 50% 50%")); //

        // Create Table
        usersTable = new JTable();
        usersTableScroll = new JScrollPane(usersTable);

        usersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        usersTable.getSelectionModel().addListSelectionListener(e -> onUserTableSelection(e));

        // create editorPanel Labels
        emailLabel = new JLabel("Email");
        passwordLabel = new JLabel("Password");
        roleLabel = new JLabel("Role");

        // create editorPanel TextFields
        emailTextField = new JTextField(30);
        passwordTextField = new JTextField(20);
        roleTextField = new JTextField(10);

        // Create crudPanel Buttons
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");

        // Add crudPanel Buttons action listeners
        addButton.addActionListener(e -> add());
        updateButton.addActionListener(e -> update());
        deleteButton.addActionListener(e -> delete());

    }

    @Override
    public void onCreateView() {
        super.onCreateView();

        // Add Labels & TextFields to editorPanel
        editorPanel.add(emailLabel);
        editorPanel.add(emailTextField);

        editorPanel.add(passwordLabel);
        editorPanel.add(passwordTextField);

        editorPanel.add(roleLabel);
        editorPanel.add(roleTextField);

        // Add Buttons to Panel
        crudPanel.add(addButton);
        crudPanel.add(updateButton);
        crudPanel.add(deleteButton);

        tablePanel.add(usersTableScroll);

        // Add Panels to contentPane
        contentPanel.add(editorPanel);
        contentPanel.add(crudPanel);
        contentPanel.add(tablePanel);

        // Add Panel to View
        setContentPane(new JScrollPane(contentPanel));
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        // Load users
        List<User> users = new ArrayList<>();
        try {
            users = userDaoImpl.getAll();
            System.out.println(users);
        } catch (EmployerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Error getting users: " + e.getMessage());
        }
        usersTable.setModel(new UserTableModel(users));
    }
    
    private void onUserTableSelection(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() || usersTable.getSelectedRow() < 0) return;

        UserTableModel tableModel = (UserTableModel) usersTable.getModel();
        User selectedUser = tableModel.getUser(usersTable.getSelectedRow());
        System.out.println(selectedUser);
        emailTextField.setText(selectedUser.getEmail());
        passwordTextField.setText(selectedUser.getPassword());
        roleTextField.setText(selectedUser.getRole());
    }

    private void add() {
        UserTableModel tableModel = (UserTableModel) usersTable.getModel();

        String email    = emailTextField.getText();
        String password = passwordTextField.getText();
        String role     = roleTextField.getText();

        User newUser = new User(email, password, role);

        try {
            newUser = userDaoImpl.insert(newUser);
            tableModel.addUser(newUser);
        } catch (EmployerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void update() {
        System.out.println("update");
        UserTableModel tableModel = (UserTableModel) usersTable.getModel();
        User selectedUser = tableModel.getUser(usersTable.getSelectedRow());

        String email    = emailTextField.getText();
        String password = passwordTextField.getText();
        String role     = roleTextField.getText();


        try {
            selectedUser.setEmail(email);
            selectedUser.setPassword(password);
            selectedUser.setRole(role);

            int affectedRows = userDaoImpl.update(selectedUser);
            if(affectedRows > 0) {
                tableModel.notifyDataChanged();
            }
        } catch (EmployerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void delete() {
        System.out.println("delete");
        UserTableModel tableModel = (UserTableModel) usersTable.getModel();
        User selectedUser = tableModel.getUser(usersTable.getSelectedRow());
        try {
            int affectedRows = userDaoImpl.delete(selectedUser);
            if(affectedRows > 0) {
                tableModel.removeUser(usersTable.getSelectedRow());
                tableModel.notifyDataChanged();
            }
        } catch (EmployerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}