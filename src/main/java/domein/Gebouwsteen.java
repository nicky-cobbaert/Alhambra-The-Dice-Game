package domein;


/**
 * De klasse {@code Gebouwsteen} stelt een speelsteen voor die op een bepaald
 * gebouwpuntengebied kan worden geplaatst en verplaatst.
 */
public class Gebouwsteen {

	  /**
     * De positie van de gebouwsteen binnen het gebied.
     */
	private int positie;
	
	  /**
     * De volgorde van de gebouwsteen ten opzichte van andere stenen.
     */
	private int volgorde;
	
    /**
     * Het {@link GebouwpuntenGebied} waaraan deze gebouwsteen gekoppeld is.
     */
	private GebouwpuntenGebied gebouwpuntGebied;
	
	 /**
     * Maakt een nieuwe gebouwsteen aan in het opgegeven {@link GebouwpuntenGebied}.
     *
     * @param gebouwpuntGebied het gebied waaraan de steen wordt toegevoegd
     */
	public Gebouwsteen(GebouwpuntenGebied gebouwpuntGebied) {
		this.gebouwpuntGebied = gebouwpuntGebied;
	}
	
	
	/**
     * Geeft de huidige positie van de gebouwsteen terug.
     *
     * @return de positie van de gebouwsteen
     */
	public int getPositie(){
		return positie;
	}
	
	
	  /**
     * Stelt de positie van de gebouwsteen in.
     *
     * @param positie de nieuwe positie
     */
	public void setPositie(int positie) {
		this.positie = positie;
	}
	
	  /**
     * Vraagt het {@link GebouwpuntenGebied} om deze gebouwsteen neer te plaatsen
     * op de opgegeven positie.
     *
     * @param positie de doelpositie
     */
	public void plaatsNeer(int positie) {
		gebouwpuntGebied.plaatsGebouwsteenNeer(this,positie);
	}
	
	  /**
     * Verplaatst de gebouwsteen met het opgegeven aantal plaatsen.
     * De verplaatsing moet tussen 1 en 3 liggen.
     *
     * @param verplaatsing het aantal posities om te verplaatsen
     * @throws IllegalArgumentException als de verplaatsing kleiner dan 1 of groter dan 3 is
     */
	public void verplaats(int verplaatsing) {
		if(verplaatsing <= 0||verplaatsing > 3) {
			throw new IllegalArgumentException("de verplaatsing is te klein (<=0) of te groot (>3)");
		}
		gebouwpuntGebied.verplaatsGebouwsteen(this, verplaatsing);
	}
	
	 /**
     * Geeft de volgorde van deze gebouwsteen terug.
     *
     * @return de volgorde
     */
	public int getVolgorde() {
		return this.volgorde;
	}
	
	   /**
     * Stelt de volgorde van deze gebouwsteen in.
     *
     * @param volgorde de nieuwe volgorde
     */
	public void setVolgorde(int volgorde) {
		this.volgorde = volgorde;
	}
	
	 /**
     * Plaatst deze gebouwsteen naast een andere door de volgorde op {@code volgordeAndere + 1} te zetten.
     *
     * @param volgordeAndere de volgorde van de andere gebouwsteen
     */
	public void plaatsNaast(int volgordeAndere) {
		setVolgorde(volgordeAndere + 1);
	}
}
