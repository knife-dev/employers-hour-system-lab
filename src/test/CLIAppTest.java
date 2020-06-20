package test;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import entities.User;
import exceptions.EmployerException;
import exceptions.NoTaskFoundException;
import exceptions.UserNotFoundException;
import entities.Task;
import dao.daoimpl.UserDaoImpl;
import db.TableManager;
import dao.daoimpl.TaskDaoImpl;
import utils.Dentre;
import utils.Prompts;

public class CLIAppTest {

	public static void main(String[] args) throws Exception {

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
                    UserDaoImpl userDao = new UserDaoImpl();
                    User inserted = userDao.insert(new User( 
                        Dentre.texto("Ingresa email: "), 
                        Dentre.texto("Ingresa contraseña: ")
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
                        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                        task.setDate(formatter.format(new Date()));
                        taskDao.insert(task);
                    } catch (EmployerException e) {
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
                    Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String hoy = formatter.format(new Date());

                    TaskDaoImpl taskDao = new TaskDaoImpl();
                    List<Task> tasks = taskDao.getTasksByDate(  hoy  );
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

                        int reward = 1;
                        Float horas = honorarios.get(userId);
                        Float honorario = (horas * reward / 1);
                        System.out.println(String.format("UserID: %d - %s - Horas: %.2f, Honorarios: %.2f\n", userId, hoy, horas, honorario));
                    }
                }
            }
            opt = Prompts.menuOption(opciones);
        }
    }
}