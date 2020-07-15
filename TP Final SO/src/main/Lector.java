package main;

import java.util.*;
public class Lector extends Thread{

	private BasedeDatos bD; // hacer clase GestorBD
	private int id;
	private int tiempoLectura;
	private int tiempoSleep;

public Lector(BasedeDatos bD, int id, int timepoLectura, int tiempoSleep){
	this.bD=bD;
	this.id=id;
	this.tiempoLectura=tiempoLectura;
	this.tiempoSleep=tiempoSleep;

}

public void run(){

	while (true){
		try {
			bD.leer(id);
			//escribiendo en BD
			Thread.sleep(tiempoSleep);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
}
}