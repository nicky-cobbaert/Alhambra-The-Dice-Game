package domein;

import java.util.ArrayList;
import java.util.List;

import dto.SpelerDTO;
import utils.SpelerKleur;

public class DomeinController {

    private final SpelerRepository spelerRepo;
    private Spel spel;

    public DomeinController() {
        spelerRepo = new SpelerRepository();
//        huidigeSpelers = new Speler();
    }

    public void registreerSpeler(String gebruikersnaam, int geboortejaar) 
    {
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
    public void kiesSpelerEnKleur(int s,SpelerKleur kleur) {
    	spel.kiesSpeler(s, kleur); //s is de plaats in de arrayList!
    }
    
    public List<SpelerDTO> geefBeschikbareSpelers() { 
    	
    	   List<Speler> speler = spel.geefBeschikbareSpelers();
    	   return zetSpelersOmNaarSpelerDTOs(speler);
	
    }
    private List<SpelerDTO> zetSpelersOmNaarSpelerDTOs(List<Speler> spelers){
    	List<SpelerDTO> resultaat = new ArrayList<>();

 	   for (Speler s : spelers) { 			//Aparte methode voor maken, dubbele code!
 		   SpelerDTO sp = new SpelerDTO(
    	            s.getGebruikersnaam(),
    	            s.getGeboortejaar(),
    	            s.getAantalGespeeld(),
    	            s.getAantalGewonnen(),
    	            null
	        );
 		   resultaat.add(sp);
 	   }	   
 	    
 	    return resultaat;
    }
    
    private static String kleurgever(SpelerKleur kleur) {

		switch (kleur.toString().toLowerCase()) {
		case "blauw":
			return "\u001B[34m";
		case "groen":
			return "\u001B[32m";
		case "wit":
			return "\u001B[37m";
		case "geel":
			return "\u001B[33m";
		case "oranje":
			return "\033[38;2;255;165;0m";
		case "rood":
			return "\u001B[31m";
		default:
			throw new IllegalArgumentException("Deze kleur is niet beschikbaar.");
		}
	}

    
    public List<SpelerDTO> geefGekozenSpelers(){
    	List<Speler> speler = spel.getGekozenSpelers();
    	return zetSpelersOmNaarSpelerDTOs(speler);
    }
 

    public List<SpelerKleur> geefBeschikbareSpelerKleuren(){
    	
    	return spel.getBeschikbareKleuren();
    }
    public String geefStartspeler() {
    	return spel.getStartSpeler();
    }
    public int geefAantalZetstenen() {
    	return spel.geefAantalZetstenen();
    }
}
