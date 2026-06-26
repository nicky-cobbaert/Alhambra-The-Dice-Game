package domein;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.DobbelsteenDTO;
import dto.FicheDTO;
import dto.GebouwsteenDTO;
import dto.SpelerDTO;
import dto.ZetsteenDTO;
import persistentie.SpelerMapper;
import utils.DobbelsteenKleur;
import utils.SpelerKleur;

/**
 * De {@code DomeinController} vormt de brug tussen de gebruikersinterface (CLI of GUI)
 * en de domeinlogica. Deze klasse beheert spelers, het spelverloop en de communicatie
 * met de repository (in zowel online- als offlinemodus).
 * <p>
 * Afhankelijk van de modus (CLI of GUI) wordt foutafhandeling anders behandeld.
 */

public class DomeinController {

    /**
     * Repository die verantwoordelijk is voor het ophalen en opslaan van spelers.
     */
	
	private final SpelerRepository spelerRepo;
    
	/**
     * De instantie van het spel dat op dit moment wordt gespeeld.
     */
	
	private Spel spel;
	
    /**
     * Vlag die aanduidt of de GUI-versie wordt gebruikt.
     * <p>
     * Indien {@code true}, wordt vertaling gebruikt. Indien {@code false}, wordt aangenomen
     * dat de CLI-versie draait en is vertaling niet van toepassing.
     */
	
	private boolean isGUI; // Nodig voor vertaling, bepaald of je in cli zit (geen vertaling) of gui (wel
							// vertaling)
	
    /**
     * Vlag die aanduidt of de applicatie in offline modus draait.
     */
	
	private boolean isOffline;
	
    /**
     * Maakt een nieuwe {@code DomeinController} aan en probeert verbinding te maken
     * met de online spelerrepository. Bij mislukken wordt automatisch overgeschakeld
     * naar een offlinemodus.
     */
	
	public DomeinController() {

		// try catch dient voor de offlinemodus te starten als er geen verbinding met
		// VICHogent is.
		
		

		SpelerRepository tempSpelerRepo;// ik wou de spelerRepo final houden

		try {
			tempSpelerRepo = new SpelerRepository();
			isOffline = false;//niet echt nodig: jvm boolean is default false
			
		} catch (RuntimeException e) {
			// als er geen internet is zal nu het programma automatisch offlinemode
			// aanzetten

			if (e.getCause() instanceof UnknownHostException || e.getCause() instanceof ConnectException
					|| e.getMessage().contains("Communications link failure")) {
				SpelerMapper.startOfflineModus();
				isOffline = true;
				tempSpelerRepo = new SpelerRepository();

			} else {
				throw e;
			}
		}

		spelerRepo = tempSpelerRepo;
		isGUI = false; // Default is cli
	}

    /**
     * Geeft aan of het spel in offlinemodus draait.
     * 
     * @return {@code true} indien offline, anders {@code false}
     */
	
	public boolean isOffline() {
		return isOffline;
	}
	
    /**
     * Stelt in of de controller in GUI-modus werkt.
     *
     * @param isGUI {@code true} voor GUI, {@code false} voor CLI
     */
	
	public void setGUIMode(boolean isGUI) {
		this.isGUI = isGUI;
	}
	
    /**
     * Vertaalt foutmeldingssleutels naar begrijpelijke boodschappen (alleen in CLI).
     *
     * @param key de foutmelding sleutel
     * @return vertaalde foutboodschap of sleutel zelf indien niet gevonden
     */
	
	public String translateErrorKey(String key) {
		// Zorgt ervoor dat berichten in cli mooi worden weergegeven
		if (!isGUI) {
			// Illegal argument exception word mooit gezet
			Map<String, String> errorMessages = new HashMap<>();
			errorMessages.put("gebruikersnaam.teKort", "Gebruikersnaam moet minimaal 6 karakters bevatten");
			errorMessages.put("geboortedatum.ongeldig",
					"Je moet tussen de leeftijd van 6 en 100 zijn om dit spel te mogen spelen");
			errorMessages.put("kleur.exception", "Er is nog geen kleur geselecteerd");
			errorMessages.put("spel.teveelSpelers", "Er mogen maximaal 6 spelers meedoen");
			errorMessages.put("gebruikersnaam.gebruik", "Deze gebruikersnaam is al in gebruik");
			// Add other translations as needed

			return errorMessages.getOrDefault(key, key); // als er niks gevonden is default
		}
		return key; // In GUI mode wordt de sluitel naar resourcebundel gestuurd
	}

