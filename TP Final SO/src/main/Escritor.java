package main;

import java.util.*;
public class Escritor extends Thread {

	private static Random r=new Random();
	private GestorBDJusto gestor; // hacer clase GestorBD
	private int id;

	public Escritor(GestorBDJusto gestor, int id){
		this.gestor=gestor;
		this.id=id;

	}


public void run() {

	while (true){
		try {
			gestor.openE(id);
			//escribiendo en BD
			Thread.sleep(r.nextInt(200));
			gestor.closeE(id);
			Thread.sleep(r.nextInt(300));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	
	
}
}