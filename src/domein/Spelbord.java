package domein;

public class Spelbord {

	private FicheGebied ficheGebied;
	private ResultatenGebied resultatenGebied;
	private GebouwpuntenGebied gebouwpuntenGebied;

	public Spelbord() {
		ficheGebied = new FicheGebied();
		resultatenGebied = new ResultatenGebied();
		gebouwpuntenGebied = new GebouwpuntenGebied();
	}

	public int geefWaardeVanPositie(int positie) {
		return ficheGebied.geefWaardeVanPositie(positie);
	}
	
	public FicheGebied getFicheGebied() {
		return ficheGebied;
	}
	
	public GebouwpuntenGebied getGebouwpuntenGebied() {
		return gebouwpuntenGebied;
	}
	public ResultatenGebied getResultatenGebied() {
		return resultatenGebied;
	}
}
