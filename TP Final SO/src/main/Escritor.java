package main;

import java.util.*;
public class Escritor extends Thread {

	private int id;
	private BasedeDatos bD;
	private int tiempoEscritura;
	private int tiempoSleep; 
	Random rnd = new Random();
	private int escritura;


	public Escritor(BasedeDatos bD, int id, int tiempoEscritura, int tiempoSleep){
		this.bD=bD;
		this.id=id;
		this.tiempoEscritura=tiempoEscritura;
		this.tiempoSleep=tiempoSleep;

	}


	public void run() {
	
		while (true){
			try {
				escritura=rnd.nextInt(1000);
				bD.escribir(id, escritura);
				//escribiendo en BD
				Thread.sleep(tiempoSleep);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			} 
		}
	
		
		
	}
}