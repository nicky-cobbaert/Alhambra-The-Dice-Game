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
    
    public void startSpel() {
    	spel.startSpel();
    }
    
    public List<Speler> geefAlleSpelers() { 
    	return spelerRepo.geefAlleSpelers();
    }
    
    public void kiesSpelerEnKleur() {
    	// dit snappen we niet?
    }
    
    public SpelerDTO[] zetGekozenSpelersOmNaarDTO(List<Speler> spelers) { //geen return type in DCD?
    	
    	   SpelerDTO[] resultaat = new SpelerDTO[spelers.size()];
    	    
    	    for (int i = 0; i < spelers.size(); i++) {
    	        Speler speler = spelers.get(i);
    	        resultaat[i] = new SpelerDTO(
    	            speler.getGebruikersnaam(),
    	            speler.getGeboortejaar(),
    	            speler.getAantalGespeeld(),
    	            speler.getAantalGewonnen()
    	        );
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
    
}
