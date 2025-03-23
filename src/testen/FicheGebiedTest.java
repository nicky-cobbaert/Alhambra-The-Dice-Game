package testen;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Fiche;
import domein.FicheGebied;

public class FicheGebiedTest {
	
private FicheGebied ficheGebied;
    
    @BeforeEach
    void setUp() {
        ficheGebied = new FicheGebied();
    }
    
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 6})
    void plaatsFicheNeer_CorrectePositie_VoegtToe(int positie) {
        Fiche fiche = new Fiche();
        fiche.setPositie(positie);
        ficheGebied.plaatsFicheNeer(fiche);
        assertTrue(ficheGebied.getGezettefiches().contains(fiche));
    }
    
    @ParameterizedTest
    @ValueSource(ints = {-5, 0, 7, 85}) 
    void plaatsFicheNeer_OnmogelijkePositie_WerptException(int positie) {
        Fiche fiche = new Fiche();
        fiche.setPositie(positie);
        assertThrows(IllegalArgumentException.class, () -> ficheGebied.plaatsFicheNeer(fiche));
    }
    
    @Test
    void plaatsFicheNeer_PositiereedsBezet_WerptException() {
        Fiche fiche1 = new Fiche();
        fiche1.setPositie(201);
        Fiche fiche2 = new Fiche();
        fiche2.setPositie(201);
        ficheGebied.plaatsFicheNeer(fiche1);
        assertThrows(IllegalArgumentException.class, () -> ficheGebied.plaatsFicheNeer(fiche2));
    }

}
