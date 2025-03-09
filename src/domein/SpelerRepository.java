package domein;

import java.util.ArrayList;
import java.util.List;

import exceptions.GebruikersnaamInGebruikException;
import persistentie.SpelerMapper;

public class SpelerRepository {
	
	private final List<Speler> spelers;

    private final SpelerMapper mapper;
    
    //Deze lijst is voor het nakijken of de gebruikersnaam al bestaat. Als de database er is kan dit allemaal weg
//    private List<Speler> spelers;

    public SpelerRepository() 
    {
        mapper = new SpelerMapper();
        spelers = new ArrayList<Speler>();
        //Dit is voor de 'DTO'
//        spelers=mapper.geefAlleSpelers();
    }
    
    public void voegToe(Speler speler) {
       if (bestaatSpeler(speler.getGebruikersnaam())) {
    	    throw new GebruikersnaamInGebruikException();
       }
                 
       mapper.voegToe(speler); 	//Dit wordt pas gebruikt als de database ingesteld is. Nu wordt een DTO gebruikt
       
       // Dit is voor de 'DTO'
//       spelers.add(speler);
    }

    private boolean bestaatSpeler(String gebruikersnaam){
        return mapper.geefSpeler(gebruikersnaam)!=null;
    	
    	//Dit is voor de 'DTO' 	
//    	for (Speler s : spelers) {
//     	   if (gebruikersnaam.contains(s.getGebruikersnaam())) {
//     		   return true;
//     	   }
//        }
//    	return false;
    }  
    
    public List<Speler> geefAlleSpelers() { 
    	
    	List<Speler> alleSpelers = mapper.geefAlleSpelers();
 
//    	for (Speler s : mapper.geefAlleSpelers()) { // spelers vervangen door this.spelers
//    		alleSpelers.add(s);
//    	}
    	
    	return alleSpelers;
    }
    


   
}

