package testen;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.GebouwpuntenGebied;
import domein.Gebouwsteen;

public class GebouwpuntenGebiedTest {
	
private GebouwpuntenGebied gebouwpuntenGebied;
    
    @BeforeEach
    void setUp() {
    	gebouwpuntenGebied = new GebouwpuntenGebied();
    }
    
    @ParameterizedTest
    @ValueSource(ints = {101, 305, 612})
    void plaatsGebouwsteenNeer_CorrectePositie_VoegtToe(int positie) {
        Gebouwsteen gebouwsteen = new Gebouwsteen();
        gebouwsteen.setPositie(positie);
        gebouwpuntenGebied.plaatsGebouwsteenNeer(gebouwsteen);
        assertTrue(gebouwpuntenGebied.getGebouwstenen().contains(gebouwsteen));
    }
    
    @ParameterizedTest
    @ValueSource(ints = {-5, 0, 7, 85}) 
    void plaatsGebouwsteenNeer_OnmogelijkePositie_WerptException(int positie) {
        Gebouwsteen gebouwsteen = new Gebouwsteen();
        gebouwsteen.setPositie(positie);
        assertThrows(IllegalArgumentException.class, () -> gebouwpuntenGebied.plaatsGebouwsteenNeer(gebouwsteen));
    }
    
    @Test
    void plaatsGebouwsteenNeer_PositiereedsBezet_WerptException() {
        Gebouwsteen gebouwsteen1 = new Gebouwsteen();
        gebouwsteen1.setPositie(201);
        Gebouwsteen gebouwsteen2 = new Gebouwsteen();
        gebouwsteen2.setPositie(201);
        gebouwpuntenGebied.plaatsGebouwsteenNeer(gebouwsteen1);
        assertThrows(IllegalArgumentException.class, () -> gebouwpuntenGebied.plaatsGebouwsteenNeer(gebouwsteen2));
    }

}
