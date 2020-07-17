package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

//https://www.youtube.com/watch?v=yH_g6QGFqes
//https://www.lawebdelprogramador.com/foros/Java/1447484-actualizar-jtable-en-tiempo-de-ejecucion.html

public class Tabla {

	public class PruebaTablaBoton {

		public static void main(String[ ] args) {
			
			JFrame mimarco=new MarcoTabla();
			
			mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			mimarco.setVisible(true);
		
		}
		
	}
		
	class MarcoTabla extends JFrame{
		
		public MarcoTabla() {
			setTitle("Prueba");
			
			setBounds(300,400,300,200);
			
			JTable tablaOperadores=new JTable(datosFila, nombresColumnas);
			
			add(new JScrollPane(tablaOperadores), BorderLayout.CENTER);
			
			JButton botonImprimir=new JButton ("Imprimir tabla");
			
			botonImprimir.addActionListener(new ActionListener() {
				
					

				@Override
				public void actionPerformed(ActionEvent e) {
					
					try {
						tablaOperadores.print();					
					
				}catch(Exception e2) {
						e2.printStackTrace();

				}
				}
				});
			
				JPanel laminaBoton=new JPanel();
				
				laminaBoton.add(botonImprimir);
				
				add(laminaBoton, BorderLayout.SOUTH);
			}
		
		

		private String [] nombresColumnas= {"id", "Abierto", "Cerrado", "Cant Ejecutado"};
		
		private Object [] [] datosFila= {
				
				{1,false, true, 0},
				{2,false, true, 0},
				{3,false, true, 0},
				{4,false, true, 0},
				{5,false, true, 0},
				{6,false, true, 0},
				{7,false, true, 0},
				{8,false, true, 0},
				{9,false, true, 0},
		};
		
		private void cargarTabla() {
			i=0;
			i++;
			tablaOperadores.
					titColumna[i] = "Col: "+i;
			
			
			
			/*for (Integer elemento : queueEscritor) {
			{id,tipoOperador,estado,cantEjecutado};
		};
		
		for (Integer elemento2 : queueLector) {
			{id,tipoOperador,estado,cantEjecutado};
		};
		*/
		}
		

		
	}
	
	
	
	
	
	
	
}
