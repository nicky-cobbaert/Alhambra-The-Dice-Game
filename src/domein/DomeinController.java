package domein;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.SpelerDTO;
import utils.DobbelsteenKleur;
import utils.SpelerKleur;

public class DomeinController {

	private final SpelerRepository spelerRepo;
	private Spel spel;

	public DomeinController() {
		spelerRepo = new SpelerRepository();
//        huidigeSpelers = new Speler();
	}

	public void registreerSpeler(String gebruikersnaam, int geboortejaar) {
		Speler nieuweSpeler = new Speler(gebruikersnaam, geboortejaar);
		spelerRepo.voegToe(nieuweSpeler);
	}

	public void maakNieuwSpel() {
		spel = new Spel();
		spel.setBeschikbareSpelers(spelerRepo.geefAlleSpelers());
	}

	public void startSpel() {
		spel.startSpel();
	}

//    public List<Speler> geefAlleSpelers() { 
//    	return spelerRepo.geefAlleSpelers();
//    }
//    
	public void kiesSpelerEnKleur(int s, SpelerKleur kleur) {
		spel.kiesSpeler(s, kleur); // s is de plaats in de arrayList!
	}
	
	/*public void verwijderSpelerEnKleur(int s, SpelerKleur kleur) {
		spel.verwijderGekozenSpeler(s, kleur); // s is de plaats in de arrayList!
		
	
	}*/

	public List<SpelerDTO> geefBeschikbareSpelers() {

		List<Speler> speler = spel.geefBeschikbareSpelers();
		return zetSpelersOmNaarSpelerDTOs(speler);

	}

	private List<SpelerDTO> zetSpelersOmNaarSpelerDTOs(List<Speler> spelers) {
		List<SpelerDTO> resultaat = new ArrayList<>();

		for (Speler s : spelers) {
			SpelerDTO sp = new SpelerDTO(s.getGebruikersnaam(), s.getGeboortejaar(), s.getAantalGespeeld(),
					s.getAantalGewonnen(), s.getKleur());
			resultaat.add(sp);
		}

		return resultaat;
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

	public String geefStartspeler() {
		return spel.getStartSpeler();
	}

	public int geefAantalZetstenen() {
		return spel.geefAantalZetstenen();
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

	public DobbelsteenKleur rol(int i) {
		return spel.rol(i - 1);
	}

	public void wijzigPositieGebouwsteen() {

	}

	public void berekenBeloning() {

	}

	public void verwijderAssociatieGebouwenpuntenEnGebouwPuntenPositie() {

	}

	public void verwijderAssociatieSpelEnBonusFiches() {

	}
//    public List<SpelDTO> toonOverzichtSpel() {
//    	
//    }

	public Map<String, Object> zoekDezeSpeler(String onzeNaam) {

		List<String> overeenkomstigeNamen = new ArrayList<>();

		int meesteLetters = 0;

		for (SpelerDTO naamVanLijst : geefBeschikbareSpelers()) {

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
				meesteLetters = gelijkeLetters;
			} else if (gelijkeLetters > meesteLetters) {
				overeenkomstigeNamen.clear();
				overeenkomstigeNamen.add(naamVanLijst.gebruikersnaam());
				meesteLetters = gelijkeLetters;
			}
		} // einde for-lus voor alle spelers --------------------------------------

		Map<String, Object> resultaat = new HashMap<>();
        resultaat.put("overeenkomstigeNamen", overeenkomstigeNamen);
        resultaat.put("meesteLetters", meesteLetters);

		
		return resultaat;

	}

	public int geefWaardeVanPositie(int positie) {
		return spel.geefWaardeVanPositie(positie);
	}
	
}

//TestCommit