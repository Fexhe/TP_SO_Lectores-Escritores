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
		nLectores = new Semaphore(cantLectores);
		this.tiempoLectura=tiempoLectura;
		this.tiempoEscritura=tiempoEscritura;
		
				
	}
	
	// TODO Generar logica/condiciones para que no se lea mientras se escribe
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
			dato = escritura;
			System.out.println("Escritor: "+id+" dato= "+dato);
			Thread.sleep(tiempoLectura);
			nEscritores.release();
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

		/*
		   private static void burbuja(int[] arreglo) {
			for (int x = 0; x < arreglo.length; x++) {
			// Aquí "y" se detiene antes de llegar
			// a length - 1 porque dentro del for, accedemos
			// al siguiente elemento con el índice actual + 1
				for (int y = 0; y < arreglo.length - 1; y++) {
					int elementoActual = arreglo[y],
							elementoSiguiente = arreglo[y + 1];
					if (elementoActual > elementoSiguiente) {
						// Intercambiar
						arreglo[y] = elementoSiguiente;
						arreglo[y + 1] = elementoActual;
					}
				}
			}
		}
	*/
	}
}
