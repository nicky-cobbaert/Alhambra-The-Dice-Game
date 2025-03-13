package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import utils.Kleuren;

public class Spel {
	private final List<Kleuren> beschikbareKleuren;
	private final List<Speler> gekozenSpelers;
	private Speler startSpeler;

	public Spel() {

		/** ------EnumOmzetting----------- */
		this.beschikbareKleuren = new ArrayList<>();
		/**
		 * Collections.addAll(beschikbareKleuren, "blauw", "groen", "wit", "geel",
		 * "oranje", "rood");
		 */
		for (Kleuren kleur : Kleuren.values()) {
			this.beschikbareKleuren.add(kleur);
		}

		this.gekozenSpelers = new ArrayList<>();
	}

	public List<Kleuren> getBeschikbareKleuren() {
		return beschikbareKleuren;
	}

	public List<Speler> getGekozenSpelers() {
		return gekozenSpelers;
	}

	public void kiesSpeler(Speler speler, String kleur) {

		Kleuren huidigeKleur;

		if (gekozenSpelers.size() >= 6) { // Onnodige code, is opgevangen in de console zelf met een break
			throw new IllegalArgumentException("Er mogen maximaal 6 spelers meedoen.");
		}
		if (kleur == null || kleur.isBlank()) { // onnodige code 
			throw new IllegalArgumentException("Er is geen kleur gekozen");
		}

		/**
		 * if (!beschikbareKleuren.contains(kleur)) { // Ook eigenlijk onnodige code,
		 * geven een lijst met alle beschikbare kleuren en moet daartussen kiezen dus
		 * kan geen gekozen kleur pakken... throw new IllegalArgumentException("Deze
		 * kleur is niet beschikbaar."); }
		 */

		/** ---------KleurStringNaarEnum--------------- */

		huidigeKleur = Kleuren.valueOf(kleur.toUpperCase());

//		switch (kleur.toLowerCase()) {
//		case "blauw" -> huidigeKleur = Kleuren.BLAUW;
//		case "groen" -> huidigeKleur = Kleuren.GROEN;
//		case "wit" -> huidigeKleur = Kleuren.WIT;
//		case "geel" -> huidigeKleur = Kleuren.GEEL;
//		case "oranje" -> huidigeKleur = Kleuren.ORANJE;
//		case "rood" -> huidigeKleur = Kleuren.ROOD;
//		default -> throw new IllegalArgumentException("Deze kleur is niet beschikbaar.");
//		}
		/** ------------------------------------------- */



		speler.setKleur(huidigeKleur); // was niet zichtbaar*
		gekozenSpelers.add(speler);
		beschikbareKleuren.remove(huidigeKleur);
	}

	public void startSpel() {
		if (gekozenSpelers.size() < 3) {
			throw new IllegalArgumentException("Er moeten minstens 3 spelers zijn om het spel te starten.");
		}

		Random rand = new Random();
		startSpeler = gekozenSpelers.get(rand.nextInt(gekozenSpelers.size()));

		System.out.println("Het spel is gestart!");//geen sysout
		System.out.println("Startspeler: " + startSpeler.getGebruikersnaam());

		for (Speler speler : gekozenSpelers) {
			System.out.println("Speler: " + speler.getGebruikersnaam() + ", Kleur: "
					+ speler.getKleur().toString().toLowerCase() + ", Leeftijd: " + speler.getGeboortejaar());
		}
	}

	public Speler getStartSpeler() {
		return startSpeler;
	}

	public List<Speler> geefBeschikbareSpelers(List<Speler> alleSpelers) {
		List<Speler> bs = new ArrayList<Speler>();

		for (Speler s : alleSpelers) {
			if (!gekozenSpelers.contains(s)) {
				bs.add(s);
			}
		}

		return bs;
	}
}
