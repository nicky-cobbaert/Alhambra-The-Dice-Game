package domein;

public class StartspelerFiche extends Fiche {
	
	private Speler bijSpeler;
	
	public Speler getBijSpeler() {
		return bijSpeler;
	}
	
	public StartspelerFiche(int positie,Speler startSpeler) {
		super(positie);
	}
	
} 
