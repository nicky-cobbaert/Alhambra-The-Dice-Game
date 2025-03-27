package domein;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import utils.SpelerKleur;

public class Spel {
	private final List<SpelerKleur> beschikbareKleuren;
	private final List<Speler> gekozenSpelers;
	private List<Speler> beschikbareSpelers;
	private Speler startSpeler;
	private static final int MAXIMUM_AANTAL_BONUSFICHES = 16;
	private static final int MAXIMUM_AANTAL_DOBBELSTENEN = 8;
	private List<Dobbelsteen> dobbelstenen;
	private StartspelerFiche startspelerfiche;
	private Spelbord spelbord;
	private List<Bonusfiche> bonusfiches;
	private List<Speler> winnaar;

	public Spel() {

		this.beschikbareKleuren = Speler.geefAlleKleuren();

		/**
		 * --Dobbelsteen, bonusfiches
		 * aanmaken---------------------------------------------------
		 */

		this.dobbelstenen = new ArrayList<Dobbelsteen>();
		for (int i = 0; i < MAXIMUM_AANTAL_DOBBELSTENEN; i++) {
			dobbelstenen.add(new Dobbelsteen());
		}

		this.bonusfiches = new ArrayList<Bonusfiche>();
		for (int i = 0; i < MAXIMUM_AANTAL_BONUSFICHES; i++) {
			bonusfiches.add(new Bonusfiche());
		}
		spelbord = new Spelbord();
		startspelerfiche = new StartspelerFiche();

		/**
		 * ---einde Dobbelsteen, bonusfiches , spelbord
		 * aanmaken-----------------------------------------------------------------
		 */

		this.beschikbareSpelers = new ArrayList<>();

		this.gekozenSpelers = new ArrayList<>();
	}

	public void setBeschikbareSpelers(List<Speler> spelers) {
		this.beschikbareSpelers = spelers;
	}

	public List<SpelerKleur> getBeschikbareKleuren() {
		return beschikbareKleuren;
	}

	public List<Speler> getGekozenSpelers() {
		return gekozenSpelers;
	}

	public void kiesSpeler(int speler, SpelerKleur kleur) {

		if (gekozenSpelers.size() >= 6) { // Onnodige code, is opgevangen in de console zelf met een break
			throw new IllegalArgumentException("Er mogen maximaal 6 spelers meedoen.");
		}
		if (kleur == null /* moet niet meer omdat we met enum werken 'Jelle'|| kleur.isBlank() */) { // onnodige code
			throw new IllegalArgumentException("Er is geen kleur gekozen");
		}

		beschikbareSpelers.get(speler).setKleur(kleur); // was niet zichtbaar*
		gekozenSpelers.add(beschikbareSpelers.get(speler));
		beschikbareSpelers.remove(speler);
		beschikbareKleuren.remove(kleur);
	}

	public void startSpel() {
		if (gekozenSpelers.size() < 3 || gekozenSpelers.size() > 6) {
			throw new IllegalArgumentException("Er moeten minstens 3 spelers zijn om het spel te starten.");
		}
		// size > 6 -> Exception!
		geefSpelersZetstenen();
		geefSpelerGebouwstenen();

		SecureRandom rand = new SecureRandom();
		startSpeler = gekozenSpelers.get(rand.nextInt(gekozenSpelers.size()));
	}

	public void speelRonde() {
		// TODO UC3 code
		// UC3 -> Bonusfiches + startspelersfiche van positie veranderen
		SecureRandom random = new SecureRandom();
		int positieStartSpelerFiche = random.nextInt(1, 7);

		startspelerfiche.plaatsNeer(positieStartSpelerFiche);
		for (int i = 1; i < 7; i++) {
			if (i != positieStartSpelerFiche) {
				int indexWaarde = random.nextInt(0, bonusfiches.size());
				bonusfiches.get(indexWaarde).plaatsNeer(i);
				bonusfiches.remove(indexWaarde);
			}
		}
	}

	public void beïndigSpel() {
		clearSpelersVanAttributenNaSpelEindigt();
		// TODO UC3 code

	}

	private void clearSpelersVanAttributenNaSpelEindigt() {
		for (Speler sp : gekozenSpelers) {
			sp.clearAttributenNaSpel();
		}
	}

	public int geefAantalZetstenen() {

		return gekozenSpelers.get(0).getZetstenen().size();
	}

	private void geefSpelersZetstenen() {
		int zetsteenAantal;
		switch (gekozenSpelers.size()) {
		case 3 -> {
			zetsteenAantal = 5;
		}
		case 4 -> {
			zetsteenAantal = 4;
		}
		case 5, 6 -> {
			zetsteenAantal = 3;
		}
		default -> {
			zetsteenAantal = 0;
		}
		}
		for (Speler sp : gekozenSpelers) {
			sp.maakZetstenenAan(zetsteenAantal);
		}
	}

	private void geefSpelerGebouwstenen() {
		for (Speler sp : gekozenSpelers) {
			sp.maakGebouwstenenAan();
			;
		}
	}

	public String getStartSpeler() {
		return startSpeler.getGebruikersnaam();
	}

	public List<Speler> geefBeschikbareSpelers() {
		return beschikbareSpelers;
	}

	public List<Speler> berekenWinnaar() {

		// Dit is voor nu nog een secure random omdat we nog geen punten berekenen!
		SecureRandom sr = new SecureRandom();
		winnaar = new ArrayList<>();
		winnaar.add(gekozenSpelers.get(sr.nextInt(0, gekozenSpelers.size()))); //Dit kunnen ook meerdere spelers zijn!
		return winnaar;

		/**
		 * Code voor winnaar te berekenen
		 * 
		 * Speler huidigHoogste = gekozenSpelers.get(0);
		 * 
		 * for (Speler speler : gekozenSpelers) { if (speler.getPunten() >
		 * huidigeHoogste.getPunten()) { huidigHoogste = speler; } }
		 * 
		 * return huidigHoogste;
		 */
	}
}
