package main;

import java.util.Scanner;

public class Principal {

	public static int cantEscritores;
	public static int cantLectores;
	public static int tiempoLectura;
	public static int tiempoEscritura;
	public static int tiempoSleepEsc;
	public static int tiempoSleepLec;
	public static int intervaloInicio;
	public static int id=0;
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);  // Create a Scanner object
	    
		System.out.println("Ingresar cantidad de Lectores");
	    int cantLectores = scan.nextInt();  // Read user input
	    System.out.println("Cantidad de Lectores = " + cantLectores);  // Output user input
 
		System.out.println("Ingresar cantidad de Escritores");
	    int cantEscritores = scan.nextInt();  // Read user input
	    System.out.println("Cantidad de Lectores = " + cantEscritores);  // Output user input	    
	    
		System.out.println("Ingresar tiempo de lectura");
	    int tiempoLectura = scan.nextInt();  // Read user input
	    System.out.println("tiempo de Lectura = " + tiempoLectura);  // Output user input	   
	    
		System.out.println("Ingresar tiempo de escritura");
	    int tiempoEscritura = scan.nextInt();  // Read user input
	    System.out.println("tiempo de Escritura = " + tiempoEscritura);  // Output user input	   
			    
	    System.out.println("Ingresar el intervalo de tiempo de inicializacion que van a tener los hilos");
	    int intervaloInicio = scan.nextInt();  // Read user input
	    System.out.println("Intervalo de inicializacion de hilos = " + intervaloInicio);  // Output user input	  
	    
	    
		BasedeDatos bD=new BasedeDatos(tiempoEscritura, tiempoLectura);
		Escritor[] escritor=new Escritor[cantEscritores];
		Lector[] lector=new Lector[cantLectores];
		
		
		
		// TODO implementar tiempos de inicio

		for (int i=0; i<escritor.length; i++) {
			id++;
			escritor[i]= new Escritor(bD, id, tiempoEscritura, tiempoSleepEsc);
		}
		
		for (int i=0; i<lector.length; i++) {
			id++;
			lector[i]= new Lector(bD, id, tiempoLectura , tiempoSleepLec);
		}
		
		
		// TODO Implementar iniciodeHilos con arreglo
		// inicio=0
		for (int i=0; i<escritor.length; i++) {
			escritor[i].start();
			//delay(inicio + rnd)
			//inicio=rnd
		}
		
		for (int i=0; i<lector.length; i++) {
			lector[i].start();
		}
		
	}

}
