package main.java;

import main.java.models.User;
import main.java.models.dao.UserDaoImpl;
import main.java.utils.Dentre;
import main.java.utils.Prompts;

public class App {
    public static void main(String[] args) throws Exception {

        int opt = Prompts.menuOption("Alta usuario", "Cancelar");
        switch (opt) {
            case 1: {
                UserDaoImpl userDao = new UserDaoImpl();
                boolean inserted = userDao.insert(new User(null, 
                    Dentre.texto("Ingresa email"), 
                    Dentre.texto("Ingresa contraseña"), 
                    Dentre.texto("Ingresa userType")
                ));
                System.out.println("inserted?" + inserted);
                break;
            }
            case 2: {

                break;
            }
            case 3: {

                break;
            }
            case 4: {

                break;
            }
        }
    }
}