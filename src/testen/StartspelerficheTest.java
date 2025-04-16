package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.FicheGebied;
import domein.StartspelerFiche;

public class StartspelerficheTest {
//TODO
	private final static int GELDIGE_WAARDE = 3;
	private final static int MAX_GELDIGE_WAARDE = 6;
	private final static int MIN_GELDIGE_WAARDE = 1;
	private FicheGebied ficheGebied;
	
	@BeforeEach
	void setUp() {
		ficheGebied = new FicheGebied();
	}
	
	   @ParameterizedTest
	    @ValueSource(ints = {-5 , 7 , 10}) 
	    public void startspelerfiche_OngeldigePosities_GooitException(int positie) {
	        StartspelerFiche startspelerfiche = new StartspelerFiche(ficheGebied);
	        assertThrows(IllegalArgumentException.class, () -> startspelerfiche.plaatsNeer(positie));
	    }
	   
	   
	   @ParameterizedTest
	    @ValueSource(ints = {GELDIGE_WAARDE, MIN_GELDIGE_WAARDE, MAX_GELDIGE_WAARDE}) 
	    public void startspelerfiche_OngeldigePosities_PlaatstStartspelerfiche(int positie) {
		   StartspelerFiche startspelerfiche = new StartspelerFiche(ficheGebied);
	        startspelerfiche.plaatsNeer(positie);
	        assertEquals(positie, startspelerfiche.getPositie());
	   }
	   
	   
	   
	   
	
	
	
}

