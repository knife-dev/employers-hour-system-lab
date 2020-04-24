package main.java.utils;

import java.io.*;

public class Dentre {

	public static String texto(String mensaje)
	{
		try
		{
			System.out.print(mensaje);
			String entrada = new BufferedReader(
			new InputStreamReader( System.in)).readLine();
			return entrada;
		}
		catch(IOException e) {
			System.out.print(e); 
			System.exit(1);
			return e.toString(); 
		} 
	}
		

	/* Metodo que permite entrar UN caracter y, como el anterior, se le pasa como
	argumento el texto para solicitar dicho caracter */

	public static char caracter(String mensaje)
	{
		String aux = texto( mensaje );
		return aux.charAt(0);
	}

	public static int entero(String mensaje) 
	{ 
		Integer dato = Integer.parseInt(texto(mensaje)); 
		return dato.intValue();  
	} 
		
	public static long largo(String mensaje)
	{
		Long dato = Long.parseLong(texto(mensaje));
		return dato.longValue();

	}
		
	public static float flotante(String mensaje)
	{ 
		Float dato = Float.parseFloat(texto(mensaje)); 
		return dato.floatValue();  
	}

	public static double doble(String mensaje)
	{
		Double dato = Double.parseDouble(texto(mensaje));
		return dato.doubleValue();  
	}

}