package domein;

import java.security.SecureRandom;

public class Bonusfiche extends Fiche {
	
	private int waarde;
	private static final int MINIMUMWAARDE_BONUSFICHES = 1;
	private static final int MAXIMUMWAARDE_BONUSFICHES = 3;
	
	
	public Bonusfiche(FicheGebied f) {
		super(f);
		SecureRandom sr = new SecureRandom();
		this.waarde = sr.nextInt(MINIMUMWAARDE_BONUSFICHES, MAXIMUMWAARDE_BONUSFICHES + 1);
	}

	public int getWaarde() {
		return waarde;
	}
	
}
