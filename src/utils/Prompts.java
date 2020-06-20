package utils;

public class Prompts {

    /**
     * Crea muestra y solicita al usuario elegir una opción del menú vía consola.
     * @return opcion del menú seleccionada.
     */
    public static int menuOption(String... opcionesDelMenu) {
        System.out.println("\n> Menu de opciones:");

        int maxOption = opcionesDelMenu.length + 1;

        for (int i = 0; i < opcionesDelMenu.length; i++) {
            System.out.println(String.format("\t%d. %s", i+1, opcionesDelMenu[i]));
        }

        int opcion = Dentre.entero("< Elige una opción: ");
        while(opcion < 1 || opcion > maxOption) {
            opcion = Dentre.entero("> Opción Incorrecta. Elige una opción: ");
        }
        return opcion;
    }

}