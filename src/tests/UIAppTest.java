package tests;

import handler.LoginHandler;
import handler.MainHandler;

public class UIAppTest {
    
    /**
     * Sistema para cargar las horas de los empleados:
     *  Se necesita manejar la carga de horas de los empleados de una empresa.
     *  La idea es que haya varios grupos/modulos y que vayan cargando las horas semanales para cada una de las tareas que hayan realizado.  
     *  De esta manera a fin de mes, se podra hacer un cierre y calcular el total de las mismas, y en base a esos totales, calcular los honorarios para cada empleado.
     * 
     * 
     */

	public static void main(String[] args) throws Exception {

        /*
        LoginHandler handler = new LoginHandler();
        handler.initApp();*/

        // comenté el login para abrir directamente el main
        MainHandler handler2 = new MainHandler();
        handler2.initApp();
    }
}