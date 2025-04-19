package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.ResultatenGebied;
import domein.Zetsteen;

class ResultatenGebiedTest {
    
    private ResultatenGebied resultatenGebied;
    private int CORRECTE_POSITIE = 231;
    private Zetsteen z1;
    private Zetsteen z2;
    private Zetsteen z3;
    private Zetsteen z4;
    
    
    @BeforeEach
    void setUp() {
        resultatenGebied = new ResultatenGebied();
        z1 = new Zetsteen(resultatenGebied);
        z2 = new Zetsteen(resultatenGebied);
        z3 = new Zetsteen(resultatenGebied);
        z4 = new Zetsteen(resultatenGebied);
    }
    
    @ParameterizedTest
    @ValueSource(ints = {211, 312, 123})
    void plaatsZetsteenNeer_CorrectePositie_VoegtToe(int positie) {
        resultatenGebied.plaatsZetsteenNeer(z1,positie);
        assertTrue(resultatenGebied.getZetstenen().contains(z1));
    }
    
    @ParameterizedTest
    @ValueSource(ints = {999, 700, 85}) 
    void plaatsZetsteenNeer_OnmogelijkePositie_WerptException(int positie) {
        assertThrows(IllegalArgumentException.class, () -> resultatenGebied.plaatsZetsteenNeer(z1,positie));
    }
    
    @Test
    void plaatsZetsteenNeer_PositiereedsBezet_WerptException() {
        resultatenGebied.plaatsZetsteenNeer(z1,CORRECTE_POSITIE);
        assertThrows(IllegalArgumentException.class, () -> resultatenGebied.plaatsZetsteenNeer(z2,CORRECTE_POSITIE));
    }
    
    @Test
    void getVooresteZetstenen_GeeftCorrecte() {
    	z1.plaatsNeer(252);
    	z2.plaatsNeer(211);
    	z3.plaatsNeer(222);
    	z4.plaatsNeer(232);
    	List<Zetsteen> vooresteZetstenen = resultatenGebied.getVoorsteZetstenen(2);
    	assertTrue(vooresteZetstenen.contains(z1) && vooresteZetstenen.contains(z4));
    	assertEquals(z1, vooresteZetstenen.get(0));
    	assertEquals(z4, vooresteZetstenen.get(1));
    }
}