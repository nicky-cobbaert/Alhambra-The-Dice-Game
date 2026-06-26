package domein;

/**
 * De klasse {@code Zetsteen} stelt een zetsteen voor die op een specifiek vak op het resultatengebied geplaatst kan worden.
 * <p>
 * De positie wordt als een driecijferig getal gecodeerd waarbij:
 * <ul>
 *   <li>Het honderdtal de kleur voorstelt (1–6)</li>
 *   <li>Het tiental de verticale positie (rij, 1–8)</li>
 *   <li>Het eenheidscijfer de horizontale positie (kolom, 1–3)</li>
 * </ul>
 */
public class Zetsteen{

    /**
     * De positie van de zetsteen binnen het gebied.
     */
	private int positie;
	
	  /**
     * Het {@link ResultatenGebied} waarin deze zetsteen zich bevindt.
     */
	private ResultatenGebied resultatenGebied;

	  /**
     * Maakt een nieuwe zetsteen aan in het opgegeven {@link ResultatenGebied}.
     *
     * @param resultatenGebied het resultatengebied waaraan deze zetsteen gekoppeld wordt
     */
	public Zetsteen(ResultatenGebied resultatenGebied) {
		this.resultatenGebied = resultatenGebied;
		
	}


	 /**
     * Plaatst deze zetsteen op het bord met de opgegeven gecodeerde positie.
     * <p>
     * De positie wordt verwacht als een driecijferig getal:
     * <ul>
     *   <li>Kleur (1–6) op de honderdtallenplaats</li>
     *   <li>Verticale positie (1–8) op de tientallenplaats</li>
     *   <li>Horizontale positie (1–3) op de eenhedenplaats</li>
     * </ul>
     *
     * @param positie de gecodeerde positie waarop de steen wordt geplaatst
     * @throws IllegalArgumentException als de kleur, verticale of horizontale positie buiten het toegestane bereik ligt
     */
	public void plaatsNeer(int positie) throws IllegalArgumentException {

		if (positie < 111 || positie > 683) {
			throw new IllegalArgumentException(
					"De positie moet een kleur tussen [1,6] , verticale positie tussen [1,8] en een horizontale positie tussen [1,3] hebben!");
		}
		if (((positie / 100) % 10) < 1 || ((positie / 100) % 10) > 6) {
			throw new IllegalArgumentException("De kleur moet tussen 1 en 6 liggen!");
		}

		if (((positie / 10) % 10) < 1 || ((positie / 10) % 10) > 8) {
			throw new IllegalArgumentException("De verticale positie moet tussen 1 en 8 liggen!");
		}

		if ((positie % 10) < 1 || (positie % 10) > 3) {
			throw new IllegalArgumentException("De horizontale positie moet tussen 1 en 3 liggen!");
		}

		resultatenGebied.plaatsZetsteenNeer(this,positie);
	}
	
	 /**
     * Verwijdert de zetsteen van het speelbord door de positie op 0 te zetten.
     */
	public void gaVanHetSpelbord() {
		this.positie = 0;
	}
	
    /**
     * Stelt de positie van de zetsteen expliciet in.
     *
     * @param positie de nieuwe positie
     */
	public void setPositie(int positie) {
		this.positie = positie;
	}

	  /**
     * Geeft de huidige gecodeerde positie van de zetsteen terug.
     *
     * @return de positie van de zetsteen
     */
	public int getPositie() {
		return positie;
	}
	
}
