package domein;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import utils.DobbelsteenKleur;
import utils.SpelerKleur;

/**
 * De klasse {@code Spel} stelt het spel voor dat gespeeld wordt.
 */
public class Spel {
	
	/**
	 * De lijst van alle beschikbare (niet-gekozen) kleuren.
	 */
	private final List<SpelerKleur> beschikbareKleuren;
	
	/**
	 * De lijst van alle gekozen spelers.
	 */
	private final List<Speler> gekozenSpelers;
	
	/**
	 * De lijst van alle beschikbare (niet-gekozen) spelers.
	 */
	private List<Speler> beschikbareSpelers;
	
	/**
	 * De startspeler van de ronde.
	 */
	private Speler startSpeler;
	
	/**
	 * Het maximaal aantal bonusfiches dat er in een spel kunnen zijn.
	 */
	private static final int MAXIMUM_AANTAL_BONUSFICHES = 16;
	
	/**
	 * Het maximaal aantal dobbelstenen dat er in een spel kunnen zijn.
	 */
	private static final int MAXIMUM_AANTAL_DOBBELSTENEN = 8;
	
	/**
	 * De lijst van alle dobbelstenen van het spel.
	 */
	private List<Dobbelsteen> dobbelstenen;
	
	/**
	 * Dit is het startspelerfiche van het spel. Deze zal in ronde 1 en 2 willekeurig op een fichegebied geplaatst worden.
	 */
	private StartspelerFiche startspelerfiche;
	
	/**
	 * Dit is het spelbord dat beschikt over alle gebieden.
	 */
	private Spelbord spelbord;
	
	/**
	 * De lijst van alle bonusfiches. Deze zullen elke ronde op een fichegebied geplaatst worden.
	 */
	private List<Bonusfiche> bonusfiches;
	
	/**
	 * De lijst van spelers die gewonnen hebben.
	 */
	private List<Speler> winnaar;
	
	/**
	 * Dit geeft aan in welke ronde we zijn.
	 */
	private int ronde;
	
	/**
	 * Dit geeft aan of het spel gedaan is of niet.
	 */
	private boolean isEindeSpel;
	
	/**
	 * Dit geeft aan hoeveel keer de huidige speler al gerold heeft.
	 */
	private int aantalKeerGerold;
	
	/**
	 * Dit geeft aan wie de huidige speler is.
	 */
	private Speler huidigeSpeler;

	/**
	 * Dit maakt een nieuw spel aan. Ook worden alle dobbelstenen, het spelbord, de bonusfiches, het startspelerfiche en de lijst van gekozen spelers aangemaakt.
	 * 
	 * @param spelers zijn alle spelers dat in de database staan.
	 */
	public Spel(List<Speler> spelers) {

		this.beschikbareKleuren = Speler.geefAlleKleuren();
		
		this.beschikbareSpelers = spelers;

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


		this.gekozenSpelers = new ArrayList<>();
		
		this.isEindeSpel = false;
		
	}

	/**
	 * Hiermee kan je de beschikbare spelers instellen op de nieuwe lijst dat deze methode binnenkrijgt.
	 * 
	 * @param spelers zijn alle beschikbare (niet-gekozen) spelers.
	 */
	public void setBeschikbareSpelers(List<Speler> spelers) {
		this.beschikbareSpelers = spelers;
	}
	
	/**
	 * Dit geeft alle beschikbare (niet-gekozen) kleuren terug.
	 * 
	 * @return Alle beschikbare (niet-gekozen) kleuren.
	 */
	public List<SpelerKleur> getBeschikbareKleuren() {
		return beschikbareKleuren;
	}

	/**
	 * Dit geeft alle gekozen spelers terug.
	 *
	 * @return Alle gekozen spelers.
	 */
	public List<Speler> getGekozenSpelers() {
		return gekozenSpelers;
	}