    /**
     * Registreert een nieuwe speler en voegt die toe aan de repository.
     *
     * @param gebruikersnaam de naam van de speler
     * @param geboortejaar het geboortejaar van de speler
     * @throws IllegalArgumentException indien ongeldig
     */
	
	public void registreerSpeler(String gebruikersnaam, int geboortejaar) {
		try {
			Speler nieuweSpeler = new Speler(gebruikersnaam, geboortejaar);
			spelerRepo.voegToe(nieuweSpeler);
		} catch (IllegalArgumentException e) {
			// Translate the error message if it's a key
			String translatedMessage = translateErrorKey(e.getMessage());
			throw new IllegalArgumentException(translatedMessage);
		}
	}

//	public DomeinController() {
//		spelerRepo = new SpelerRepository();
////        huidigeSpelers = new Speler();
//	}
//
//	public void registreerSpeler(String gebruikersnaam, int geboortejaar) {
//		Speler nieuweSpeler = new Speler(gebruikersnaam, geboortejaar);
//		spelerRepo.voegToe(nieuweSpeler);
//	}
	
    /**
     * Initialiseert een nieuw spel met de geregistreerde spelers.
     */

	public void maakNieuwSpel() {
		spel = new Spel(spelerRepo.geefAlleSpelers());
	}
	
    /**
     * Start het spel (voorafgaand aan de eerste ronde).
     */

	public void startSpel() {
		spel.startSpel();
	}
	
    /**
     * Start een nieuwe spelronde.
     */

	public void startRonde() {
		spel.startRonde();
	}

//    public List<Speler> geefAlleSpelers() { 
//    	return spelerRepo.geefAlleSpelers();
//    }
//    
	
    /**
     * Kiest een speler en wijst een kleur toe.
     *
     * @param s index van de speler in de lijst
     * @param kleur gewenste {@link SpelerKleur}
     */
	
	public void kiesSpelerEnKleur(int s, SpelerKleur kleur) {
		spel.kiesSpeler(s, kleur); // s is de plaats in de arrayList!
	}

	/*
	 * public void verwijderSpelerEnKleur(int s, SpelerKleur kleur) {
	 * spel.verwijderGekozenSpeler(s, kleur); // s is de plaats in de arrayList!
	 * 
	 * 
	 * }
	 */

	
    /**
     * Geeft een lijst van beschikbare spelers in DTO-vorm.
     *
     * @return lijst van beschikbare spelers
     */
	
	public List<SpelerDTO> geefBeschikbareSpelers() {

		List<Speler> speler = spel.getBeschikbareSpelers();
		return zetSpelersOmNaarSpelerDTOs(speler);

	}
	
	/**
	 * Zet een lijst van Speler-objecten om naar een lijst van SpelerDTO’s.
	 * 
	 * @param spelers De lijst met spelers.
	 * @return Een lijst met SpelerDTO-objecten.
	 */

	private List<SpelerDTO> zetSpelersOmNaarSpelerDTOs(List<Speler> spelers) {
		List<SpelerDTO> resultaat = new ArrayList<>();

		for (Speler s : spelers) {
			SpelerDTO sp = zetSpelerOmNaarDTO(s);
			resultaat.add(sp);
		}

		return resultaat;
	}
	
	/**
	 * Zet een lijst van Zetsteen-objecten om naar een lijst van ZetsteenDTO’s.
	 * 
	 * @param zetstenen De lijst met zetstenen.
	 * @return Een lijst met ZetsteenDTO-objecten, of null als de invoer null is.
	 */

	private List<ZetsteenDTO> zetZetsteenNaarDTO(List<Zetsteen> zetstenen) {
		List<ZetsteenDTO> zetsteenDTOs = new ArrayList<ZetsteenDTO>();
		if (zetstenen == null) {
			return null;
		}
		for (Zetsteen z : zetstenen) {
			zetsteenDTOs.add(new ZetsteenDTO(z.getPositie()));
		}
		return zetsteenDTOs;
	}
	
