package domein;

import java.security.SecureRandom;

import utils.DobbelsteenKleur;

/**
 * De {@link Dobbelsteen} klasse die een <strong>alhambra dobbelsteen</strong>
 * voorstelt. <br>
 * Deze klasse is onderdeel van package <strong>domein</strong>. Deze klasse
 * heeft een {@link DobbelsteenKleur} kleur en een nogrollen attribuut. Er zijn
 * voor beide attributen een setter en getter aangemaakt. <br>
 * De constructor zet het nogrollen attribuut default op true, zodat de
 * {@link Dobbelsteen} kan rollen.<br>
 * De dobbelsteen kan aangesproken worden om te rollen via de dobbel methode.
 * Deze methode gebruikt de {@link SecureRandom} klasse van de <i>Java Runtime
 * Environment System Library</i> om een willekeurig getal tussen bereik [0, 6[
 * te kiezen. Dit getal zal dan een kleur uit de {@link DobbelsteenKleur} lijst
 * kiezen en retourneren.
 */
public class Dobbelsteen {

	/**
	 * De {@link DobbelsteenKleur} die toegewezen is aan deze {@link Dobbelsteen}.
	 * Default is deze null.
	 */
	private DobbelsteenKleur kleur;

	/**
	 * nogRollen bepaalt of de {@link Dobbelsteen} de {@link dobbel()} methode
	 * gebruikt kan worden.
	 */
	private boolean nogRollen;

	/**
	 * De constructor zal default attribuut nogRollen op true zetten, zodat een
	 * nieuw aangemaakte {@link Dobbelsteen} kan rollen.
	 */
	public Dobbelsteen() {
		this.nogRollen = true;
	}

	/**
	 * De methode dobbel zal als het nogRollen attribuut waar (true) is, een
	 * {@link SecureRandom } aanmaken. Deze {@link SecureRandom } zal arbitrair een
	 * getal tussen 0 tot 5 kiezen. <br>
	 * (lengte van de {@link DobbelsteenKleur} is 6, de {@link SecureRandom} zal
	 * hierdoor bereik [0,6[ gebruiken.) <br>
	 * Dit getal wordt dan gebruikt om deze {@link Dobbelsteen} een willekeurige
	 * kleur van {@link DobbelsteenKleur} toe te wijzen.
	 */
	public void dobbel() {

		if (nogRollen) {
			int index = new SecureRandom().nextInt(0, DobbelsteenKleur.values().length);
			this.kleur = DobbelsteenKleur.values()[index];
		}
	}

	/**
	 * Stelt het nogRollen attribuut in voor deze {@link Dobbelsteen} volgens de
	 * parameter.
	 * 
	 * @param nogRollen is de {@link Boolean} waarde die setNogRollen zal toewijzen
	 *                  aan deze {@link Dobbelsteen}.
	 */
	public void setNogRollen(boolean nogRollen) {

		this.nogRollen = nogRollen;
	}

	/**
	 * Geeft het nogRollen attribuut voor deze {@link Dobbelsteen} terug via de
	 * return.
	 * 
	 * @return De {@link Boolean} waarde die aan attribuut nogRollen van deze
	 *         {@link Dobbelsteen} is toegewezen.
	 */
	public boolean getNogRollen() {

		return this.nogRollen;
	}

	/**
	 * Geeft de kleur van deze {@link Dobbelsteen} terug via de return.
	 * 
	 * @return De kleur van deze {@link Dobbelsteen}.
	 */
	public DobbelsteenKleur getDobbelsteenKleur() {
		return this.kleur;
	}

	/**
	 * Stelt een kleur in voor deze {@link Dobbelsteen} volgens de parameter.
	 * 
	 * @param De kleur dat zal toegewezen worden aan deze {@link Dobbelsteen}.
	 */
	public void setDobbelsteenKleur(DobbelsteenKleur kleur) {
		this.kleur = kleur;
	}

}
