package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Bonusfiche;

public class BonusficheTest {

	
	private final static int GELDIGE_WAARDE = 3;
	private final static int MAX_GELDIGE_WAARDE = 6;
	private final static int MIN_GELDIGE_WAARDE = 1;
	
	
    @ParameterizedTest
    @ValueSource(ints = {-5, 0 , 7 , 10}) 
    public void bonusfiche_OngeldigePosities_GooitException(int positie) {
        assertThrows(IllegalArgumentException.class, () -> new Bonusfiche(positie, 2));
    }
    
    @ParameterizedTest
    @ValueSource(ints = {GELDIGE_WAARDE, MIN_GELDIGE_WAARDE, MAX_GELDIGE_WAARDE }) 
    public void bonusfiche_GeldigePosities_GooitException(int positie) {
        Bonusfiche bonusfiche = new Bonusfiche(positie, 2);
        assertEquals(positie, bonusfiche.getPositie());
    }	
	
    // TEST VOOR ZETSTEEN 
    
//	private final static int GELDIGE_WAARDE = 452;
//	private final static int MAX_GELDIGE_WAARDE = 683;
//	private final static int MIN_GELDIGE_WAARDE = 111;
//	
//	
//    @ParameterizedTest
//    @ValueSource(ints = {783, 692, 684, 602, 680, 83, 000}) // Inclusief 083 
//    public void bonusfiche_OngeldigePosities_GooitException(int positie) {
//        assertThrows(IllegalArgumentException.class, () -> new Bonusfiche(positie, 2));
//    }
//    
//    @ParameterizedTest
//    @ValueSource(ints = {152, 652, 412, 482, 451, 453, GELDIGE_WAARDE, MIN_GELDIGE_WAARDE, MAX_GELDIGE_WAARDE }) // Inclusief 083 
//    public void bonusfiche_GeldigePosities_GooitException(int positie) {
//        Bonusfiche bonusfiche = new Bonusfiche(positie, 2);
//        assertEquals(positie, bonusfiche.getPositie());
//    }
}
