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

	/*
	 private Spel speeltSpel; geen idee wat deze is 'van Jelle'
	 private List<Dobbelsteen> dobbelstenen;
	 private Startspelerfiche startspelerfiche
	 private Spelbord spelbord
	 private List<Bonusfiche> bonusfiches;
	 */
	
	public Spel() {

		/** ------EnumOmzetting----------- */
		this.beschikbareKleuren = Speler.geefAlleKleuren();
		/**
		 * Collections.addAll(beschikbareKleuren, "blauw", "groen", "wit", "geel",
		 * "oranje", "rood");
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

	public void kiesSpeler(int speler, String kleur) {

		SpelerKleur huidigeKleur;

		if (gekozenSpelers.size() > 6) { // Onnodige code, is opgevangen in de console zelf met een break
			throw new IllegalArgumentException("Er mogen maximaal 6 spelers meedoen.");
		}
		if (kleur == null || kleur.isBlank()) { // onnodige code 
			throw new IllegalArgumentException("Er is geen kleur gekozen");
		}

		/** ---------KleurStringNaarEnum--------------- */

		huidigeKleur = SpelerKleur.valueOf(kleur.toUpperCase());

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
		if (gekozenSpelers.size() < 3 || gekozenSpelers.size() > 6) {
			throw new IllegalArgumentException("Er moeten minstens 3 spelers zijn om het spel te starten.");
		}
		// size > 6 -> Exception!
		geefSpelersZetstenen();
		
		SecureRandom rand = new SecureRandom();
		startSpeler = gekozenSpelers.get(rand.nextInt(gekozenSpelers.size()));
	}
	public void speelRonde() {
		// TODO UC3 code
	}
	public void beïndigSpel() {
		clearSpelersVanAttributenNaSpelEindigt();
		// TODO UC3 code
	}
	private void clearSpelersVanAttributenNaSpelEindigt() {
		for(Speler sp:gekozenSpelers) {
			sp.clearAttributenNaSpel();
		}
	}
	public int geefAantalZetstenen() {
		
		return gekozenSpelers.get(0).getZetstenen().size();
	}
	
	private void geefSpelersZetstenen() {
		int zetsteenAantal;
		switch(gekozenSpelers.size()){
		case 3: zetsteenAantal = 5;
		case 4: zetsteenAantal = 4;
		case 5,6: zetsteenAantal = 3;
		default: zetsteenAantal = 0;
		}
		for(Speler sp:gekozenSpelers) {
			sp.maakZetstenenAan(zetsteenAantal);
		}
	}

	public String getStartSpeler() {
		return startSpeler.getGebruikersnaam();
	}

	public List<Speler> geefBeschikbareSpelers() {
		return beschikbareSpelers;
	}
}
