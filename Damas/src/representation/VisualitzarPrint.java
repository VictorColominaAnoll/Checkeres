package representation;

import domain.Taulell;
import intermediari.Intermediari;

public class VisualitzarPrint {
	
	public static void main(String[] args) {
		
//		System.out.println("Introdueix els noms dels jugadors");
		
		String names[] = {"Jugador1", "Jugador2"};
				
		Intermediari i = new Intermediari(names);
		System.out.println("Blanca = 0\nNegra = 1\n----------------\n");
		System.out.println(i.toString());
		
	}
	
}
