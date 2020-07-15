package main;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class BasedeDatos {

	private int dato=0;
	public int tiempoLectura;
	public int tiempoEscritura;
	public int contLectores=0;
	private Semaphore mutex= new Semaphore(1);
	private Semaphore semEscritura= new Semaphore(1);

	
	public BasedeDatos(int tiempoLectura, int tiempoEscritura) {
		this.tiempoLectura=tiempoLectura;
		this.tiempoEscritura=tiempoEscritura;			
	}
	

	public void leer(int id){
		try {
			mutex.acquire();	//wait
			contLectores++;
			if(contLectores == 1) {
				semEscritura.acquire();	//wait
				mutex.release();	//signal
				
					System.out.println("Lector"+id+" dato= "+dato);
					Thread.sleep(tiempoLectura);
				
				mutex.acquire();	//wait
				contLectores--;
			}
			if(contLectores == 0) {
				semEscritura.release();	//signal
				mutex.release();	//signal
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

	}
	
	public void escribir(int id, int escritura){
		
		try {
			semEscritura.acquire();	//wait
				
				dato = escritura;
				System.out.println("Escritor: "+id+" dato= "+dato);
				Thread.sleep(tiempoEscritura);
			
			semEscritura.release();	//signal
		
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
