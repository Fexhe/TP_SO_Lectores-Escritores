package main;

public class GestorBD {

	private int nLectores=0;
	private boolean hayEscritor=false;

	public synchronized void openL(int id) throws InterruptedException{
		while(hayEscritor){
			wait();
		}
		nLectores++;
		System.out.println("Lector"+id+"entra en la BD");
	}
	
	public synchronized void closeL(int id){
		System.out.println("Lector"+id + "sale de la BD");
		nLectores--;
		if (nLectores==0) notify();
		

	}
	
	public synchronized void openE(int id) throws InterruptedException{
		while (hayEscritor || nLectores>0){
			wait();
		}
		hayEscritor=true;
		System.out.println("Escritor"+id+"entra en la BD");

	}
	
	public synchronized void closeE(int id) {
		System.out.println("Escritor"+id+"sale en la BD");
		hayEscritor=false;
		notifyAll();


}

	
}
