package main.java;

import java.util.List;

import main.java.models.User;
import main.java.models.dao.UserDaoImpl;
import main.java.utils.Dentre;
import main.java.utils.Prompts;

public class App {
    public static void main(String[] args) throws Exception {

        TableManager.createUserTable();

        int opt = Prompts.menuOption("Alta usuario", "Ver usuarios", "Cancelar");
        while(opt != 3) {
            switch (opt) {
                case 1: {
                    UserDaoImpl userDao = new UserDaoImpl();
                    User inserted = userDao.insert(new User( 
                        Dentre.texto("Ingresa email: "), 
                        Dentre.texto("Ingresa contraseña: ")
                    ));
                    System.out.println("Usuario Insertado, ID: " + inserted.getId());
                    break;
                }
                case 2: {
                    System.out.println("Trayendo usuarios... ");
                    UserDaoImpl userDao = new UserDaoImpl();
                    List<User> users = userDao.getAll();
                    for (User user : users) {
                        System.out.println("User: " + user.toString());
                    }
                    break;
                }
                case 3: {
    
                    break;
                }
                case 4: {
    
                    break;
                }
            }
            opt = Prompts.menuOption("Alta usuario", "Ver usuarios", "Cancelar");
        }
    }
}