package domain;

public class Taulell {

	private static final int BLANC = 0;
	private static final int NEGRE = 1;
	
	private Casella taulell[][] = new Casella[8][8];
	private Jugador[] jugador = new Jugador[2];
	
	public Taulell(String []names){
	
		jugador[0] = new Jugador(names[0], BLANC);
		jugador[1] = new Jugador(names[1], NEGRE);
	
		for (int i = 0; i < taulell.length; i++) {
			for (int j = 0; j < taulell.length; j++) {
				try {
					taulell[i][j] = new Casella(new Coordenada(i,j));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		init();
	
	}
	private void init() {
		
		for (int i = 0; i < taulell.length; i++) {
			for (int j = 0; j < taulell.length; j++) {
				if (i < 3)  // BLANCAS
					introduirFichaEnElTaulell(i, j, jugador[0]);
				else
					if(i > 4)  //NEGRAS
						introduirFichaEnElTaulell(i, j, jugador[1]);
			}
		}
	}

	private void introduirFichaEnElTaulell(int i, int j, Jugador player) {
		if (i % 2 == 0 && (j == 1 || j == 3 ||j == 5 ||j == 7)) { // PARES
			taulell[i][j].setFicha(new Ficha(player));
		}
		else {
			if(i % 2 != 0 && (j == 0 || j == 2 ||j == 4 ||j == 6)){
				taulell[i][j].setFicha(new Ficha(player));
			}
			else{
				taulell[i][j].setFicha(null);
			}
			
		}
		
	}

	public String toString(){
		
		String resultat = "";
		
		for (int i = 0; i < taulell.length; i++) {
			for (int j = 0; j < taulell.length; j++) {
				if(!taulell[i][j].isEmpty())
						resultat += taulell[i][j].getColor() + "  ";
				
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
		
		Ficha fichaSeleccionada = taulell[coordenadaActual.getX()][coordenadaActual.getY()].getFicha();
		
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
				if (taulell[coordenadaNova.getX()][coordenadaNova.getY()].getFicha() != null)
					throw new Exception("ERROR: Ja existeix una ficha a la nova posicio.");

				else {

					killMovement(coordenadaActual, coordenadaNova);

					if (!keepKilling(coordenadaNova))
						cambioDeTurno();
				}
			}
			else 
				throw new Exception("ERROR: Moviment no valid.");
			
		}
		
	}
	
	
	
	
	
	
//	private boolean keepKilling(Coordenada coordenadaActual) throws Exception {
//		
//		Ficha fichaSeleccionada = taulell[coordenadaActual.getX()][coordenadaActual.getY()].getFicha();
//		
//		Coordenada coordenadaVictima = coordenadaActual.getDreta(fichaSeleccionada.getColor());
//
//		if(/*taulell[coordenadaVictima.getX()][coordenadaVictima.getY()] != null ||*/ taulell[coordenadaVictima.getX()][coordenadaVictima.getY()].getColor() != taulell[coordenadaActual.getX()][coordenadaActual.getY()].getColor()){ //RIGHT
//			return checkFinalPosition(coordenadaVictima.getDreta(taulell[coordenadaVictima.getX()][coordenadaVictima.getY()].getColor()));
//		}
//		else{ //LEFT
//			coordenadaVictima = coordenadaActual.getEsquerra(fichaSeleccionada.getColor());
//			
//			if(/*taulell[coordenadaVictima.getX()][coordenadaVictima.getY()] != null ||*/ taulell[coordenadaVictima.getX()][coordenadaVictima.getY()].getColor() != taulell[coordenadaActual.getX()][coordenadaActual.getY()].getColor()){
//				return checkFinalPosition(coordenadaVictima.getEsquerra(taulell[coordenadaVictima.getX()][coordenadaVictima.getY()].getColor()));
//			}
//		}
//		
//		return false;
//	}

	
	private boolean keepKilling(Coordenada coordenadaActual) throws Exception {
		//Left
		
		int xActual = coordenadaActual.getX();
		int yActual = coordenadaActual.getY();
		
		int color = taulell[xActual][yActual].getColor();
		
		Coordenada coordenadaVictima = coordenadaActual.getEsquerra(color);
		
		int x = coordenadaVictima.getX();
		int y = coordenadaVictima.getY();
		
		if (!taulell[x][y].isEmpty())
			if (!taulell[x][y].isSamePlayer(taulell[xActual][yActual].getFicha())) {
				return checkFinalPosition(coordenadaVictima.getEsquerra(color));
			}
		return false;
	}
	private boolean checkFinalPosition(Coordenada coordenadaVictima) throws Exception {
		
		return taulell[coordenadaVictima.getX()][coordenadaVictima.getY()].isEmpty();
	}

	private void killMovement(Coordenada coordenadaActual, Coordenada coordenadaNova) throws Exception {
		
		Ficha fichaSeleccionada = taulell[coordenadaActual.getX()][coordenadaActual.getY()].getFicha();
		Coordenada coordenadaFichaVictima;
		
		if(coordenadaActual.direccio(coordenadaNova)){
			coordenadaFichaVictima = coordenadaActual.getEsquerra(fichaSeleccionada.getColor());
			System.out.println("holis");
		} else{
			coordenadaFichaVictima = coordenadaActual.getDreta(fichaSeleccionada.getColor());
			System.out.println("adewis");

		}
		
		Ficha fichaVictima = taulell[coordenadaFichaVictima.getX()][coordenadaFichaVictima.getY()].getFicha();
				
		if(fichaVictima == null)
			throw new Exception("ERROR: Moviment no valid.");
		if(fichaVictima.getColor() == fichaSeleccionada.getColor())
			throw new Exception("ERROR: No es pot matar una ficha teva.");
		else{
			// Kill
			fichaVictima.setEstat();
			taulell[coordenadaFichaVictima.getX()][coordenadaFichaVictima.getY()].setFicha(null);
			// Change to the new position
			taulell[coordenadaNova.getX()][coordenadaNova.getY()].setFicha(taulell[coordenadaActual.getX()][coordenadaActual.getY()].getFicha());
			// Delete from the old position
			taulell[coordenadaActual.getX()][coordenadaActual.getY()].setFicha(null);
			
		}
			
	}

	private void cambioDeTurno() {
		
		jugador[0].setTurno(!jugador[0].getTurno());
		jugador[1].setTurno(!jugador[1].getTurno());

	}
	
	public String getTornActual(){
		if(jugador[0].getTurno())
			return jugador[0].getName();
		else
			return jugador[1].getName();
		
	}

	private void simpleMovement(Coordenada coordenadaActual, Coordenada coordenadaNova) throws Exception {
		if(taulell[coordenadaNova.getX()][coordenadaNova.getY()].getFicha() != null)
			throw new Exception("ERROR: Existeix una ficha en la nova posicio");
		else{
			// Change to the new position
			taulell[coordenadaNova.getX()][coordenadaNova.getY()].setFicha(taulell[coordenadaActual.getX()][coordenadaActual.getY()].getFicha());
			// Delete from the old position
			taulell[coordenadaActual.getX()][coordenadaActual.getY()].setFicha(null);
		}
			
	}
	
	
	//FER UN METODE PER SABER SI TE ALGUNA FICHA VIVA (EN CAS CONTRARI EL JUGADOR RIVAL HA GUANYAT LA PARTIDA)
	
}















