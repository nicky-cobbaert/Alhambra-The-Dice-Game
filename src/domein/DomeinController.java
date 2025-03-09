package domein;

import java.util.ArrayList;
import java.util.List;

import dto.SpelerDTO;

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
    }
    
    public void startSpel() {
    	spel.startSpel();
    }
    
    public List<Speler> geefAlleSpelers() { 
    	return spelerRepo.geefAlleSpelers();
    }
    
    public void kiesSpelerEnKleur(Speler s,String kleur) {
    	spel.kiesSpeler(s, kleur);
    }
    
    public List<SpelerDTO> zetGekozenSpelersOmNaarDTO(List<Speler> spelers) { //geen return type in DCD?
    	
    	   List<SpelerDTO> resultaat = new ArrayList<>();
    	    
    	    for (int i = 0; i < spelers.size(); i++) {
    	        Speler speler = spelers.get(i);
    	        SpelerDTO sp = new SpelerDTO(
    	            speler.getGebruikersnaam(),
    	            speler.getGeboortejaar(),
    	            speler.getAantalGespeeld(),
    	            speler.getAantalGewonnen()
    	        );
    	        
    	        resultaat.add(sp);
    	    }
    	    
    	    return resultaat;
	
    }
    
    public List<SpelerDTO> geefGekozenSpelers() {
        List<SpelerDTO> resultaat = new ArrayList<>();

        for (Speler s : spelerRepo.geefAlleSpelers()) {
            resultaat.add(new SpelerDTO(
                s.getGebruikersnaam(),
                s.getGeboortejaar(),
                s.getAantalGespeeld(),
                s.getAantalGewonnen()
            ));
        }

        return resultaat;
    }
    //Deze hebben volgens mij geen nut, staan ook niet in DCD!
//    public List<Speler> geefBeschikbareSpelers(){
//    	return spel.geefBeschikbareSpelers(spelerRepo.geefAlleSpelers());
//    }
//    
    public List<String> geefBeschikbareKleuren(){
    	return spel.getBeschikbareKleuren();
    }
//    
}
