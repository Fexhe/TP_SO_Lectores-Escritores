package main;

import java.util.*;
public class Escritor extends Thread {

	private int id;
	private int tiempoEscritura;
	private int tiempoSleep; 
	Random rnd = new Random();
	private int escritura;


	//public Escritor(BasedeDatos bD, int id, int tiempoEscritura){
	public Escritor(int id, int tiempoEscritura, int tiempoSleepEsc){
		//this.bD=bD;
		this.tiempoSleep=tiempoSleepEsc;
		this.id=id;
		this.tiempoEscritura=tiempoEscritura;

	}


	public void run() {
	
		while (true){
			
			try {
				Principal.wrt.acquire();	//wait (p)
					//Escribiendo en la base de datos
					Principal.baseDatos=rnd.nextInt(1000);
					Thread.sleep(tiempoEscritura);
					System.out.println("Escritor: "+id+" dato= "+Principal.baseDatos);
				Principal.wrt.release();	//signal (v)
				Thread.sleep(tiempoSleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
}