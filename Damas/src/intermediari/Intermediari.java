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
	
}