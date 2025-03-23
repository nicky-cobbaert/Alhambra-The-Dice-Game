package domein;

public class Bonusfiche extends Fiche {
	
	private int waarde;
	
	
	public Bonusfiche(int positie, int waarde) {
		super();
		this.waarde = waarde;
	}

	public int getWaarde() {
		return waarde;
	}
	
}
