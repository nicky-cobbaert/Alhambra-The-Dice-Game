package domein;

public class Bonusfiche extends Fiche {
	
	private int waarde;
	private Speler vanSpeler;
	
	public Bonusfiche(int positie, int waarde) {
		super(positie);
		this.waarde = waarde;
	}

	public int getWaarde() {
		return waarde;
	}
	
}
