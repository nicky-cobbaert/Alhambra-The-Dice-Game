package domein;

import java.security.SecureRandom;

/**
 * De klasse {@code Bonusfiche} stelt een fiche voor die een willekeurige bonuswaarde bevat.
 * <p>
 * Een {@code Bonusfiche} wordt op een {@link FicheGebied} geplaatst en heeft een willekeurige waarde tussen 1 en 3, 
 * toegekend bij creatie.
 */

public class Bonusfiche extends Fiche {
	
    /**
     * De toegekende bonuswaarde van deze fiche (tussen 1 en 3).
     */
	
	private int waarde;
	
	/**
     * De minimale waarde die een bonusfiche kan hebben.
     */
	
	private static final int MINIMUMWAARDE_BONUSFICHES = 1;
	
	/**
     * De maximale waarde die een bonusfiche kan hebben.
     */
	
	private static final int MAXIMUMWAARDE_BONUSFICHES = 3;
	
    /**
     * Maakt een nieuwe {@code Bonusfiche} aan en kent er een willekeurige waarde tussen 1 en 3 aan toe.
     *
     * @param f het {@link FicheGebied} waarop deze bonusfiche zich bevindt
     */
	
	public Bonusfiche(FicheGebied f) {
		super(f);
		SecureRandom sr = new SecureRandom();
		this.waarde = sr.nextInt(MINIMUMWAARDE_BONUSFICHES, MAXIMUMWAARDE_BONUSFICHES + 1);
	}

	/**
     * Geeft de waarde van deze bonusfiche terug.
     *
     * @return de toegekende bonuswaarde (tussen 1 en 3)
     */
		
	public int getWaarde() {
		return waarde;
	}
	
}
