package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.GebouwpuntenGebied;
import domein.Gebouwsteen;

public class GebouwpuntenGebiedTest {
	
private GebouwpuntenGebied gebouwpuntenGebied;
private Gebouwsteen g1;
private Gebouwsteen g2;
private Gebouwsteen g3;
    
    @BeforeEach
    void setUp() {
    	gebouwpuntenGebied = new GebouwpuntenGebied();
    	g1 = new Gebouwsteen(gebouwpuntenGebied);
    	g2 = new Gebouwsteen(gebouwpuntenGebied);
    	g3 = new Gebouwsteen(gebouwpuntenGebied);
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
        g1.plaatsNeer(201);
        
        gebouwpuntenGebied.plaatsGebouwsteenNeer(g2,201);
        assertEquals(201, g1.getPositie());
        assertEquals(201, g2.getPositie());
        assertEquals(1, g1.getVolgorde());
        assertEquals(2, g2.getVolgorde());
    }
    
    @Test
    void getVoorsteGebouwsteen_GeeftDeJuiste_Ronde3() {
    	g1.plaatsNeer(209);
    	g2.plaatsNeer(206);
    	g3.plaatsNeer(203);
    	List<Gebouwsteen> voorstegeb= gebouwpuntenGebied.getVoorsteGebouwstenen(3, 2);
    	assertTrue(voorstegeb.contains(g1)&&voorstegeb.contains(g2)&&voorstegeb.contains(g3));
    }
    
    @Test
    void getVoorsteGebouwsteen_GeeftDeJuiste_Ronde2() {
    	g1.plaatsNeer(209);
    	g2.plaatsNeer(206);
    	g3.plaatsNeer(203);
    	List<Gebouwsteen> voorstegeb= gebouwpuntenGebied.getVoorsteGebouwstenen(2, 2);
    	assertTrue(voorstegeb.contains(g1)&&voorstegeb.contains(g2));
    }
    
    @Test
    void getVoorsteGebouwsteen_GeeftDeJuisteRonde1() {
    	g1.plaatsNeer(209);
    	g2.plaatsNeer(206);
    	g3.plaatsNeer(203);
    	List<Gebouwsteen> voorstegeb= gebouwpuntenGebied.getVoorsteGebouwstenen(1, 2);
    	assertTrue(voorstegeb.contains(g1));
    }
    
    @Test
    void getVoorsteGebouwsteen_GeeftDeJuisteVolgorde_Ronde3(){
    	g1.plaatsNeer(209);
    	g2.plaatsNeer(206);
    	g3.plaatsNeer(203);
    	List<Gebouwsteen> voorstegeb= gebouwpuntenGebied.getVoorsteGebouwstenen(3, 2);
    	assertEquals(g1,voorstegeb.get(0));
    	assertEquals(g2,voorstegeb.get(1));
    	assertEquals(g3,voorstegeb.get(2));
    }
    
    @ParameterizedTest
    @ValueSource(ints = {3,4,6} )
    void getVoorsteGebouwtseen_GeeftGeenGebouwstenenNietGeplaatst(int positieKleur) {
    	g1.plaatsNeer(103);
    	g2.plaatsNeer(504);
    	g3.plaatsNeer(209);
    	List<Gebouwsteen> voorstegeb = gebouwpuntenGebied.getVoorsteGebouwstenen(1, positieKleur);
    	assertEquals(0, voorstegeb.size());
    }
    
    @ParameterizedTest
    @ValueSource(ints = {1,3,6})
    void getVoorsteGebouwsteen_GeeftGeenGebouwstenenPositie0(int positieKleur) {
    	g1.plaatsNeer(positieKleur*100);
    	g2.plaatsNeer(positieKleur*100);
    	g3.plaatsNeer(positieKleur*100);
    	List<Gebouwsteen> voorstegeb = gebouwpuntenGebied.getVoorsteGebouwstenen(3, positieKleur);
    	assertEquals(0, voorstegeb.size());
    }
    
    @Test
    void getVoorsteGebouwsteen_GeeftCorrecteMetMeerDan3GebouwstenenGeplaatst() {
    	Gebouwsteen g4 = new Gebouwsteen(gebouwpuntenGebied);
    	Gebouwsteen g5 = new Gebouwsteen(gebouwpuntenGebied);
    	Gebouwsteen g6 = new Gebouwsteen(gebouwpuntenGebied);
    	Gebouwsteen g7 = new Gebouwsteen(gebouwpuntenGebied);
    	Gebouwsteen g8 = new Gebouwsteen(gebouwpuntenGebied);
    	g1.plaatsNeer(209);//1
    	g5.plaatsNeer(201);
    	g6.plaatsNeer(202);
    	g4.plaatsNeer(206);
    	g2.plaatsNeer(209);//2 
    	g3.plaatsNeer(208);//3
    	g7.plaatsNeer(202);
    	g8.plaatsNeer(207);
    	List<Gebouwsteen> voorstegeb = gebouwpuntenGebied.getVoorsteGebouwstenen(3, 2);
    	assertEquals(3, voorstegeb.size());
    	assertEquals(g1, voorstegeb.get(0));
    	assertEquals(g2, voorstegeb.get(1));
    	assertEquals(g3, voorstegeb.get(2));
    	
    }
    
    @Test
    void getVoorsteGebouwsteen_GeeftCorrecteRonde3Niet3InLijst() {
    	g1.plaatsNeer(200);
    	g2.plaatsNeer(206);
    	g3.plaatsNeer(203);
    	List<Gebouwsteen> voorstegeb = gebouwpuntenGebied.getVoorsteGebouwstenen(3, 2);
    	assertEquals(2, voorstegeb.size());
    	assertEquals(g2, voorstegeb.get(0));
    	assertEquals(g3, voorstegeb.get(1));
    }
}