	/**
	 * 
	 * 
	 * @param speler
	 * @param kleur
	 */
	public void kiesSpeler(int speler, SpelerKleur kleur) {

		if (gekozenSpelers.size() > 6) { // Onnodige code, is opgevangen in de console zelf met een break
			throw new IllegalArgumentException("spel.teveelSpelers");
		}
		if (kleur == null /* moet niet meer omdat we met enum werken 'Jelle'|| kleur.isBlank() */) { // onnodige code
			throw new IllegalArgumentException("kleur.exception");
		}

		Speler gekozenSpeler  = beschikbareSpelers.get(speler);// was niet zichtbaar*
		gekozenSpeler.setKleur(kleur);
		gekozenSpelers.add(gekozenSpeler);
		beschikbareSpelers.remove(gekozenSpeler);
		beschikbareKleuren.remove(kleur);

	}

	/**
	 * 
	 */
	public void startSpel() {
		if (gekozenSpelers.size() < 3 || gekozenSpelers.size() > 6) {
			throw new IllegalArgumentException("Er moeten minstens 3 spelers zijn om het spel te starten.");
		}
		geefSpelersZetstenen();
		geefSpelerGebouwstenen();
		this.aantalKeerGerold = 0;
		this.ronde = 0;
		
		SecureRandom rand = new SecureRandom();
		startSpeler = gekozenSpelers.get(rand.nextInt(gekozenSpelers.size()));
		startSpeler.setIsStartSpeler(true);
		startRonde();
	}

