package domein;

public class Bonusfiche extends Fiche {
	
	private int waarde;
	
	
	public Bonusfiche(int positie, int waarde) {
		super(positie);
		this.waarde = waarde;
	}

	public int getWaarde() {
		return waarde;
	}
	
}
