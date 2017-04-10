package domain;

public class Jugador {

	private String name;
	// private int puntuacio;
	private int color;
	
	public Jugador(String name, int color){
		this.name = name;
		this.color = color;
	}

	public int getColor() {
		return this.color;
	}
	
}