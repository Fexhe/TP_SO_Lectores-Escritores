package main;

import java.util.Collection;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

// TODO Faltan hacer Colas (Chequear si se puede con las de los semaforos), Agregar tiempo en la tabla, Terminar documentacion

public class Principal {
	public static int nEscritores=0;
	public static int nLectores=0;
	public static int contEscritores=0;
	public static int contLectores=0;
	public static Semaphore mutex= new Semaphore(1);
	public static Semaphore wrt= new Semaphore(1);

	public static int baseDatos;
	public static int cantEscritores;
	public static int cantLectores;
	public static int tiempoLectura;  //Tiempo minimo de 600
	public static int tiempoEscritura;  //Tiempo minimo de 600
	public static int tiempoSleepEsc;  //Tiempo minimo de 2000
	public static int tiempoSleepLec;  //Tiempo minimo de 2000
	public static int intervaloInicio; 
	public static int id=0;

	public static void main(String[] args) {
		
		cantEscritores=2;
		cantLectores=10;
		tiempoLectura=500;
		tiempoEscritura=1000;
		tiempoSleepEsc=3000;
		tiempoSleepLec=6000;
		intervaloInicio=2000;
		
		/* 
		 * Se pueden ingresar los parametros iniciales por Consola
		 * 
		Scanner scan = new Scanner(System.in);  // Create a Scanner object
	    
		System.out.println("Ingresar cantidad de Lectores");
	    int cantLectores = scan.nextInt();  // Read user input
 
		System.out.println("Ingresar cantidad de Escritores");
	    int cantEscritores = scan.nextInt();  // Read user input   
	    
		System.out.println("Ingresar tiempo de lectura");
	    int tiempoLectura = scan.nextInt();  // Read user input  
	    
		System.out.println("Ingresar tiempo sleep de lectura");
	    int tiempoSleepLec = scan.nextInt();  // Read user input 
	    
		System.out.println("Ingresar tiempo de escritura");
	    int tiempoEscritura = scan.nextInt();  // Read user input   
		
	    System.out.println("Ingresar tiempo sleep de escritura");
	    int tiempoSleepEsc = scan.nextInt();  // Read user input  
			    
	    System.out.println("Ingresar el intervalo de tiempo de inicializacion que van a tener los hilos");
	    int intervaloInicio = scan.nextInt();  // Read user input  
	    */
	    
		//BasedeDatos bD=new BasedeDatos(tiempoEscritura, tiempoLectura);
		Escritor[] escritor=new Escritor[cantEscritores];
		Lector[] lector=new Lector[cantLectores];
		
		JFrame tablaEstado=new MarcoTabla();
		JFrame colaLectores=new MarcoColaLectores();
		JFrame colaEscritores=new MarcoColaEscritores();
		tablaEstado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tablaEstado.setVisible(true);
		colaLectores.setVisible(true);
		colaEscritores.setVisible(true);
		

		for (int i=0; i<escritor.length; i++) {
			id++;
			escritor[i]= new Escritor(id, tiempoEscritura, tiempoSleepEsc, tablaEstado, colaEscritores, mutex, wrt);
		}
		
		for (int i=0; i<lector.length; i++) {
			id++;
			lector[i]= new Lector(id, tiempoLectura, tiempoSleepLec, tablaEstado, colaLectores, mutex, wrt);
		}
		
		
		
		for (int i=0; i<escritor.length; i++) {
			escritor[i].start();
			try {
				Thread.sleep(intervaloInicio);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (int i=0; i<lector.length; i++) {
			lector[i].start();
			try {
				Thread.sleep(intervaloInicio);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
