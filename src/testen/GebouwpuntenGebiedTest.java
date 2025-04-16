package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    @ValueSource(ints = {101, 305, 609})
    void plaatsGebouwsteenNeer_CorrectePositie_VoegtToe(int positie) {
        Gebouwsteen gebouwsteen = new Gebouwsteen(gebouwpuntenGebied);
        gebouwpuntenGebied.plaatsGebouwsteenNeer(gebouwsteen,positie);
        assertTrue(gebouwpuntenGebied.getGebouwstenen().contains(gebouwsteen));
    }
    
    @ParameterizedTest
    @ValueSource(ints = {-5, 7, 85}) 
    void plaatsGebouwsteenNeer_OnmogelijkePositie_WerptException(int positie) {
        Gebouwsteen gebouwsteen = new Gebouwsteen(gebouwpuntenGebied);
        assertThrows(IllegalArgumentException.class, () -> gebouwpuntenGebied.plaatsGebouwsteenNeer(gebouwsteen,positie));
    }
    
    @Test
    void plaatsGebouwsteenNeer_PositiereedsBezetVolgordeVeranderd_AssertsEquals() {
        Gebouwsteen gebouwsteen1 = new Gebouwsteen(gebouwpuntenGebied);
        gebouwsteen1.plaatsNeer(201);
        Gebouwsteen gebouwsteen2 = new Gebouwsteen(gebouwpuntenGebied);
        gebouwpuntenGebied.plaatsGebouwsteenNeer(gebouwsteen2,201);
        assertEquals(201, gebouwsteen1.getPositie());
        assertEquals(201, gebouwsteen2.getPositie());
        assertEquals(1, gebouwsteen1.getVolgorde());
        assertEquals(2, gebouwsteen2.getVolgorde());
    }

}
