package main.java;

import java.util.List;

import main.java.models.User;
import main.java.models.Task;
import main.java.models.dao.UserDaoImpl;
import main.java.models.dao.TaskDaoImpl;
import main.java.utils.Dentre;
import main.java.utils.Prompts;

public class App {

	
	public static void main(String[] args) throws Exception {

		String [] opciones = {"Alta usuario", "Ver usuarios", "Cargar horas", "Cancelar"};
		
		TableManager.createUserTable();

        int opt = Prompts.menuOption(opciones);
        while(opt != opciones.length) {
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
                	TaskDaoImpl taskDao = new TaskDaoImpl();
                    List<Task> tasks = taskDao.getAll();
                    long id = Dentre.largo("Ingrese id del usuario: ");
                    for (Task task : tasks) {
                        if (task.getId() == id ) {
                        	System.out.println("Usuario encontrado!");
                        	task.setHours(Dentre.flotante("Ingrese horas a cargar: "));
                        	taskDao.update(task);
                        }else {
                        	System.out.println("Usuario no encontrado!");
                        }
                    }                	
    
                    break;
                }
                case 4: {
    
                    break;
                }
            }
            opt = Prompts.menuOption(opciones);
        }
    }
}