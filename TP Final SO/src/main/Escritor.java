package main;

import java.util.*;
import java.util.concurrent.Semaphore;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class Escritor extends Thread {

	private int id;
	private int tiempoEscritura;
	private int tiempoSleep; 
	Random rnd = new Random();
	private int escritura;
	private MarcoTabla tablaEstado;
	private MarcoColaEscritores colaEscritores;
	private Semaphore mutex;
	private Semaphore wrt;
	long time;


	//public Escritor(BasedeDatos bD, int id, int tiempoEscritura){
	public Escritor(int id, int tiempoEscritura, int tiempoSleepEsc, JFrame tablaEstado, JFrame colaEscritores, Semaphore mutex, Semaphore wrt){
		//this.bD=bD;
		this.tiempoSleep=tiempoSleepEsc;
		this.id=id;
		this.tiempoEscritura=tiempoEscritura;
		this.tablaEstado=(MarcoTabla) tablaEstado;
		this.colaEscritores=(MarcoColaEscritores)colaEscritores;
		this.mutex=mutex;
		this.wrt=wrt;

	}


	public void run() {
	
		while (true){
			
			try {
				
				wrt.acquire();	//wait (p)
				tablaEstado.modelo.addRow(new Object[]{id, "Escritor", "Inicio Escritura", time=System.nanoTime()});
					//Escribiendo en la base de datos
					Principal.baseDatos=rnd.nextInt(1000);
					Thread.sleep(tiempoEscritura);
					System.out.println("Escritor: "+id+" dato= "+Principal.baseDatos);
				wrt.release();	//signal (v)
				tablaEstado.modelo.addRow(new Object[]{id, "Escritor", "Fin Escritura", time=System.nanoTime()});
				Thread.sleep(tiempoSleep);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
}