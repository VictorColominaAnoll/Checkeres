package representation;

import java.util.Scanner;

import domain.Taulell;
import intermediari.Intermediari;

public class VisualitzarPrint {
	
	public static void main(String[] args) {
		
//		System.out.println("Introdueix els noms dels jugadors");
		
		Scanner sc = new Scanner(System.in);
		
		String names[] = {"Jugador1", "Jugador2"};
				
		Intermediari i = new Intermediari(names);
		System.out.println("Blanca = 0\nNegra = 1\n----------------\n");
		while (true){
			System.out.println(i.getTorn());
			
			
			System.out.println(i.toString());

			System.out.println("Introdueix la posicio de la ficha a moure: ");
			System.out.println("X: ");
			int xActual = sc.nextInt();
			System.out.println("Y: ");
			int yActual = sc.nextInt();
			
			System.out.println("Introdueix la posicio on vols colocar la ficha a moure: ");
			System.out.println("X: ");
			int xNova = sc.nextInt();
			System.out.println("Y: ");
			int yNova = sc.nextInt();
			
			try {
				i.moviment(xActual, yActual, xNova, yNova);
			} catch (Exception e) {
				System.out.println("\n" + e.getMessage());
			}
			
		}
		
	}
	
}
