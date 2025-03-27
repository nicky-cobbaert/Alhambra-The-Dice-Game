package domein;

public class Gebouwsteen {

	private int positie;
	
	public Gebouwsteen() {
		
	}
	
	public int getPositie(){ //toegevoegd voor mijn methode  
		return positie;
	}
	
	
	public void plaatsNeer(int positie) {
		this.positie = positie;
	}
	
}
