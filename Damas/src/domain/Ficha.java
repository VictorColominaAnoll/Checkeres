package domain;

public class Ficha {
	
	private static final boolean VIU = true; //ESTA VIVA
	
	private boolean estat;
	private Jugador jugador;
	private int color;
	
	public Ficha(Jugador jugador){
		
		this.jugador = jugador;
		this.estat = VIU;
		this.color = jugador.getColor();
	}
	
	public Jugador getJugador(){
		return this.jugador;		
	}
	
	public void setEstat() {
		this.estat = false;
	}

	public int getColor() {
		return this.color;
	}
	
}
