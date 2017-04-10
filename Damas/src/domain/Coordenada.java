package domain;

public class Coordenada {

	private int x, y;
	
	public Coordenada(int x, int y) throws Exception{
		
		if(x < 0 || y < 0 || x > 60 || y > 60)
			throw new Exception("ERROR: Coordenada erronea.");
		
		this.x = x;
		this.y = y;
		
	}
}
