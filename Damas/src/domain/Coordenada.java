package domain;

public class Coordenada {

	// RIGHT x++, y--
	// LEFT x--, y--

	private int x, y;
	
	public Coordenada(int x, int y) throws Exception{
		
		if(x < 0 || y < 0 || x > 60 || y > 60)
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

	public Coordenada getDreta(Coordenada c, int color){
		
		int x = c.getX() + 1;
		int y;
		
		if(color == 0) // White
			y = c.getY() - 1;
		else // Black
			y = c.getY() + 1;
		
		c.setX(x);
		c.setY(y);
		
		return c;
		
	}
	
	public Coordenada getEsquerra(Coordenada c, int color){
		
		int x = c.getX() - 1;
		int y;
		
		if(color == 0) // White
			y = c.getY() - 1;
		else // Black
			y = c.getY() + 1;
		
		c.setX(x);
		c.setY(y);
				
		return c;
		
	}

	public boolean isSimpleMovement(Coordenada nova){
		
		return (x - nova.getX() == 1 || x - nova.getX() == -1);
		
	}
	
}
