package main;

public class GestorBDJusto {

	private int nLectores=0;
	private boolean hayEscritor=false;
	private int nEscritor=0;

	public synchronized void openL(int id) throws InterruptedException{
		while(hayEscritor || nEscritor>0){
			wait();
		}
		nLectores++;
		System.out.println("Lector"+id+"entra en la BD");
	}
	
	public synchronized void closeL(int id){
		System.out.println("Lector"+id + "sale de la BD");
		nLectores--;
		if (nLectores==0) notifyAll();
		

	}
	
	public synchronized void openE(int id) throws InterruptedException{
		
		nEscritor++;
		while (hayEscritor || nLectores>0){
			wait();
		}
		hayEscritor=true;
		System.out.println("Escritor"+id+"entra en la BD");

	}
	
	public synchronized void closeE(int id) {
		nEscritor--;
		System.out.println("Escritor"+id+"sale en la BD");
		hayEscritor=false;
		notifyAll();


}
	
	
}
