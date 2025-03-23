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

}
