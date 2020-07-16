package main;

import java.util.*;
public class Lector extends Thread {

	private int id;
	private int tiempoLectura;
	private int tiempoSleep;

	//public Lector(BasedeDatos bD, int id, int timepoLectura){
	public Lector(int id, int tiempoLectura, int tiempoSleepLec){
		//this.bD=bD;
		this.tiempoSleep=tiempoSleepLec;
		this.id=id;
		this.tiempoLectura=tiempoLectura;
	
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
				System.out.println("Lector"+id+" dato= "+Principal.baseDatos);
				Thread.sleep(tiempoLectura);
				Principal.mutex.acquire();	//wait (p)
				Principal.contLectores--;
				if(Principal.contLectores == 0) {
					Principal.mutex.release();	//signal (v)
				}
				Principal.wrt.release();	//signal (v)
				Thread.sleep(tiempoSleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}