package domain;

public class Coordenada {

	// RIGHT x++, y--
	// LEFT x--, y--

	private int x, y;
	
	public Coordenada(int x, int y) throws Exception{
		
		if(x < 0 || y < 0 || x > 8 || y > 8)
			throw new Exception("ERROR: Coordenada erronea.");
		
		this.x = x;
		this.y = y;
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean direccio(Coordenada coordenadaNova){
		
		//RETURN false IF THE DIRECTION IS TO THE LEFT AND true IF IT GOES TO THE RIGHT
			
		return x - coordenadaNova.getX() > 0;
	}
	
	public Coordenada getDreta(int color) throws Exception{

		int x = this.x + 1;
		int y;
		
		if(color == 0) // White
			y = this.y - 1;
		else // Black
			y = this.y + 1;
	
		return new Coordenada(x,y);
		
	}
	
	public Coordenada getEsquerra(int color) throws Exception{
		
		int x = this.x + 1;
		int y;
		
		if(color == 0) // White
			y = this.y - 1;
		else // Black
			y = this.y + 1;
	
		return new Coordenada(x,y);
		
	}

	public boolean isSimpleMovement(Coordenada coordenadaNova){
		
		return (x - coordenadaNova.getX() == 1 || x - coordenadaNova.getX() == -1) &&
				(y - coordenadaNova.getY() == 1 || y - coordenadaNova.getY() == -1);
		
	}

	public boolean isKillMovement(Coordenada coordenadaNova) {
		
		return (x - coordenadaNova.getX() == 2 || x - coordenadaNova.getX() == -2) &&
				(y - coordenadaNova.getY() == 2 || y - coordenadaNova.getY() == -2);
		
	}
	
}
