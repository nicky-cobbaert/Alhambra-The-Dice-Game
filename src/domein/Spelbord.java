package domein;

public class Spelbord {

	private FicheGebied fichegebied;
	private ResultatenGebied resultatengebied;
	private GebouwpuntenGebied gebouwpuntengebied;

	public Spelbord() {
		fichegebied = new FicheGebied();
		resultatengebied = new ResultatenGebied();
		gebouwpuntengebied = new GebouwpuntenGebied();
	}

	public int geefWaardeVanPositie(int positie) {
		return fichegebied.geefWaardeVanPositie(positie);
	}
	
	public FicheGebied getFicheGebied() {
		return fichegebied;
	}
}