	/**
	 * Zet een lijst van Gebouwsteen-objecten om naar een lijst van GebouwsteenDTO’s.
	 * 
	 * @param gebouwstenen De lijst met gebouwstenen.
	 * @return Een lijst met GebouwsteenDTO-objecten, of null als de invoer null is.
	 */

	private List<GebouwsteenDTO> zetGebouwsteenNaarDTO(List<Gebouwsteen> gebouwstenen) {
		List<GebouwsteenDTO> gebouwsteenDTOs = new ArrayList<GebouwsteenDTO>();
		if (gebouwstenen == null) {
			return null;
		}
		for (Gebouwsteen g : gebouwstenen) {
			gebouwsteenDTOs.add(new GebouwsteenDTO(g.getPositie(), g.getVolgorde()));
		}
		return gebouwsteenDTOs;
	}
	
	/**
	 * Zet een SpelerKleur om naar een ANSI-kleurcode voor console-uitvoer.
	 * 
	 * @param kleur De SpelerKleur.
	 * @return De ANSI-kleurcode als string.
	 * @throws IllegalArgumentException Als de kleur niet beschikbaar is.
	 */

	public static String kleurGever(SpelerKleur kleur) {

		switch (kleur.toString().toLowerCase()) {
		case "blauw":
			return "\u001B[34m";
		case "groen":
			return "\u001B[32m";
		case "wit":
			return "\u001B[37m";
		case "geel":
			return "\033[38;2;254;193;7m";
		case "oranje":
			return "\033[38;2;255;147;0m";
		case "rood":
			return "\u001B[31m";
		default:
			throw new IllegalArgumentException("Deze kleur is niet beschikbaar.");
		}
	}
	
	/**
	 * Zet een SpelerKleur om naar een GUI-kleur in Engelse notatie.
	 * 
	 * @param kleur De SpelerKleur.
	 * @return De stringrepresentatie van de kleur voor GUI.
	 * @throws IllegalArgumentException Als de kleur niet beschikbaar is.
	 */

	public static String kleurGeverGuiEditie(SpelerKleur kleur) {

		switch (kleur.toString().toLowerCase()) {
		case "blauw":
			return "blue";
		case "groen":
			return "green";
		case "wit":
			return "white";
		case "geel":
			return "yellow";
		case "oranje":
			return "orange";
		case "rood":
			return "red";
		default:
			throw new IllegalArgumentException("Deze kleur is niet beschikbaar.");
		}
	}
	
    /**
     * Geeft een lijst van reeds gekozen spelers.
     *
     * @return lijst van gekozen spelers
     */

	public List<SpelerDTO> geefGekozenSpelers() {
		List<Speler> speler = spel.getGekozenSpelers();
		return zetSpelersOmNaarSpelerDTOs(speler);
	}
	
    /**
     * Geeft beschikbare spelerkleuren.
     *
     * @return lijst van beschikbare kleuren
     */

	public List<SpelerKleur> geefBeschikbareSpelerKleuren() {

		return spel.getBeschikbareKleuren();
	}

    /**
     * Geeft de startspeler.
     *
     * @return DTO van de startspeler
     */
	
	public SpelerDTO geefStartspeler() {
		return zetSpelerOmNaarDTO(spel.getStartSpeler());
	}
	
    /**
     * Geeft het aantal zetstenen in het spel.
     *
     * @return aantal zetstenen
     */

	public int geefAantalZetstenen() {
		return spel.getAantalZetstenen();
	}

    /**
     * Update de statistieken van spelers die gewonnen hebben.
     */
	
	public void updateGewonnen() {
		
		for (Speler s : spel.getWinnaar()) {
			spelerRepo.updateGewonnen(s.getGebruikersnaam());
		}

	}
	
    /**
     * Update de statistieken van alle spelers die het spel gespeeld hebben.
     */

	public void updateGespeeld() {
		for (Speler s : spel.getGekozenSpelers()) {
			spelerRepo.updateGespeeld(s.getGebruikersnaam());
		}
	}
	
	/**
	 * Start een nieuwe ronde in het spel.
	 */

	public void speelRonde() {
		spel.startRonde();
	}
	
    /**
     * Geeft een lijst met de winnaars van het huidige spel.
     *
     * @return lijst van winnaars
     */

