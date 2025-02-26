package domein;

import java.util.List;

public class DomeinController {

    private final SpelerRepository spelerRepo;
    private Speler huidigeSpeler; //stond op DCD maar wordt nog niets mee gedaan

    public DomeinController() {
        spelerRepo = new SpelerRepository();
    }

    public void registreerSpeler(String gebruikersnaam, int geboortejaar) 
    {
        Speler nieuweSpeler = new Speler(gebruikersnaam, geboortejaar);
        spelerRepo.voegToe(nieuweSpeler);
    }
}