	/**
	 * 
	 */
	public void beïndigSpel() {
		berekenWinnaar();
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public int getAantalZetstenen() {

		return gekozenSpelers.get(0).getZetstenen().size();
	}

	/**
	 * 
	 */
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

	/**
	 * 
	 */
	private void geefSpelerGebouwstenen() {
		for (Speler sp : gekozenSpelers) {
			sp.maakGebouwstenenAan(spelbord.getGebouwpuntenGebied());
			;
		}
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public Speler getStartSpeler() {
		return startSpeler;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public List<Speler> getBeschikbareSpelers() {
		return beschikbareSpelers;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public List<Speler> berekenWinnaar() {
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

	/**
	 * 
	 */
	public void startRonde() {
		SecureRandom random = new SecureRandom();

		if (ronde ==2) {
			plaatsBonusFiches(random);
		} else {
			plaatsStartSpelerFiche(random);
			plaatsBonusFiches(random);
		}
		
			veranderHuidigeSpeler(1);
			ronde ++;
		if (ronde == 3) {
			this.isEindeSpel = true;
		}
	}
	
	/**
	 * 
	 * 
	 * @param rand
	 */
	private void plaatsStartSpelerFiche(SecureRandom rand) {
		if(startspelerfiche.getPositie() == 0) {
			List<Fiche> fiches = spelbord.getFicheGebied().getGezettefiches();
			List<Integer> vrijePlaatsen = getVrijePlaatsen(fiches);
			startspelerfiche.plaatsNeer(vrijePlaatsen.get(rand.nextInt(vrijePlaatsen.size())).intValue());
		}
	}
	
	/**
	 * 
	 * 
	 * @param fiches
	 * 
	 * @return
	 */
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
	
	/**
	 * 
	 * 
	 * @param rand
	 */
	private void plaatsBonusFiches(SecureRandom rand) {
		for(int plaats:getVrijePlaatsen(spelbord.getFicheGebied().getGezettefiches())){
			int index = rand.nextInt(bonusfiches.size());
			bonusfiches.get(index).plaatsNeer(plaats);
			bonusfiches.remove(index);
		}
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public List<Speler> getWinnaar() {
		return winnaar;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public boolean getIsEindeSpel() {
		return isEindeSpel;
	}

	/**
	 * 
	 * 
	 * @return
	 */
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
	
	/**
	 * 
	 */
	private void resetVoorVolgendeSpeler() {// moet nog private worden want wordt enkel gedaan als er een nieuwe speler speelt dus als Beurt Eïndigt
		for(Dobbelsteen d:dobbelstenen) {
			d.setNogRollen(true);
			d.setDobbelsteenKleur(null);
		}
		this.aantalKeerGerold = 0;
	}
	
	/**
	 * 
	 * 
	 * @param index
	 * 
	 * @return
	 */
	public boolean veranderStatusNogRollenDobbelsteen(int index) {
		Dobbelsteen dobbelsteen = dobbelstenen.get(index);
		if(dobbelsteen.getNogRollen() == false) {
			dobbelsteen.setNogRollen(true);
		}else {
			dobbelsteen.setNogRollen(false);
		}
		return dobbelsteen.getNogRollen();
	}
	
	/**
	 *
	 * 
	 * @return
	 */
	public List<Dobbelsteen> getDobbelstenen(){
		return this.dobbelstenen;
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public int getAantalKeerGerold() {
		return this.aantalKeerGerold;
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public List<Fiche> getGezetteFiches() {
		return spelbord.getFicheGebied().getGezettefiches();
	}
	
	/**
	 * 
	 * @param kleur
	 * 
	 * @return
	 */
	public Speler beïndigBeurt(DobbelsteenKleur kleur) {
		int positie = aantalKeerGerold;
		boolean nogHerhalen = true;
		//moet weer uit commentaar als het werkt
		positie += dobbelstenen.stream().filter(e -> e.getDobbelsteenKleur() == kleur).count() * 10;
		switch(kleur) {
			case BLAUW -> {positie += 100;}
			case ROOD  -> {positie += 200;}
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
		Speler current = huidigeSpeler;
		resetVoorVolgendeSpeler();
		veranderHuidigeSpeler(0);
		return current;
	}
	
	/**
	 * 
	 */
	public void beïndigRonde() {
		verzetDeGebouwstenen();
		geefSpelersPunten();
		for(Speler s:gekozenSpelers) {
			s.cleanUpNaRonde();
		}
		veranderHuidigeSpeler(1);
	}
	
	/**
	 * 
	 * 
	 * @param plaats
	 * @param positieKleur
	 * 
	 * @return
	 */
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

	/**
	 * 
	 */
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
	
	/**
	 * 
	 */
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
				if(verplaatsing > 0){
					s.verplaatsGebouwsteen(positieKleur, verplaatsing);
				}
				
			}
		}
	}
	
	/**
	 * 
	 * 
	 * @param s
	 * @param positieKleur
	 */
	private void geefBonus(Speler s,int positieKleur) {
		Fiche gewonnenFiche = spelbord.getFicheGebied().getGezettefiches().stream().
				filter(e -> e.getPositie() == positieKleur).
				findFirst().get();
		if(gewonnenFiche instanceof Bonusfiche b) {
			s.voegPuntenToe(b.getWaarde());
		}else {
			for(Speler speler:gekozenSpelers) {
				speler.setIsStartSpeler(false);
			}
			s.setIsStartSpeler(true);
		}
		spelbord.getFicheGebied().haalFicheWeg(gewonnenFiche);
	}

	/**
	 * 
	 * 
	 * @param type
	 */
	public void veranderHuidigeSpeler(int type) {
		//2 types mogelijk
		//0 als het tijdens een ronde is
		//1 als er een nieuwe  cycle wordt gestart
		int indexVanHuidigeSpeler = gekozenSpelers.indexOf(huidigeSpeler);
		if(type == 0) {
			
			//3 4 1 2
			if(indexVanHuidigeSpeler == gekozenSpelers.size()-1) {
				huidigeSpeler = gekozenSpelers.getFirst();
			} else {
				huidigeSpeler = gekozenSpelers.get(indexVanHuidigeSpeler+1);
			}
		}
		if(type == 1){
			Speler starter = new Speler("Onbekend",2000);
			
			for (Speler s : gekozenSpelers) {
				if (s.getIsStartSpeler()==true) {
					starter = s;
				}
			}
			
			this.startSpeler = starter;
			this.huidigeSpeler = this.startSpeler;
			return;
		}
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public Speler getHuidigeSpeler() {
		return huidigeSpeler; 
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public int getRonde() {
		return ronde;
	}

	/**
	 * 
	 * 
	 * @param startSpeler
	 */
	public void setStartSpeler(Speler startSpeler) {
		for(Speler s:gekozenSpelers) {
			s.setIsStartSpeler(false);
		}
		this.startSpeler = startSpeler;
		startSpeler.setIsStartSpeler(true);
	}
}
