package ui.views;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import handler.MainHandler;
import java.awt.event.ActionEvent;

public class MenuBar extends JMenuBar {

    public MainHandler handler;

    public MenuBar(MainHandler handler) {
        super();
        this.handler = handler;

        JMenu usersMenu = new JMenu("Users");
        JMenu helpMenu  = new JMenu("Help");

        JMenuItem userControlItem = new JMenuItem("User Control");
        userControlItem.addActionListener(e -> handler.showUserControlPanel());

        JMenuItem exitItem        = new JMenuItem("Exit");
        exitItem.addActionListener(e -> handler.exitApp());


        add(usersMenu);
        add(helpMenu);
        usersMenu.add(userControlItem);
        helpMenu.add(exitItem);
    }

}