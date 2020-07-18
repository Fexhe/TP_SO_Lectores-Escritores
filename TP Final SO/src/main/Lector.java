package main;

import java.util.*;
import java.util.concurrent.Semaphore;

import javax.swing.JFrame;

public class Lector extends Thread {

	private int id;
	private int tiempoLectura;
	private int tiempoSleep;
	private MarcoTabla tablaEstado;
	private MarcoColaLectores colaLectores;
	private Semaphore mutex;
	private Semaphore wrt;
	long time;

	//public Lector(BasedeDatos bD, int id, int timepoLectura){
	public Lector(int id, int tiempoLectura, int tiempoSleepLec, JFrame tablaEstado, JFrame colaLectores, Semaphore mutex, Semaphore wrt){
		//this.bD=bD;
		this.tiempoSleep=tiempoSleepLec;
		this.id=id;
		this.tiempoLectura=tiempoLectura;
		this.tablaEstado=(MarcoTabla) tablaEstado;
		this.colaLectores= (MarcoColaLectores) colaLectores;
		this.mutex=mutex;
		this.wrt=wrt;
				
	
	}
	
	public void run(){
	
		while (true){
			try {
				
				mutex.acquire();	//wait (p)
				Principal.contLectores++;
				if (Principal.contLectores == 1) {
					Principal.wrt.acquire();	//wait (p)
				}
				mutex.release();	//signal (v)
				//Leyendo Base de Datos
				tablaEstado.modelo.addRow(new Object[]{id, "Lector", "Inicio Lectura", time=System.nanoTime()});
				System.out.println("Lector"+id+" dato= "+Principal.baseDatos);
				Thread.sleep(tiempoLectura);
				mutex.acquire();	//wait (p)
				Principal.contLectores--;
				if(Principal.contLectores == 0) {
					Principal.wrt.release();	//signal (v)
				}
				mutex.release();	//signal (v)
				tablaEstado.modelo.addRow(new Object[]{id, "Lector", "Fin Lectura", time=System.nanoTime()});
				Thread.sleep(tiempoSleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}