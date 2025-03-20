package domein;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import utils.Kleuren;

public class Spel {
	private final List<Kleuren> beschikbareKleuren;
	private final List<Speler> gekozenSpelers;
	private List<Speler> beschikbareSpelers;
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
		this.beschikbareSpelers = new ArrayList<>();
		
		this.gekozenSpelers = new ArrayList<>();
	}
	
	public void setBeschikbareSpelers(List<Speler> spelers) {
		this.beschikbareSpelers = spelers;
	}

	public List<Kleuren> getBeschikbareKleuren() {
		return beschikbareKleuren;
	}

	public List<Speler> getGekozenSpelers() {
		return gekozenSpelers;
	}

	public void kiesSpeler(int speler, String kleur) {

		Kleuren huidigeKleur;

		if (gekozenSpelers.size() >= 6) { // Onnodige code, is opgevangen in de console zelf met een break
			throw new IllegalArgumentException("Er mogen maximaal 6 spelers meedoen.");
		}
		if (kleur == null || kleur.isBlank()) { // onnodige code 
			throw new IllegalArgumentException("Er is geen kleur gekozen");
		}

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



		beschikbareSpelers.get(speler).setKleur(huidigeKleur); // was niet zichtbaar*
		gekozenSpelers.add(beschikbareSpelers.get(speler));
		beschikbareSpelers.remove(speler);
		beschikbareKleuren.remove(huidigeKleur);
	}

	public void startSpel() {
		if (gekozenSpelers.size() < 3) {
			throw new IllegalArgumentException("Er moeten minstens 3 spelers zijn om het spel te starten.");
		}

		SecureRandom rand = new SecureRandom();
		startSpeler = gekozenSpelers.get(rand.nextInt(gekozenSpelers.size()));

	}
	public int geefAantalZetstenen() {
		int ind = gekozenSpelers.size();
		switch(ind){
		case 3: return 5;
		case 4: return 4;
		case 5,6: return 3;
		default: return 0;
		}
	}

	public String getStartSpeler() {
		return startSpeler.getGebruikersnaam();
	}

	public List<Speler> geefBeschikbareSpelers() {
		return beschikbareSpelers;
	}
}
