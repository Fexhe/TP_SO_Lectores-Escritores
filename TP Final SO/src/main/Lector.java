package main;

public class Lector extends Thread{

	private BaseDatos bd;
	private int id;
	
	public Lector(BaseDatos bd, int id){
		this.bd=bd;
		this.id=id;
	}

	public void run() {
		while(true) {
			bd.openL(id);
			//lectura de Base de Datos
			bd.closeL(id);
		}
	}
}
