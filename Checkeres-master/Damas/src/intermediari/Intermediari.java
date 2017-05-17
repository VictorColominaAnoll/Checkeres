package intermediari;

import domain.Taulell;

public class Intermediari {

	private Taulell taulell;
	
	public Intermediari(String []names){
		this.taulell = new Taulell(names);
	}
	
	public String toString(){
		return taulell.toString();
	}
	
	public void moviment(int xActual, int yActual, int xNova, int yNova) throws Exception{
		this.taulell.moviment(xActual, yActual, xNova, yNova);
	}
	
	public String getTorn(){
		return this.taulell.getTornActual();
	}
}