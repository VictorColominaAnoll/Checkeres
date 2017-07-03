package domain;

public class Casella {

	// Atributs
	private Coordenada coordenada;
	private Ficha ficha;
	
	// Constructors
	public Casella(Coordenada coordenada){
		this(coordenada, null);
	}
	public Casella(Coordenada coordenada, Ficha ficha) {
		this.coordenada = coordenada;
		this.ficha = ficha;
	}

	// Methods
	public Ficha getFicha(){
		return this.ficha;
	}
	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}
	public boolean isEmpty(){
		return this.ficha == null;
	}
	public boolean isSamePlayer(Ficha f){
		return ficha.getJugador().equals(f.getJugador());
	}
	public int getColor() {
		if(ficha!=null)
			return this.ficha.getColor();
		
		return -1;
	}
	
}
