package domein;

public class Zetsteen {
	
	private int positie; 
	
	public Zetsteen() {
		
	}
	
	public void plaatsNeer(int positie) {
		
		this.positie = positie;
	}
	
	
	public int getPositie() { // Snel getter toegevoegd voor mijn methode
        return positie;
    }

}
