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
	 * Hier wordt er gechecked of er al teveel spelers zijn of er wel een kleur is gekozen. Daarna zal de speler uit de lijst gehaald worden door middel van de spelerint. De kleur zal ingesteld worden en de speler zal toegevoegd worden in de lijst van gekozen spelers. De speler en kleur zullen dan verwijderd worden uit de beschikbare lijsten.
	 * 
	 * @param speler is de index van de gekozen speler in de spelerlijst.
	 * @param kleur is de kleur die de speler gekozen heeft.
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
	 * Er wordt gechecked of de juiste aantal spelers meespelen. Daarna zullen de spelers zetstenen en gebouwstenen krijgen. Het aantal keer gerold en de ronde zullen op 0 gezet worden. Daarna zal de startspeler random gekozen worden, dat wordt dan ook ingesteld bij die speler. Daarna zal een ronde gestart worden.
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
	 * Het spel is gedaan dus zal de winnaar berekent worden.
	 */
	public void beïndigSpel() {
		berekenWinnaar();
	}

	/**
	 * Dit geeft het aantal zetstenen van de eerste speler.
	 * 
	 * @return Het aantal zetstenen van de eerste speler.
	 */
	public int getAantalZetstenen() {

		return gekozenSpelers.get(0).getZetstenen().size();
	}

	/**
	 * Dit kijkt hoeveel zetstenen de spelers moeten krijgen. Uiteindelijk worden deze aangemaakt en aan een speler toegekent en aan een resultatenGebied.
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
	 * Hiermee krijgen alle gekozen spelers hun gebouwstenen.
	 */
	private void geefSpelerGebouwstenen() {
		for (Speler sp : gekozenSpelers) {
			sp.maakGebouwstenenAan(spelbord.getGebouwpuntenGebied());
			;
		}
	}

	/**
	 * Dit geeft de startspeler van de ronde.
	 * 
	 * @return De startspeler van de ronde
	 */
	public Speler getStartSpeler() {
		return startSpeler;
	}

	/**
	 * Dit geef alle beschikbare (niet-gekozen) spelers.
	 * 
	 * @return De beschikbare (niet-gekozen) spelers.
	 */
	public List<Speler> getBeschikbareSpelers() {
		return beschikbareSpelers;
	}

	/**
	 * Hier worden de winnaars berekent, dit gebeurt door alle punten van elke gekozen speler te vergelijken met elkaar. Dit retourneert een lijst omdat er meerdere winnaars kunnen zijn (meerdere mensen met hetzelfde aantal punten).
	 * 
	 * @return Een lijst van alle spelers met de hoogste score
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
	 * Hier wordt de ronde begonnen, zolang het niet de laatste ronde is wordt er een startspelerfiche geplaatst en op alle andere plaatsen wordt een bonusfiche geplaatst. De ronde zal met 1 verhoogd worden. De huidige speler zal veranderd worden en als het de derde ronde is zal de isEindeSpel op true gezet worden zodat er geen ronde meer gespeeld zal worden.
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
	 * Hier zal de startspeler op een willekeurige positie gezet worden.
	 * 
	 * @param rand is een SecureRandom waarmee de positie bepaalt wordt.
	 */
	private void plaatsStartSpelerFiche(SecureRandom rand) {
		if(startspelerfiche.getPositie() == 0) {
			List<Fiche> fiches = spelbord.getFicheGebied().getGezettefiches();
			List<Integer> vrijePlaatsen = getVrijePlaatsen(fiches);
			startspelerfiche.plaatsNeer(vrijePlaatsen.get(rand.nextInt(vrijePlaatsen.size())).intValue());
		}
	}
	
	/**
	 * Dit geeft alle vrije plaatsen op het fichegebied aan.
	 * 
	 * @param fiches zijn de gezette fiches in het fichegebied.
	 * 
	 * @return Alle vrije plaatsen op het fichegebied.
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
	 * Hiermee worden de bonusfiches geplaatst op het spelbord. Deze wordt uit de lijst gekozen door een random getal als index te gebruiken.
	 * 
	 * @param rand is een SecureRandom waarmee bepaalt wordt welk fiche er zal geplaatst worden.
	 */
	private void plaatsBonusFiches(SecureRandom rand) {
		for(int plaats:getVrijePlaatsen(spelbord.getFicheGebied().getGezettefiches())){
			int index = rand.nextInt(bonusfiches.size());
			bonusfiches.get(index).plaatsNeer(plaats);
			bonusfiches.remove(index);
		}
	}

	/**
	 * Hiermee krijg je de winaar(s) van het spel.
	 * 
	 * @return De winnaar(s) van het spel.
	 */
	public List<Speler> getWinnaar() {
		return winnaar;
	}

	/**
	 * Hiermee kom je te weten of het spel gedaan is of niet.
	 * 
	 * @return Of het spel gedaan is.
	 */
	public boolean getIsEindeSpel() {
		return isEindeSpel;
	}

	/**
	 * Hiermee rol je de dobbelsteen zolang het aantalKeerGerold minder dan 3 is. Je gaat over alle 3 de dobbelstenen en rolt ze.
	 * 
	 * @return Je geeft aan of het rollen gelukt is.
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
	 * Hiermee worden de dobbelstenen en het aantalKeerGerold terug gezet naar hun beginwaarde voor de volgende speler.
	 */
	private void resetVoorVolgendeSpeler() {
		for(Dobbelsteen d:dobbelstenen) {
			d.setNogRollen(true);
			d.setDobbelsteenKleur(null);
		}
		this.aantalKeerGerold = 0;
	}
	
	/**
	 * Hiermee verander je de status van de dobbelsteen. Als deze geselecteerd is betekent dat hij niet meer mag rollen als de speler wilt rollen met de andere dobbelstenen.
	 * 
	 * @param index is over welke dobbelsteen het gaat.
	 * 
	 * @return De status van de gekozen dobbelsteen.
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
	 * Hiermee krijg je alle dobbelstenen.
	 * 
	 * @return Alle dobbelstenen.
	 */
	public List<Dobbelsteen> getDobbelstenen(){
		return this.dobbelstenen;
	}
	
	/**
	 * Hiermee krijg je hoeveel keer er al met de dobbelstenen is gerold.
	 * 
	 * @return Hoeveel keer er al gerold is.
	 */
	public int getAantalKeerGerold() {
		return this.aantalKeerGerold;
	}
	
	/**
	 * Hiermee krijg je alle gezette fiches.
	 * 
	 * @return Alle gezette fiches.
	 */
	public List<Fiche> getGezetteFiches() {
		return spelbord.getFicheGebied().getGezettefiches();
	}
	
	/**
	 * Hiermee wordt een beurt beïndigt, de zetsteen wordt doormiddel van een positie geplaatst. De positie wordt als volgt berekent: Het is een getal met 3 (XYZ) cijfers, het honderdtal (X) wordt gezet naar zodat we weten op welke kleur de zetsteen uiteindelijk moet komen te staan.
	 * Het tiental (Y) wordt gezet op hoeveel dobbelstenen je van de gekozen kleur hebt gerold. De eenheid (Z) is in hoeveel keer je gerold hebt. Bij het plaatsen van de zetsteen wordt er gekeken of die positie al dan niet bezet is.
	 * Als deze positie bezet is zal de zetsteen naar de vorige positie geplaatst worden. Dit totdat de positie niet bezet is door een andere zetsteen. De speler van wie de zetsteen geplaatst is wordt geretourneerd
	 * Daarna wordt alles gereset voor de volgende speler en wordt de huidige speler veranderd naar de volgende in de lijst.
	 * 
	 * @param kleur is de kleur van de dobbelsteen die ze gekozen hebben.
	 * 
	 * @return De speler van wie de zetsteen geplaatst is.
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
	 * Hier worden de zetstenen verwijderd en de gebouwstenen verplaatst. De spelers krijgen hun punten en de huidige speler zal naar de nieuwe startspeler gezet worden.
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
	 * Hier worden de punten van de speler berekent, hiervoor maken we gebruik van de positie van een formule die gebruik maakt van op welke plaats de gebouwsteen stond (1 = slecht, 3 = beste) en het honderdtal van een positie die allebei worden meegegeven. 
	 * De plaats laat ons ook meteen weten in welke ronde we zijn. Plaats 3 wordt niet opgeroepen in de 2de ronde omdat dan alleen de eerste 2 punten krijgen. Hierdoor konden we onze formule zo maken dat deze methode altijd het juiste aantal punten meegeeft.
	 * 
	 * @param plaats is de plaats van de gebouwsteen.
	 * @param positieKleur is uit welke kolom (kleur) de gebouwsteen komt.
	 * 
	 * @return Het aantal punten dat de speler moet krijgen.
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
	 * Hier worden alle voorste gebouwstenen (die dus in de punten zouden vallen) per kleur (gebouwsoort) opgehaald uit het Spelbord en worden deze in een lijst gestoken. 
	 * Dan zullen de gebouwenstenen van elke speler opgehaald worden en wordt er gekeken of deze gebouwstenen in de lijst van voorste gebouwstenen staan.
	 * De punten dat de gebouwstenen waard zouden zijn worden dan met {@link #berekenPunten(int, int)} berekent en toegevoegd aan de speler. 
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
	 * Hier worden de 2 voorste zetstenen gehaald uit een resultatengebied van een kleur en in een lijst gestoken op volgorde waar de beste (hoogst gegooid met het minst aantal worden) als eerste in de lijst staat.
	 * Elke speler zijn zetstenen worden bekeken of ze in deze lijst staan. Als ze als eerste in de lijst staat, dan zal de verplaatsingswaarde 2 worden, die persoon mag 2 plaaten omhoog gaan in dat kleurengebied.
	 * Als deze als 2de in de lijst staat zal de verplaatsingswaarde maar 1 worden omdat hij dan 1 plaats omhoog mag in dat kleurengebied. Ook zal hij met {@link #geefBonus(Speler, int)} de voordelen van het bonusfiche krijgen dat bij die kleur lag in die ronde.
	 * Als ze er niet bijzitten wordt er niets gedaan.
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
	 * Eerst wordt het gewonnen bonusfiche opgehaald uit het spelbord. Daarna kijken we over welk soort fiche het gaat. Is dit een bonusfiche? Dan zullen we de speler de waarde van dit bonusfiche als punten geven.
	 * Is dit een startspelerfiche? Dan zullen we eerst zorgen dat bij iedereen staat dat ze geen startspeler zijn om ervoor te zorgen dat de startspeler van de vorige ronde niet terug mag beginnen. 
	 * Daarna wordt er wel bijgehouden dat de speler die het startspelerfiche gewonnen heeft wel de volgende ronde startspeler is.
	 * Daarna zullen we de fiches verwijderd zullen worden.
	 * 
	 * @param s is over welke speler het gaat.
	 * @param positieKleur is over welk kleurengebied van het spelbord het gaat.
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
	 * We hebben 2 mogelijke scenario's waar we van huidige speler willen veranderen. Als de ronde al bezig is of als deze pas begint. 
	 * Als de ronde al bezig is zullen we de index van de huidige speler pakken en daar eentje bij optellen zodat we aan de volgende speler in de lijst komen. 
	 * Als de speler de laatste in de lijst is zullen we terug gaan naar het begin van de lijst en zal de eerste speler van de lijst de huidige speler worden.
	 * Als we een ronde gaan beginnen willen we uiteraard dat de startspeler de huidige speler wordt. Hiervoor gaan we bij elke speler gaan kijken of hij/zij startspeler is. De persoon die startspeler is zal dan als huidige speler gezet worden.
	 * 
	 * @param type is wanneer in het spel we veranderen van huidige speler. Net als een ronde begint of tijdens een ronde.
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
	 * Hiermee geef je de huidige speler terug.
	 * 
	 * @return De huidige speler.
	 */
	public Speler getHuidigeSpeler() {
		return huidigeSpeler; 
	}

	/**
	 * Hiermee geef je de huidige ronde van het spel terug.
	 * 
	 * @return De huidige ronde van het spel.
	 */
	public int getRonde() {
		return ronde;
	}

	/**
	 * Hiermee wordt de startspeler van de huidige ronde op de juiste speler gezet. 
	 * 
	 * @param startSpeler is de startspeler van de huidige ronde.
	 */
	public void setStartSpeler(Speler startSpeler) {
		for(Speler s:gekozenSpelers) {
			s.setIsStartSpeler(false);
		}
		this.startSpeler = startSpeler;
		startSpeler.setIsStartSpeler(true);
	}
}
