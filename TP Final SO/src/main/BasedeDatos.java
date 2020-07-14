package main;

import java.util.Queue;

public class BasedeDatos {

	private int dato=0;
	private int nLectores=0;
	private int nEscritor=0;
	private Queue colaLectores;
	private Queue colaEscritores;
	
	private boolean hayEscritor=false;

	
	public void openL(int id) throws InterruptedException{
		while(hayEscritor || nEscritor>0){
			wait();
		}
		nLectores++;
		System.out.println("Lector"+id+"entra en la BD");
	}
	
	public void closeL(int id){
		System.out.println("Lector"+id + "sale de la BD");
		nLectores--;
		if (nLectores==0) notifyAll();
		

	}
}
