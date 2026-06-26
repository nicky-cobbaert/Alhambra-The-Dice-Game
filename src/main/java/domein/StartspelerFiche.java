package domein;

/**
 * De klasse {@code StartspelerFiche} stelt een fiche voor die aangeeft wie de startspeler is in het spel.
 * <p>
 * Deze fiche wordt op het {@link FicheGebied} geplaatst en is een specifieke vorm van een {@link Fiche}.
 */

public class StartspelerFiche extends Fiche {
	
	/**
     * Maakt een nieuwe {@code StartspelerFiche} aan en koppelt deze aan het opgegeven {@link FicheGebied}.
     *
     * @param f het fichegebied waarop deze startspelerfiche zich bevindt
     */
	
	public StartspelerFiche(FicheGebied f) {
		super(f);
	}
	
} 
