package domein;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.DobbelsteenDTO;
import dto.FicheDTO;
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
	
	public void beïndigBeurt(DobbelsteenKleur kleur) {
		spel.beïndigBeurt(kleur);
	}
	
	public void beïndigRonde() {
		spel.beïndigRonde();
	}
	
	public void beïndigSpel() {
		spel.beïndigSpel();
	}

}

//TestCommit