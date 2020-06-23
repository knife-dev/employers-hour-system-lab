package test;

import ui.views.LoginView;
import ui.views.MainView;
import ui.views.UsersControlView;

public class UIAppTest {
    
    /**
     * Sistema para cargar las horas de los empleados:
     *  Se necesita manejar la carga de horas de los empleados de una empresa.
     *  La idea es que haya varios grupos/modulos y que vayan cargando las horas semanales para cada una de las tareas que hayan realizado.  
     *  De esta manera a fin de mes, se podra hacer un cierre y calcular el total de las mismas, y en base a esos totales, calcular los honorarios para cada empleado.
     */

	public static void main(String[] args) throws Exception {

        new MainView();
        // new UsersControlView();
        
    }
}