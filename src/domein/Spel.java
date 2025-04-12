package domein;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import utils.DobbelsteenKleur;
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
	private List<Bonusfiche> geplaatsteBonusfiches;
	private List<Speler> winnaar;
	private int ronde=0;
	private boolean isEindeSpel=false;

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
		spelbord = new Spelbord();
		
		this.bonusfiches = new ArrayList<Bonusfiche>();
		for (int i = 0; i < MAXIMUM_AANTAL_BONUSFICHES; i++) {
			bonusfiches.add(new Bonusfiche(spelbord.getFicheGebied()));
		}
		
		startspelerfiche = new StartspelerFiche(spelbord.getFicheGebied());

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
			sp.maakGebouwstenenAan(spelbord.getGebouwpuntenGebied());
			;
		}
	}

	public String getStartSpeler() {
		return startSpeler.getGebruikersnaam();
	}

	public List<Speler> geefBeschikbareSpelers() {
		return beschikbareSpelers;
	}

	private void berekenWinnaar() {

		// Dit is voor nu nog een secure random omdat we nog geen punten berekenen!
		
//		  SecureRandom sr = new SecureRandom(); winnaar = new ArrayList<>();
//		  winnaar.add(gekozenSpelers.get(sr.nextInt(0, gekozenSpelers.size()))); //Ditkunnen ook meerdere spelers zijn! 
//		  return winnaar;
		
		winnaar = new ArrayList<Speler>(); // Initialiseer het veld
	    int hoogstepunten = -1;

	    if (gekozenSpelers != null) { // Voorkom NullPointerException als gekozenSpelers null is
	        for (Speler speler : gekozenSpelers) {
	            if (speler.getPunten() > hoogstepunten) {
	                winnaar.clear();
	                winnaar.add(speler);
	                hoogstepunten = speler.getPunten();
	            } else if (speler.getPunten() == hoogstepunten) {
	                winnaar.add(speler);
	            }
	        }
	    }
	}
		 

//		List<Speler> spelersMetHoogstePunten = new ArrayList<Speler>();
//
//		for (Speler speler : gekozenSpelers) {
//			if (speler.getPunten() > spelersMetHoogstePunten.get(0).getPunten()) {
//				// .get(0) => er is normaal gezien maar 1 winnaar, anders hebben de andere
//				// winnaars ook dezelfde punten.
//				spelersMetHoogstePunten.clear();
//				spelersMetHoogstePunten.add(speler);
//			}
//
//			if (speler.getPunten() == spelersMetHoogstePunten.get(0).getPunten()) {
//				spelersMetHoogstePunten.add(speler);
//			}
//		}
//
//		return spelersMetHoogstePunten;

	/*
	 * dit is de volgorde van hoe een ronde wordt gespeeld
	 */
	
	//1 fiches worden aangelegd
	public void startRonde() {
		// TODO UC3 code
		ronde++;
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
		
		//Code voor speelBeurt 
		
		if (ronde==3) {
			this.isEindeSpel = true;
		}
	}
	
	/*
	 * TODO UC5 hier moet speelBeurt() komen ook opgesplitst
	 */
	
	/*
	 * einde UC5 om een beurt te kunnen spelen
	 */
	
	//2 gebouwstenen worden verplaatst volgens hoe de zetstenen liggen
	
	//3 bonusfiches worden gegeven met hun punten aan de speler die het moet krijgen
	
	//4 zetstenenen worden weggehaald
	
	/*
	 * alle code staat hierboven om een ronde te kunnen spelen
	 */

	public List<Speler> getWinnaar() {
		return winnaar;
	}
	public boolean getIsEindeSpel() {
		return isEindeSpel;
	}
	
	public DobbelsteenKleur rol(int i) {
		dobbelstenen.get(i).dobbel();
		return dobbelstenen.get(i).getDobbelsteenKleur();
	}
	
	public int geefWaardeVanPositie(int positie){
		return spelbord.geefWaardeVanPositie(positie);
	}
	
}
