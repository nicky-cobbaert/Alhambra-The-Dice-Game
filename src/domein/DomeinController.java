package domein;

import java.util.ArrayList;
import java.util.List;

import dto.GekozenSpelersDTO;
import dto.SpelerDTO;
import utils.Kleuren;

public class DomeinController {

    private final SpelerRepository spelerRepo;
    private Speler huidigeSpeler; //stond op DCD maar wordt nog niets mee gedaan
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
    
    public List<Speler> geefAlleSpelers() { 
    	return spelerRepo.geefAlleSpelers();
    }
    
    public void kiesSpelerEnKleur(int s,String kleur) {
    	spel.kiesSpeler(s, kleur);
    }
    
    public List<SpelerDTO> geefBeschikbareSpelers() { //geen return type in DCD?
    	
    	   List<Speler> speler = spel.geefBeschikbareSpelers();
    	   List<SpelerDTO> resultaat = new ArrayList<>();
    	    
//    	    for (int i = 0; i < spelers.size(); i++) {
//    	        Speler speler = spelers.get(i);
//    	        SpelerDTO sp = new SpelerDTO(
//    	            speler.getGebruikersnaam(),
//    	            speler.getGeboortejaar(),
//    	            speler.getAantalGespeeld(),
//    	            speler.getAantalGewonnen(),
//    	            speler.getKleur()
//    	        );
//    	        
//    	        resultaat.add(sp);
//    	    }
    	   for (Speler s : speler) {
    		   SpelerDTO sp = new SpelerDTO(
       	            s.getGebruikersnaam(),
       	            s.getGeboortejaar(),
       	            s.getAantalGespeeld(),
       	            s.getAantalGewonnen()       	          
   	        );
    		   resultaat.add(sp);
    	   }	   
    	    
    	    return resultaat;
	
    }

    
    public List<GekozenSpelersDTO> geefGekozenSpelers(){
    	List<Speler> speler = spel.getGekozenSpelers();
    	List<GekozenSpelersDTO> resultaat = new ArrayList<>();
    	
    	for (Speler s : speler) {
 		   GekozenSpelersDTO sp = new GekozenSpelersDTO(
    	            s.getGebruikersnaam(),
    	            s.getGeboortejaar(),
    	            s.getAantalGespeeld(),
    	            s.getAantalGewonnen(),
    	            s.getKleur()
	        );
 		   resultaat.add(sp);
 	   }	   
 	    
 	    return resultaat;
    }
 

    public List<String> geefBeschikbareKleuren(){
    	
    	List<String> lijst = new ArrayList<String>();
    	
    	/**----kleur wordt een string--------*/
    	
    	for (Kleuren kleur : spel.getBeschikbareKleuren()) {
    		lijst.add(kleur.toString().toLowerCase());
    	}
    	
    	return lijst;
    }
    public String geefStartspeler() {
    	return spel.getStartSpeler();
    }
}
