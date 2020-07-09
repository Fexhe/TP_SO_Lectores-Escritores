package main;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestorBDJusto gestor=new GestorBDJusto();
		Escritor[] esc=new Escritor[1];
		Lector[] lector=new Lector[4];
		
		for (int i=0; i<esc.length; i++) {
			esc[i]= new Escritor(gestor, i);
		}
		
		for (int i=0; i<lector.length; i++) {
			lector[i]= new Lector(gestor, i);
		}
		
		for (int i=0; i<esc.length; i++) {
			esc[i].start();
		}
		for (int i=0; i<lector.length; i++) {
			lector[i].start();
		}
		
	}

}
