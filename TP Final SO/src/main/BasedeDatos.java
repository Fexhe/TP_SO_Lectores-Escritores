package main;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class BasedeDatos {

	private int dato=0;
	public int tiempoLectura;
	public int tiempoEscritura;
	private Semaphore nLectores;
	private Semaphore nEscritores= new Semaphore(1);
	
	private boolean hayEscritor=false;

	public BasedeDatos(int cantLectores, int tiempoLectura, int tiempoEscritura) {
		// TODO pasarle la cantidad de lectores al constructor
		new Semaphore(cantLectores);
		this.tiempoLectura=tiempoLectura;
		this.tiempoEscritura=tiempoEscritura;
		
				
	}
	
	public void leer(int id){
		
		try {
			nLectores.acquire();
			System.out.println("Lector"+id+" dato= "+dato);
			Thread.sleep(tiempoLectura);
			
			nLectores.release();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

	}
	
	public void escribir(int id, int escritura){
		
		try {
			nEscritores.acquire();
			System.out.println("Lector"+id+" dato= "+dato);
			dato = escritura;
			Thread.sleep(tiempoLectura);
			nEscritores.release();
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		


	}
}
