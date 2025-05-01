package domein;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.DobbelsteenDTO;
import dto.FicheDTO;
import dto.GebouwsteenDTO;
import dto.SpelerDTO;
import dto.ZetsteenDTO;
import utils.DobbelsteenKleur;
import utils.SpelerKleur;

public class DomeinController {

	private final SpelerRepository spelerRepo;
	private Spel spel;
	private boolean isGUI; // Nodig voor vertaling, bepaald of je in cli zit (geen vertaling) of gui (wel vertaling)
    
    public DomeinController() {
        spelerRepo = new SpelerRepository();
        isGUI = false; // Default is cli
    }
    
    public void setGUIMode(boolean isGUI) {
        this.isGUI = isGUI;
    }
    
    // Zorgt ervoor dat berichten in cli mooi worden weergegeven
    private String translateErrorKey(String key) {
        if (!isGUI) {
            // Illegal argument exception word mooit gezet
            Map<String, String> errorMessages = new HashMap<>();
            errorMessages.put("gebruikersnaam.teKort", "Gebruikersnaam moet minimaal 6 karakters bevatten");
            errorMessages.put("geboortedatum.ongeldig", "Je moet tussen de leeftijd van 6 en 100 zijn om dit spel te mogen spelen");
            // Add other translations as needed
            
            return errorMessages.getOrDefault(key, key); // als er niks gevonden is default
        }
        return key; // In GUI mode wordt de sluitel naar resourcebundel gestuurd
    }
    
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

	public void maakNieuwSpel() {
		spel = new Spel(spelerRepo.geefAlleSpelers());
	}

	public void startSpel() {
		spel.startSpel();
	}

	public void startRonde() {
		spel.startRonde();
	}
//    public List<Speler> geefAlleSpelers() { 
//    	return spelerRepo.geefAlleSpelers();
//    }
//    
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

	public List<SpelerDTO> geefBeschikbareSpelers() {

		List<Speler> speler = spel.getBeschikbareSpelers();
		return zetSpelersOmNaarSpelerDTOs(speler);

	}

	private List<SpelerDTO> zetSpelersOmNaarSpelerDTOs(List<Speler> spelers) {
		List<SpelerDTO> resultaat = new ArrayList<>();

		for (Speler s : spelers) {
			SpelerDTO sp = zetSpelerOmNaarDTO(s);
			resultaat.add(sp);
		}

		return resultaat;
	}
	
	private List<ZetsteenDTO> zetZetsteenNaarDTO(List<Zetsteen> zetstenen){
		List<ZetsteenDTO> zetsteenDTOs = new ArrayList<ZetsteenDTO>();
		if (zetstenen == null) {
			return null;
		}
		for(Zetsteen z:zetstenen) {
			zetsteenDTOs.add(new ZetsteenDTO(z.getPositie()));
		}
		return zetsteenDTOs;
	}
	private List<GebouwsteenDTO> zetGebouwsteenNaarDTO(List<Gebouwsteen> gebouwstenen){
		List<GebouwsteenDTO> gebouwsteenDTOs = new ArrayList<GebouwsteenDTO>();
		if (gebouwstenen == null) {
			return null;
		}
		for(Gebouwsteen g:gebouwstenen) {
			gebouwsteenDTOs.add(new GebouwsteenDTO(g.getPositie(),g.getVolgorde()));
		}
		return gebouwsteenDTOs;
	}

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

	public List<SpelerDTO> geefGekozenSpelers() {
		List<Speler> speler = spel.getGekozenSpelers();
		return zetSpelersOmNaarSpelerDTOs(speler);
	}

	public List<SpelerKleur> geefBeschikbareSpelerKleuren() {

		return spel.getBeschikbareKleuren();
	}

	public SpelerDTO geefStartspeler() {
		return zetSpelerOmNaarDTO(spel.getStartSpeler());
	}

	public int geefAantalZetstenen() {
		return spel.getAantalZetstenen();
	}

	public void updateGewonnen() {
		for (Speler s : spel.getWinnaar()) {
			spelerRepo.updateGewonnen(s.getGebruikersnaam());
		}

	}

	public void updateGespeeld() {
		for (Speler s : spel.getGekozenSpelers()) {
			spelerRepo.updateGespeeld(s.getGebruikersnaam());
		}
	}

	public void speelRonde() {
		spel.startRonde();
	}

	public List<SpelerDTO> geefWinnaars() {
		List<Speler> speler = spel.getWinnaar();
		return zetSpelersOmNaarSpelerDTOs(speler);
	}

	public boolean getIsEindeSpel() {
		return spel.getIsEindeSpel();
	}

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
	
	public List<DobbelsteenDTO> getDobbelstenenDTOs(){
		List<DobbelsteenDTO> dobbelsteenDTOs = new ArrayList<DobbelsteenDTO>();
		for(Dobbelsteen d:spel.getDobbelstenen()) {
			DobbelsteenDTO dobbelDTO = new DobbelsteenDTO(d.getDobbelsteenKleur(), d.getNogRollen());
			dobbelsteenDTOs.add(dobbelDTO);
		}
		return dobbelsteenDTOs;
	}
	
	public boolean veranderStatusNogRollenDobbelsteen(int index) {
		return spel.veranderStatusNogRollenDobbelsteen(index);
	}
	
	public int getAantalKeerGerold() {
		return spel.getAantalKeerGerold();
	}
	
	public boolean rolDobbelstenen() {
		return spel.rolDobbelstenen();
	}
	
	public List<FicheDTO> getGeplaatsteFicheDTOs(){
		List<FicheDTO> ficheDTOs = new ArrayList<FicheDTO>(); 
		for(Fiche f:spel.getGezetteFiches()) {
			FicheDTO ficheDTO;
			if(f instanceof Bonusfiche bf) {
				ficheDTO = new FicheDTO(bf.getPositie(), bf.getWaarde());
			}else {
				ficheDTO = new FicheDTO(f.getPositie(), 0);
			}
			ficheDTOs.add(ficheDTO);
		}
		return ficheDTOs;
	}
	
	public SpelerDTO beïndigBeurt(DobbelsteenKleur kleur) {
		return zetSpelerOmNaarDTO(spel.beïndigBeurt(kleur));
	}
	
	public void beïndigRonde() {
		spel.beïndigRonde();
	}
	
	public void beïndigSpel() {
		spel.beïndigSpel();
	}

	public void startOfflineModus() {
		spelerRepo.startOfflineModus();
	}
	
	public List<SpelerDTO> geefLeaderboard(){
		return zetSpelersOmNaarSpelerDTOs(spelerRepo.geefLeaderboard());
	}
	
	private SpelerDTO zetSpelerOmNaarDTO(Speler s) {
		return new SpelerDTO(s.getGebruikersnaam(),s.getGeboortejaar(), s.getAantalGespeeld(),
				s.getAantalGewonnen(), s.getKleur(),zetZetsteenNaarDTO(s.getZetstenen()),
				zetGebouwsteenNaarDTO(s.getGebouwstenen()), s.getPunten());
	}
	
	public SpelerDTO getHuidigeSpelerDTO() {
		return zetSpelerOmNaarDTO(spel.getHuidigeSpeler());
	}

	public int getRonde() {
		
		return spel.getRonde();
	}
	
}

//TestCommit