package main;

import java.util.*;
public class Escritor extends Thread {

	private BasedeDatos bD;
	private int tiempoEscritura;
	private int tiempoSleep; 
	private int id;

	public Escritor(BasedeDatos bD, int id, int tiempoEscritura, int tiempoSleep){
		this.bD=bD;
		this.id=id;
		this.tiempoEscritura=tiempoEscritura;
		this.tiempoSleep=tiempoSleep;

	}


public void run() {

	while (true){
		try {
			bD.escribir(id);
			//escribiendo en BD
			Thread.sleep(tiempoSleep);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		} 
	}

	
	
}
}