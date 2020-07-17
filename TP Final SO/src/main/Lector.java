package main;

import java.util.*;

import javax.swing.JFrame;

public class Lector extends Thread {

	private int id;
	private int tiempoLectura;
	private int tiempoSleep;
	private MarcoTabla mimarco;
	long time;

	//public Lector(BasedeDatos bD, int id, int timepoLectura){
	public Lector(int id, int tiempoLectura, int tiempoSleepLec, JFrame mimarco){
		//this.bD=bD;
		this.tiempoSleep=tiempoSleepLec;
		this.id=id;
		this.tiempoLectura=tiempoLectura;
		this.mimarco=(MarcoTabla) mimarco;
	
	}
	
	public void run(){
	
		while (true){
			try {
				
				Principal.mutex.acquire();	//wait (p)
				Principal.contLectores++;
				if (Principal.contLectores == 1) {
					Principal.wrt.acquire();	//wait (p)
				}
				Principal.mutex.release();	//signal (v)
				//Leyendo Base de Datos
				mimarco.modelo.addRow(new Object[]{id, "Lector", "Inicio Lectura", time=System.nanoTime()});
				System.out.println("Lector"+id+" dato= "+Principal.baseDatos);
				Thread.sleep(tiempoLectura);
				Principal.mutex.acquire();	//wait (p)
				Principal.contLectores--;
				if(Principal.contLectores == 0) {
					Principal.wrt.release();	//signal (v)
				}
				Principal.mutex.release();	//signal (v)
				mimarco.modelo.addRow(new Object[]{id, "Lector", "Fin Lectura", time=System.nanoTime()});
				Thread.sleep(tiempoSleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}