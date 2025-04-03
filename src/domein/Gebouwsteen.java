package domein;

public class Gebouwsteen {

	private int positie;
	private int volgorde;
	
	public Gebouwsteen() {
		
	}
	
	public int getPositie(){ //toegevoegd voor mijn methode  
		return positie;
	}
	
	
	public void plaatsNeer(int positie) {
		this.positie = positie;
	}
	
}
