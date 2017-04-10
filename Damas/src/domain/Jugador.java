package domain;

public class Jugador {

	private String name;
	// private int puntuacio;
	private int color;
	private boolean turno;
	
	public Jugador(String name, int color){
		this.name = name;
		this.color = color;
		this.turno = color == 0; // Si son blancas sera true y sino false.
	}

	public int getColor() {
		return this.color;
	}
	
	public boolean getTurno() {
		return this.turno;
	}
	
	public void setTurno(boolean turno){
		this.turno = turno;
	}
	
}