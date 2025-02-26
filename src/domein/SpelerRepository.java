package domein;

import java.util.List;

import exceptions.GebruikersnaamInGebruikException;
import persistentie.SpelerMapper;

public class SpelerRepository {

    private final SpelerMapper mapper;
    
    //Dit is voor de DTO werkende te krijgen
    private List<Speler> spelers;

    public SpelerRepository() 
    {
        mapper = new SpelerMapper();
        spelers=mapper.geefAlleSpelers();
    }
    
    public void voegToe(Speler speler) {
       if (bestaatSpeler(speler.getGebruikersnaam()))
            throw new GebruikersnaamInGebruikException();
       
//       mapper.voegToe(speler); 	//Dit wordt pas gebruikt als de database ingesteld is. Nu wordt een DTO gebruikt
       
    }

    private boolean bestaatSpeler(String gebruikersnaam){
        return mapper.geefSpeler(gebruikersnaam)!=null;
    }  
    
    //Dit is voor de DTO
    public List<Speler> getSpelers(){
    	return spelers;
    }
}

