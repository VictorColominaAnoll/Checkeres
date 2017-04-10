package domain;

public class Taulell {

	// DRETA x++, y--
	// ESQUERRA x--, y--

	
	private static final int BLANC = 0;
	private static final int NEGRE = 1;
	
	private Ficha taulell[][] = new Ficha[8][8];
	private Jugador[] jugador = new Jugador[2];
	
	public Taulell(String []names){
	
		jugador[0] = new Jugador(names[0], BLANC);
		jugador[1] = new Jugador(names[1], NEGRE);
	
		init();
	
	}
	
	private void init() {
		for (int i = 0; i < taulell.length; i++) {
			for (int j = 0; j < taulell.length; j++) {
				if (i < 3)  // BLANCAS
					introduirFichaEnElTaulell(i, j, jugador[0]);
				else
					if(i > 4) //NEGRAS
						introduirFichaEnElTaulell(i, j, jugador[1]);
			}
		}
	}

	private void introduirFichaEnElTaulell(int i, int j, Jugador player) {
		if (i % 2 == 0 && (j == 1 || j == 3 ||j == 5 ||j == 7)) { // PARES
			taulell[i][j] = new Ficha(player);
		}
		else {
			if(i % 2 != 0 && (j == 0 || j == 2 ||j == 4 ||j == 6)){
				taulell[i][j] = new Ficha(player);
			}
			else{
				taulell[i][j] = null;
			}
			
		}
		
	}

	public String toString(){
		
		String resultat = "";
		
		for (int i = 0; i < taulell.length; i++) {
			for (int j = 0; j < taulell.length; j++) {
				if(taulell[i][j] != null){
					resultat += taulell[i][j].getColor() + "  ";
				}
				else{
					resultat += "-  ";
				}
			}
			
			resultat += "\n";
		}
		
		return resultat;
	}

	public void moviment(int xActual, int yActual, int xNova, int yNova) throws Exception{
		
		/* PODEM MOURE?
		 * Nomes podem en els seguents casos:
		 * 
		 * 1) A la posicio es trobi una ficha
		 * 2) No surt del taulell
		 * 3) No hi ha una ficha nostre a la posicio que volem anar
		 * 
		 * PODEM MATAR?
		 * Nomes podem si:
		 * 
		 * 1) Es cumpleix que podem moure
		 * 2) No hi ha una ficha radera de la que es vol matar (posicio en la que la notre ficha acabaria)
		 * 
		 */
		
		//QUAN LES COORDENADAS ENS LES DONEN x2 VOL DIR QUE VOL MATAR A ALGU, PER TANT NOMES HAUREM DE COMPROVAR EN AQUEST CAS.
		
		
		Coordenada coordenadaActual = new Coordenada(xActual, yActual);
		Coordenada coordenadaNova = new Coordenada(xNova, yNova);
		
		
		
		
	}
	
	//FER UN METODE PER SABER CUANTAS FICHAS TE UN DELS DOS JUGADORS.
	
}















