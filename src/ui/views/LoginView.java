package ui.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import handler.LoginHandler;
import ui.helpers.KMessageDialog;
import ui.helpers.UIHelper;
import ui.views.bases.BaseFrameView;

public class LoginView extends BaseFrameView {

    private static final long serialVersionUID = 1L;

    private LoginHandler handler;

    public LoginView(LoginHandler handler) {
        super(true);
        this.handler = handler;
        this.onViewCreated();
    }

    // Action Commands
    public static final String LOGIN_COMMAND = "login";

    // Panels
    private JPanel contentPanel;

    // Labels
    private JLabel emailLabel;
    private JLabel passwordLabel;

    // TextFields
    private JTextField emailTextField;
    private JTextField passwordTextField;

    // Buttons
    private JButton submitButton;

    // Listeners

    public boolean performLogin() {
        return handler.authenticate(emailTextField.getText(), passwordTextField.getText());
    }


    @Override
    public void onCreate() {
        super.onCreate();
        setTitle("Login - Employer!");
        setResizable(false); 
        setSize(350, 150);


        // Create Panel
        contentPanel = UIHelper.createCenteredPanel(); //

        // Create Labels
        emailLabel    = UIHelper.createLabel("Email: ");
        passwordLabel = UIHelper.createLabel("Password: ");

        // Create TextFields
        emailTextField    = new JTextField(10);
        passwordTextField = new JTextField(10);

        // Create Buttons
        submitButton = new JButton("Login");
        submitButton.setActionCommand(LOGIN_COMMAND);
        submitButton.addActionListener(this.getActionListener());


    }

    @Override
    public void onCreateView() {
        super.onCreateView();
        
        // Add Elements to Panel

        /*
        JPanel statusPane = new JPanel(new MigLayout());
        statusPane.add(statusLabel);
        contentPanel.add(statusPane, "newline");

        JPanel loginPane = new JPanel(new MigLayout());
        contentPanel.add(statusPane, "newline");

        loginPane.add(emailLabel);
        loginPane.add(emailTextField, "wrap 5");

        loginPane.add(passwordLabel);
        loginPane.add(passwordTextField, "wrap 10");

        contentPanel.add(loginPane, "newline");*/

        
        // contentPanel.add(statusLabel);

        contentPanel.add(emailLabel);
        contentPanel.add(emailTextField, "wrap 5");

        contentPanel.add(passwordLabel);
        contentPanel.add(passwordTextField, "wrap 10");

        // Add Buttons to Panel
        contentPanel.add(submitButton, "newline");

        // Add Panel to View
        setContentPane(contentPanel);

    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
    }
}