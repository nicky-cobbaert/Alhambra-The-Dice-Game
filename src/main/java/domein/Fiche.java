package domein;

/**
 * De klasse {@code Fiche} stelt een fiche voor die op een bepaald fichegebied kan worden geplaatst.
 */
public class Fiche {

	  /**
     * De positie waarop de fiche zich bevindt.
     */
	private int positie;
	
	
	 /**
     * Het gebied waaraan deze fiche gekoppeld is.
     */
	private FicheGebied ficheGebied;
	
	
	
	  /**
     * Maakt een nieuwe fiche aan met een opgegeven {@link FicheGebied}.
     *
     * @param f het fichegebied waaraan de fiche wordt gekoppeld
     */
	public Fiche(FicheGebied f) {
		ficheGebied = f;
	}

	   /**
     * Geeft de huidige positie van de fiche terug.
     *
     * @return de positie van de fiche
     */
	public int getPositie() {
		return positie;
	}
	
	/**
	 * Stelt de positie van de fiche in. 
     *
     * @param positie de nieuwe positie van de fiche
     */
	public void setPositie(int positie) {
		this.positie = positie;
	}

	
	  /**
     * Plaatst de fiche neer op de opgegeven positie in het gekoppelde fichegebied.
     *
     * @param positie de positie waarop de fiche geplaatst moet worden
     * @throws IllegalArgumentException als de positie buiten het toegestane bereik valt (momenteel niet geactiveerd)
     */
	public void plaatsNeer(int positie) {
		
		
		  // Validatie tijdelijk uitgeschakeld — heractiveren indien nodig
		
//		if (positie < 0 || positie > 6) {
//			throw new IllegalArgumentException(String.format("Ongeldig fiche positie %d", positie));
//		}
		
	        ficheGebied.plaatsFicheNeer(this,positie);
	    }
	}
	

