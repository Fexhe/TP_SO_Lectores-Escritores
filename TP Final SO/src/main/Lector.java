package main;

import java.util.*;
public class Lector extends Thread{

	private BaseDatos bD; // hacer clase GestorBD
	private int id;
	private int tiempoLectura;
	private int tiempoSleep;

public Lector(BaseDatos bD, int id, int timepoLectura, int tiempoSleep){
	this.bD=bD;
	this.id=id;
	this.tiempoLectura=tiempoLectura;
	this.tiempoSleep=tiempoSleep;

}

public void run(){

	while (true){
		try {
			bD.openL(id);
			//leyendo BD
			Thread.sleep(tiempoLectura);
			bD.closeL(id);
			Thread.sleep(tiempoSleep);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
}