	public List<SpelerDTO> geefWinnaars() {
		List<Speler> speler = spel.getWinnaar();
		return zetSpelersOmNaarSpelerDTOs(speler);
	}
	
    /**
     * Geeft aan of het spel ten einde is.
     *
     * @return {@code true} indien het spel gedaan is
     */

	public boolean getIsEindeSpel() {
		return spel.getIsEindeSpel();
	}
	
	/**
	 * Zoekt een speler met een naam die het meest overeenkomt met de opgegeven naam.
	 * 
	 * @param onzeNaam De naam die gezocht wordt.
	 * @return Een map met overeenkomstige namen (als lijst) en het aantal gelijke letters.
	 */

	public Map<String, Object> zoekDezeSpeler(String onzeNaam) {

		List<String> overeenkomstigeNamen = new ArrayList<>();
		List<Integer> indexenVanDeNamen = new ArrayList<>();// anders werkt de zoekfucntie niet
		List<Integer> geboorteDatums = new ArrayList<>();
		int naamindex = 0;

		int meesteLetters = 0;

		for (SpelerDTO naamVanLijst : geefBeschikbareSpelers()) {
			++naamindex;

			int gelijkeLetters = 0;

			for (int i = 0; i < naamVanLijst.gebruikersnaam().toCharArray().length; i++) {

				if (onzeNaam.length() == i) {
					break;
					// De lus breakt als de naam zijn laatste letter bereikt is
				}

				if (onzeNaam.toLowerCase().charAt(i) == naamVanLijst.gebruikersnaam().toLowerCase().charAt(i)) {
					gelijkeLetters++;
					// lus test of elke letter van een naam gelijk is
					// aan de gezochte naam zijn letter
				}

			} // einde for voor letters ( --------------------------

			if (gelijkeLetters == meesteLetters) {
				overeenkomstigeNamen.add(naamVanLijst.gebruikersnaam());
				indexenVanDeNamen.add(naamindex);
				geboorteDatums.add(naamVanLijst.geboortejaar());
				meesteLetters = gelijkeLetters;
			} else if (gelijkeLetters > meesteLetters) {
				overeenkomstigeNamen.clear();
				overeenkomstigeNamen.add(naamVanLijst.gebruikersnaam());
				indexenVanDeNamen.clear();
				indexenVanDeNamen.add(naamindex);
				geboorteDatums.clear();
				geboorteDatums.add(naamVanLijst.geboortejaar());
				meesteLetters = gelijkeLetters;
			}
		} // einde for-lus voor alle spelers --------------------------------------

		// alles samenvoegen---------
		List<String> zoekResultaat = new ArrayList<String>();
		for (int i = 0; i < overeenkomstigeNamen.size(); i++) {

			zoekResultaat.add(String.format("%d. %s - %d", indexenVanDeNamen.get(i), overeenkomstigeNamen.get(i),
					geboorteDatums.get(i)));

		}

		// ----------------------------

		Map<String, Object> resultaat = new HashMap<>();
		resultaat.put("overeenkomstigeNamen", zoekResultaat);
		resultaat.put("meesteLetters", meesteLetters);

		return resultaat;

	}
	
    /**
     * Geeft de dobbelstenen van het huidige spel in DTO-vorm.
     *
     * @return lijst met dobbelsteenDTOs
     */

	public List<DobbelsteenDTO> getDobbelstenenDTOs() {
		List<DobbelsteenDTO> dobbelsteenDTOs = new ArrayList<DobbelsteenDTO>();
		for (Dobbelsteen d : spel.getDobbelstenen()) {
			DobbelsteenDTO dobbelDTO = new DobbelsteenDTO(d.getDobbelsteenKleur(), d.getNogRollen());
			dobbelsteenDTOs.add(dobbelDTO);
		}
		return dobbelsteenDTOs;
	}

    /**
     * Verandert de status van een dobbelsteen zodat hij niet opnieuw gegooid wordt.
     *
     * @param index index van de dobbelsteen
     * @return {@code true} indien de status met succes werd gewijzigd
     */
	
	public boolean veranderStatusNogRollenDobbelsteen(int index) {
		return spel.veranderStatusNogRollenDobbelsteen(index);
	}

