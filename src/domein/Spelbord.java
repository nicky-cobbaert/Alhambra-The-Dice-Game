package domein;

/**
 * De klasse {@code Spelbord} stelt het spelbord voor waarop het spel zich afspeelt.
 * <p>
 * Het bord bestaat uit drie hoofdgebieden:
 * <ul>
 *     <li>{@link FicheGebied} – het gebied waar fiches geplaatst worden</li>
 *     <li>{@link ResultatenGebied} – het gebied waar zetstenen terechtkomen</li>
 *     <li>{@link GebouwpuntenGebied} – het gebied waar punten voor gebouwstenen worden bijgehouden</li>
 * </ul>
 */

public class Spelbord {

	/**
     * Het gebied waar fiches geplaatst worden.
     */
	
	private FicheGebied ficheGebied;
	
    /**
     * Het gebied waar zetstenen worden neergelegd om resultaten bij te houden.
     */
	
	private ResultatenGebied resultatenGebied;
	
    /**
     * Het gebied waarin de punten voor gebouwstenen worden bijgehouden.
     */
	
	private GebouwpuntenGebied gebouwpuntenGebied;
	
    /**
     * Maakt een nieuw {@code Spelbord} aan met een nieuw fichegebied, resultatengebied en gebouwpuntengebied.
     */

	public Spelbord() {
		ficheGebied = new FicheGebied();
		resultatenGebied = new ResultatenGebied();
		gebouwpuntenGebied = new GebouwpuntenGebied();
	}
	
    /**
     * Geeft toegang tot het fichegebied van het spelbord.
     *
     * @return het {@link FicheGebied} van dit spelbord
     */
	
	public FicheGebied getFicheGebied() {
		return ficheGebied;
	}
	
    /**
     * Geeft toegang tot het gebouwpuntengebied van het spelbord.
     *
     * @return het {@link GebouwpuntenGebied} van dit spelbord
     */
	
	public GebouwpuntenGebied getGebouwpuntenGebied() {
		return gebouwpuntenGebied;
	}
	
    /**
     * Geeft toegang tot het resultatengebied van het spelbord.
     *
     * @return het {@link ResultatenGebied} van dit spelbord
     */
	
	public ResultatenGebied getResultatenGebied() {
		return resultatenGebied;
	}
}
