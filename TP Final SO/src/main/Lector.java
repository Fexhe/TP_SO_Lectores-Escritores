package main;

import java.util.*;
public class Lector extends Thread{

private static Random r=new Random();
private GestorBDJusto gestor; // hacer clase GestorBD
private int id;

public Lector(GestorBDJusto gestor, int id){
	this.gestor=gestor;
	this.id=id;

}

public void run(){

	while (true){
		try {
			gestor.openL(id);
			//leyendo BD
			Thread.sleep(r.nextInt(200));
			gestor.closeL(id);
			Thread.sleep(r.nextInt(300));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
}