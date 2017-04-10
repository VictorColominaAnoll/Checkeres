package domain;

public class Taulell {

	private static final int BLANC = 0;
	private static final int NEGRE = 1;
	
	private Ficha taulell[][] = new Ficha[8][8];
	private Jugador[] jugador = new Jugador[2];
	
	public Taulell(String []names){
	
		jugador[0] = new Jugador(names[0], BLANC);
		jugador[1] = new Jugador(names[1], NEGRE);
	
		for (int i = 0; i < taulell.length; i++) {
			for (int j = 0; j < taulell.length; j++) {
				taulell[i][j] = null;
			}
		}
		
		taulell[1][1] = new Ficha(jugador[0]);
		taulell[2][2] = new Ficha(jugador[1]);
		taulell[4][2] = new Ficha(jugador[1]);
		
		//init();
	
	}
	
	private void init() {
		
		for (int i = 0; i < taulell.length; i++) {
			for (int j = 0; j < taulell.length; j++) {
				if (i < 3)  // BLANCAS
					introduirFichaEnElTaulell(i, j, jugador[0]);
				else
					if(i < 5) 
						taulell[i][j] = null;
					else //NEGRAS
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
		
		Coordenada coordenadaActual = new Coordenada(xActual, yActual);
		Coordenada coordenadaNova = new Coordenada(xNova, yNova);
		
		Ficha fichaSeleccionada = taulell[coordenadaActual.getX()][coordenadaActual.getY()];
		
		if(fichaSeleccionada == null)
			throw new Exception("ERROR: No s'ha seleccionat cap ficha.");
		else
			if(!fichaSeleccionada.getJugador().getTurno())
				throw new Exception("ERROR: Ficha de otro jugador.");
		
		if(coordenadaActual.isSimpleMovement(coordenadaNova)){
			simpleMovement(coordenadaActual, coordenadaNova);
			
			cambioDeTurno();
			
		} else {
			
			if(coordenadaActual.isKillMovement(coordenadaNova)){ // VOL MATAR
				
				if(taulell[coordenadaNova.getX()][coordenadaNova.getY()] != null)
					throw new Exception("ERROR: Ja existeix una ficha a la nova posicio.");
				
				else{
					
					killMovement(coordenadaActual, coordenadaNova);
					
					if(!keepKilling(coordenadaNova))
						cambioDeTurno();
				}
			}
			else 
				throw new Exception("ERROR: Moviment no valid.");
			
		}
		
	}

	private boolean keepKilling(Coordenada coordenadaActual) throws Exception {
		
		Ficha fichaSeleccionada = taulell[coordenadaActual.getX()][coordenadaActual.getY()];
		
		Coordenada coordenadaVictima = coordenadaActual.getDreta(fichaSeleccionada.getColor());

		if(taulell[coordenadaVictima.getX()][coordenadaVictima.getY()] != null){ //RIGHT
			return checkFinalPosition(coordenadaVictima);
		}
		else{ //LEFT
			coordenadaVictima = coordenadaActual.getEsquerra(fichaSeleccionada.getColor());
			
			if(taulell[coordenadaVictima.getX()][coordenadaVictima.getY()] != null){
				return checkFinalPosition(coordenadaVictima);
			}
		}
		
		return false;
	}

	private boolean checkFinalPosition(Coordenada coordenadaVictima) throws Exception {
		
		Coordenada coordenadaNovaDreta = coordenadaVictima.getDreta(taulell[coordenadaVictima.getX()][coordenadaVictima.getY()].getColor());
		Coordenada coordenadaNovaEsquerra = coordenadaVictima.getEsquerra(taulell[coordenadaVictima.getX()][coordenadaVictima.getY()].getColor());
		
		return taulell[coordenadaNovaDreta.getX()][coordenadaNovaDreta.getY()] != null || taulell[coordenadaNovaEsquerra.getX()][coordenadaNovaEsquerra.getY()] != null;
	}

	private void killMovement(Coordenada coordenadaActual, Coordenada coordenadaNova) throws Exception {
		
		Ficha fichaSeleccionada = taulell[coordenadaActual.getX()][coordenadaActual.getY()];
		Coordenada coordenadaFichaVictima;
		
		if(coordenadaActual.direccio(coordenadaNova)){
			coordenadaFichaVictima = coordenadaActual.getDreta(fichaSeleccionada.getColor());
		} else{
			coordenadaFichaVictima = coordenadaActual.getEsquerra(fichaSeleccionada.getColor());
		}
		
		Ficha fichaVictima = taulell[coordenadaFichaVictima.getX()][coordenadaFichaVictima.getY()];
				
		if(fichaVictima == null)
			throw new Exception("ERROR: Moviment no valid.");
		if(fichaVictima.getColor() == fichaSeleccionada.getColor())
			throw new Exception("ERROR: No es pot matar una ficha teva.");
		else{
			// Kill
			fichaVictima.setEstat();
			taulell[coordenadaFichaVictima.getX()][coordenadaFichaVictima.getY()] = null;
			// Change to the new position
			taulell[coordenadaNova.getX()][coordenadaNova.getY()] = taulell[coordenadaActual.getX()][coordenadaActual.getY()];
			// Delete from the old position
			taulell[coordenadaActual.getX()][coordenadaActual.getY()] = null; 
			
		}
			
	}

	private void cambioDeTurno() {
		
		jugador[0].setTurno(!jugador[0].getTurno());
		jugador[1].setTurno(!jugador[1].getTurno());

	}

	private void simpleMovement(Coordenada coordenadaActual, Coordenada coordenadaNova) throws Exception {
		if(taulell[coordenadaNova.getX()][coordenadaNova.getY()] != null)
			throw new Exception("ERROR: Existeix una ficha en la nova posicio");
		else{
			// Change to the new position
			taulell[coordenadaNova.getX()][coordenadaNova.getY()] = taulell[coordenadaActual.getX()][coordenadaActual.getY()];
			// Delete from the old position
			taulell[coordenadaActual.getX()][coordenadaActual.getY()] = null;
		}
			
	}
	
	//FER UN METODE PER SABER CUANTAS FICHAS TE UN DELS DOS JUGADORS.
	
}















