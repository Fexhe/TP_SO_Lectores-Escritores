package main;

import java.util.*;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class Escritor extends Thread {

	private int id;
	private int tiempoEscritura;
	private int tiempoSleep; 
	Random rnd = new Random();
	private int escritura;
	private MarcoTabla mimarco;
	long time;


	//public Escritor(BasedeDatos bD, int id, int tiempoEscritura){
	public Escritor(int id, int tiempoEscritura, int tiempoSleepEsc, JFrame mimarco){
		//this.bD=bD;
		this.tiempoSleep=tiempoSleepEsc;
		this.id=id;
		this.tiempoEscritura=tiempoEscritura;
		this.mimarco=(MarcoTabla) mimarco;

	}


	public void run() {
	
		while (true){
			
			try {
				
				Principal.wrt.acquire();	//wait (p)
				mimarco.modelo.addRow(new Object[]{id, "Escritor", "Inicio Escritura", time=System.nanoTime()});
					//Escribiendo en la base de datos
					Principal.baseDatos=rnd.nextInt(1000);
					Thread.sleep(tiempoEscritura);
					System.out.println("Escritor: "+id+" dato= "+Principal.baseDatos);
				Principal.wrt.release();	//signal (v)
				mimarco.modelo.addRow(new Object[]{id, "Escritor", "Fin Escritura", time=System.nanoTime()});
				Thread.sleep(tiempoSleep);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
}