	/**
	 * Geeft het aantal keren dat er is gerold in het spel.
	 * 
	 * @return Het aantal worpen.
	 */
	
	public int getAantalKeerGerold() {
		return spel.getAantalKeerGerold();
	}
	
    /**
     * Gooit de dobbelstenen voor deze beurt.
     *
     * @return {@code true} indien de dobbelstenen werden gegooid
     */

	public boolean rolDobbelstenen() {
		return spel.rolDobbelstenen();
	}

    /**
     * Geeft de geplaatste fiches als DTOs.
     *
     * @return lijst van ficheDTOs
     */
	
	public List<FicheDTO> getGeplaatsteFicheDTOs() {
		List<FicheDTO> ficheDTOs = new ArrayList<FicheDTO>();
		for (Fiche f : spel.getGezetteFiches()) {
			FicheDTO ficheDTO;
			if (f instanceof Bonusfiche bf) {
				ficheDTO = new FicheDTO(bf.getPositie(), bf.getWaarde());
			} else {
				ficheDTO = new FicheDTO(f.getPositie(), 0);
			}
			ficheDTOs.add(ficheDTO);
		}
		return ficheDTOs;
	}
	
    /**
     * Laat een spelerbeurt eindigen.
     *
     * @param kleur kleur van de dobbelsteen die eindigt
     * @return de volgende speler
     */

	public SpelerDTO beïndigBeurt(DobbelsteenKleur kleur) {
		return zetSpelerOmNaarDTO(spel.beïndigBeurt(kleur));
	}
	
    /**
     * Beeïndigt de huidige ronde.
     */

	public void beïndigRonde() {
		spel.beïndigRonde();
	}
	
    /**
     * Beeïndigt het spel en voegt eventueel willekeurige punten toe in CLI-modus.
     */

	public void beïndigSpel() {
		if(isGUI==false) {
			for(Speler s : spel.getGekozenSpelers()) {
				SecureRandom sr = new SecureRandom();
				s.voegPuntenToe(sr.nextInt(0, 100));
			}
		}
		
		spel.beïndigSpel();
	}
	
    /**
     * Start offlinemodus expliciet.
     */

	public void startOfflineModus() {
		spelerRepo.startOfflineModus();
	}
	
    /**
     * Geeft het leaderboard van gespeelde spellen.
     *
     * @return lijst van spelers gesorteerd op score
     */

	public List<SpelerDTO> geefLeaderboard() {
		return zetSpelersOmNaarSpelerDTOs(spelerRepo.geefLeaderboard());
	}
	
	/**
	 * Zet een Speler-object om naar een SpelerDTO.
	 * 
	 * @param s Het Speler-object dat omgezet moet worden.
	 * @return Een SpelerDTO met de gegevens van de speler.
	 */

	private SpelerDTO zetSpelerOmNaarDTO(Speler s) {
		return new SpelerDTO(s.getGebruikersnaam(), s.getGeboortejaar(), s.getAantalGespeeld(), s.getAantalGewonnen(),
				s.getKleur(), zetZetsteenNaarDTO(s.getZetstenen()), zetGebouwsteenNaarDTO(s.getGebouwstenen()),
				s.getPunten());
	}
	
    /**
     * Geeft de huidige speler van het spel.
     *
     * @return spelerDTO van de huidige speler
     */

	public SpelerDTO getHuidigeSpelerDTO() {
		return zetSpelerOmNaarDTO(spel.getHuidigeSpeler());
	}

    /**
     * Geeft het nummer van de huidige ronde.
     *
     * @return ronde-nummer
     */
	
	public int getRonde() {

		return spel.getRonde();
	}
	
    /**
     * Controleert of de ronde afgelopen is (alle zetstenen geplaatst).
     *
     * @return {@code true} indien ronde beëindigd is
     */

	public boolean isEindeVanDeRonde() {
		int hoeveelZijnNietGezet = spel.getGekozenSpelers().stream().mapToInt(
				e -> e.getZetstenen().stream().mapToInt(z -> z.getPositie()).filter(p -> p == 0).findAny().orElse(1))
				.reduce(0, (x, y) -> x + y);
		return hoeveelZijnNietGezet == spel.getGekozenSpelers().size();
	}

}

//TestCommit