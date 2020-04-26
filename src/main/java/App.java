package main.java;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import main.java.models.User;
import main.java.exceptions.DaoException;
import main.java.exceptions.NoTaskFoundException;
import main.java.exceptions.UserNotFoundException;
import main.java.models.Task;
import main.java.models.dao.UserDaoImpl;
import main.java.models.dao.TaskDaoImpl;
import main.java.utils.Dentre;
import main.java.utils.Prompts;

public class App {

	
	public static void main(String[] args) throws Exception {
        String [] userTypes = {"Transportist", "Ejecutive"};

        String [] opciones = {
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
        
        /**
         * Clase Temporal para crear la DB
         */
		TableManager.createUserTable();
		TableManager.createTaskTable();

        int opt = Prompts.menuOption(opciones);
        while(opt != opciones.length) {
            switch (opt) {
                case 1: {
                    int ut = Prompts.menuOption(userTypes);
                    UserDaoImpl userDao = new UserDaoImpl();
                    User inserted = userDao.insert(new User( 
                        Dentre.texto("Ingresa email: "), 
                        Dentre.texto("Ingresa contraseña: "),
                        userTypes[ut-1]
                    ));
                    System.out.println("Usuario Insertado, ID: " + inserted.getId());
                    break;
                }
                case 2: {
                    long userId = Dentre.largo("Ingrese id del usuario: ");
                    UserDaoImpl userDao = new UserDaoImpl();
                    try {
                        User user = userDao.get(userId);
                        user.setPassword(Dentre.texto("Ingresa una nueva contraseña: "));
                        int ut = Prompts.menuOption(userTypes);
                        user.setUserType(userTypes[ut-1]);    
                    } catch (UserNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 3: {
                    long userId = Dentre.largo("Ingrese id del usuario: ");
                    UserDaoImpl userDao = new UserDaoImpl();
                    try {
                        TaskDaoImpl taskDao = new TaskDaoImpl();

                        User user = userDao.get(userId);
                        taskDao.deleteByUserId(userId);
                        userDao.delete(user);
    
                    } catch (UserNotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                }
                case 4: {
                    System.out.println("Trayendo usuarios... ");
                    UserDaoImpl userDao = new UserDaoImpl();
                    List<User> users = userDao.getAll();
                    for (User user : users) {
                        System.out.println("User: " + user.toString());
                    }
                    break;
                }
                case 5: {
                	TaskDaoImpl taskDao = new TaskDaoImpl();
                    long userId = Dentre.largo("Ingrese id del usuario: ");
                    try {
                        Task task = new Task(userId);
                        task.setHours(Dentre.flotante("Ingrese horas a cargar: "));
                        task.setDate(new Date().toString());
                        taskDao.insert(task);
                    } catch (DaoException e) {
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 6: {
                	TaskDaoImpl taskDao = new TaskDaoImpl();
                    int deletedRows = taskDao.deleteAll();
                    System.out.println("Tareas eliminadas: " + deletedRows);
                    break;
                }
                case 7: {
                    TaskDaoImpl taskDao = new TaskDaoImpl();
                    long userId = Dentre.largo("Ingrese id del usuario: ");
                    try {
                        List<Task> tasks = taskDao.getTaskByUserId(userId);
                        for (Task task : tasks) {
                            System.out.println("Tarea: " + task.toString());
                        }
                    } catch (NoTaskFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 8: {
                    TaskDaoImpl taskDao = new TaskDaoImpl();
                    List<Task> tasks = taskDao.getAll();
                    if(tasks.size() <= 0 ) {
                        System.out.println("Todavia no se cargó ninguna tarea.");
                        break;
                    }
                    HashMap<Long, Float> honorarios = new HashMap<Long, Float>(); // userId, Horas

                    for (Task task : tasks) {
                        System.out.println("Tarea: " + task.toString());
                        
                        Float currentHours = honorarios.get(task.getUserId());
                        Float hours = currentHours != null ? currentHours + task.getHours() : task.getHours();
                        honorarios.put(task.getUserId(), hours);
                    }

                    for (Long userId : honorarios.keySet()) {

                        UserDaoImpl userDao = new UserDaoImpl();
                        User user = userDao.get(userId);

                        int reward = 1;
                        switch(user.getUserType()) {
                            case "Transportist": reward = 10; break;
                            case "Ejecutive": reward = 22; break;
                        }

                        Float horas = honorarios.get(userId);
                        Float honorario = (horas * reward / 1);
                        System.out.println(String.format("UserID: %d - Horas: %.2f, Honorarios: %.2f\n", userId, horas, honorario));
                    }
                }
            }
            opt = Prompts.menuOption(opciones);
        }
    }
}