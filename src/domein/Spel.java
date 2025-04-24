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
	private List<Speler> winnaar;
	private int ronde;
	private boolean isEindeSpel;
	private int aantalKeerGerold;
	private Speler huidigeSpeler;

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
		
		this.isEindeSpel = false;
		
		this.ronde = 0;
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

		if (gekozenSpelers.size() > 6) { // Onnodige code, is opgevangen in de console zelf met een break
			throw new IllegalArgumentException("Er mogen maximaal 6 spelers meedoen.");
		}
		if (kleur == null /* moet niet meer omdat we met enum werken 'Jelle'|| kleur.isBlank() */) { // onnodige code
			throw new IllegalArgumentException("Er is geen kleur gekozen");
		}

		beschikbareSpelers.get(speler).setKleur(kleur); // was niet zichtbaar*
		gekozenSpelers.add(beschikbareSpelers.get(speler));
		beschikbareSpelers.remove(speler);
		beschikbareKleuren.remove(kleur);
		/*
		beschikbareSpelers.sort(null);
		gekozenSpelers.sort(null);
		*/
	}

	/*public void verwijderGekozenSpeler(int speler, SpelerKleur kleur) {

		gekozenSpelers.get(speler).setKleur(null);
		beschikbareSpelers.add(gekozenSpelers.get(speler));
		gekozenSpelers.remove(speler);
		beschikbareKleuren.add(kleur);
		/*
		beschikbareSpelers.sort(null);
		gekozenSpelers.sort(null);
		
	
	}*/

	public void startSpel() {
		if (gekozenSpelers.size() < 3 || gekozenSpelers.size() > 6) {
			throw new IllegalArgumentException("Er moeten minstens 3 spelers zijn om het spel te starten.");
		}
		// size > 6 -> Exception!
		geefSpelersZetstenen();
		geefSpelerGebouwstenen();
		this.aantalKeerGerold = 0;

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

	public int getAantalZetstenen() {

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
			sp.maakZetstenenAan(zetsteenAantal,spelbord.getResultatenGebied());
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

	public List<Speler> getBeschikbareSpelers() {
		return beschikbareSpelers;
	}

	public List<Speler> berekenWinnaar() {

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
		
		return winnaar;
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

	// 1 fiches worden aangelegd
	public void startRonde() {
		// UC3 -> Bonusfiches + startspelersfiche van positie veranderen
		SecureRandom random = new SecureRandom();
//		int positieStartSpelerFiche = random.nextInt(1, 7);
//
//		startspelerfiche.plaatsNeer(positieStartSpelerFiche);
//		for (int i = 1; i <= 6; i++) {
//			if (i != positieStartSpelerFiche) {
//				int indexWaarde = random.nextInt(0, bonusfiches.size());
//				bonusfiches.get(indexWaarde).plaatsNeer(i);
//				bonusfiches.remove(indexWaarde);
//			}
//		}
//		if(this.isEindeSpel) {
//			beïndigSpel();
//		}else {
			ronde ++;
			plaatsStartSpelerFiche(random);
			plaatsBonusFiches(random);
		//}

		// Code voor speelBeurt

		if (ronde == 3) {
			this.isEindeSpel = true;
		}
	}
	private void plaatsStartSpelerFiche(SecureRandom rand) {
		if(startspelerfiche.getPositie() == 0) {
			List<Fiche> fiches = spelbord.getFicheGebied().getGezettefiches();
			List<Integer> vrijePlaatsen = getVrijePlaatsen(fiches);
			startspelerfiche.plaatsNeer(vrijePlaatsen.get(rand.nextInt(vrijePlaatsen.size())).intValue());
		}
	}
	private List<Integer> getVrijePlaatsen(List<Fiche> fiches){
		List<Integer> vrijePlaatsen = new ArrayList<Integer>();
		for(int index = 1;index <= 6;index ++) {
			vrijePlaatsen.add(Integer.valueOf(index));
		}
		if(fiches.size() != 0) {
			for(Fiche f:fiches) {
				if(vrijePlaatsen.contains(Integer.valueOf(f.getPositie()))) {
					vrijePlaatsen.remove(Integer.valueOf(f.getPositie()));
				}
			}
		}
		return vrijePlaatsen;
	}
	
	private void plaatsBonusFiches(SecureRandom rand) {
		List<Integer> vrijePlaatsen = getVrijePlaatsen(spelbord.getFicheGebied().getGezettefiches());
		if(vrijePlaatsen.size() != 0) {
			for(Integer plaats:getVrijePlaatsen(spelbord.getFicheGebied().getGezettefiches())){
				int index = rand.nextInt(bonusfiches.size());
				bonusfiches.get(index).plaatsNeer(plaats.intValue());
				bonusfiches.remove(index);
			}
		}
	}

	/*
	 * TODO UC5 hier moet speelBeurt() komen ook opgesplitst
	 */

	/*
	 * einde UC5 om een beurt te kunnen spelen
	 */


	

	public List<Speler> getWinnaar() {
		return winnaar;
	}

	public boolean getIsEindeSpel() {
		return isEindeSpel;
	}

	public boolean rolDobbelstenen() {
		if(aantalKeerGerold < 3) {
			for(Dobbelsteen d:dobbelstenen) {
				d.dobbel();
			}
			aantalKeerGerold ++;
			return true;
		}
		return false;
	}
	
	private void resetVoorVolgendeSpeler() {// moet nog private worden want wordt enkel gedaan als er een nieuwe speler speelt dus als Beurt Eïndigt
		for(Dobbelsteen d:dobbelstenen) {
			d.setNogRollen(true);
			d.setDobbelsteenKleur(null);
		}
		this.aantalKeerGerold = 0;
	}
	
	public boolean veranderStatusNogRollenDobbelsteen(int index) {
		Dobbelsteen dobbelsteen = dobbelstenen.get(index);
		if(dobbelsteen.getNogRollen() == false) {
			dobbelsteen.setNogRollen(true);
		}else {
			dobbelsteen.setNogRollen(false);
		}
		return dobbelsteen.getNogRollen();
	}
	public List<Dobbelsteen> getDobbelstenen(){
		return this.dobbelstenen;
	}
	
	public int getAantalKeerGerold() {
		return this.aantalKeerGerold;
	}
	
	public List<Fiche> getGezetteFiches() {
		return spelbord.getFicheGebied().getGezettefiches();
	}
	public void beïndigBeurt(DobbelsteenKleur kleur) {
		int positie = aantalKeerGerold;
		boolean nogHerhalen = true;
		positie += dobbelstenen.stream().filter(e -> e.getDobbelsteenKleur() == kleur).count() * 10;
		switch(kleur) {
			case BLAUW -> {positie += 100;}
			case ROOD ->  {positie += 200;}
			case BRUIN -> {positie += 300;}
			case GRIJS -> {positie += 400;}
			case GROEN -> {positie += 500;}
			case PAARS -> {positie += 600;}
		}
		do {
			try{
				huidigeSpeler.plaatsEenZetsteenNeer(positie);
				nogHerhalen = false;
			}catch(IllegalArgumentException i){
				if(positie % 10 == 3) {
					positie -= 12;
				}else {
					positie += 1;
				}
			}
		}while(nogHerhalen);
		resetVoorVolgendeSpeler();
	}
	
	public void beïndigRonde() {
		verzetDeGebouwstenen();
		geefSpelersPunten();
		
	}
	private int berekenPunten(int plaats,int positieKleur) {
		int positie = plaats; //1 is altijd kleinste (“slechtste”), 2 is het middelste en 3 altijd het grootste (“beste”) => omgedraaid dan in de voorbeelden!
		int x = positieKleur; //XYZ getallensysteem
		int res=0;

		 if (positie == 1) {
		     res = x;
		 } else {
		     res = x + ((positie-1)*8 - 1);
		 }
		 return res;
	}
	private void geefSpelersPunten() {
		for(int positieKleur = 1;positieKleur <= 6;positieKleur ++) {
			List<Gebouwsteen> gebouwstenen = spelbord.getGebouwpuntenGebied().getVoorsteGebouwstenen(ronde,positieKleur);
			if(gebouwstenen.size() != 0) {
				for(Speler s:gekozenSpelers) {
					int plaats = 0;
					for(Gebouwsteen g:gebouwstenen) {
						plaats ++;
						if(s.getGebouwstenen().contains(g)) {
							s.voegPuntenToe(berekenPunten(gebouwstenen.size() - plaats + 1,positieKleur));
						}
					}
				}
			}
			
		}
		
	}
	private void verzetDeGebouwstenen() {
		for(int positieKleur = 1;positieKleur <= 6;positieKleur ++) {
			List<Zetsteen> zetstenen = spelbord.getResultatenGebied().getVoorsteZetstenen(positieKleur);
			for(Speler s:gekozenSpelers) {
				int plaats = 0;
				int verplaatsing = 0;
				for(Zetsteen z:zetstenen) {
					plaats ++;
					if(s.getZetstenen().contains(z)) {
						if(plaats == 1) {
							verplaatsing += 2;
						}else {
							verplaatsing += 1;
							geefBonus(s,positieKleur);
						}
					}
				}
				s.verplaatsGebouwsteen(positieKleur, verplaatsing);
			}
		}
	}
	private void geefBonus(Speler s,int positieKleur) {
		if(spelbord.getFicheGebied().getGezettefiches().get(positieKleur) instanceof Bonusfiche b) {
			s.voegPuntenToe(b.getWaarde());
		}else {
			for(Speler speler:gekozenSpelers) {
				speler.setIsStartSpeler(false);
			}
			s.setIsStartSpeler(true);
		}
		spelbord.getFicheGebied().haalFicheWeg(spelbord.getFicheGebied().getGezettefiches().get(positieKleur));
	}
	/*
	 * alle code staat hierboven om een ronde te kunnen spelen
	 */
}
