package testen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;

import domein.ResultatenGebied;
import domein.Zetsteen;

class ResultatenGebiedTest {
    
    private ResultatenGebied resultatenGebied;
    private int CORRECTE_POSITIE = 231;
    
    @BeforeEach
    void setUp() {
        resultatenGebied = new ResultatenGebied();
    }
    
    @ParameterizedTest
    @ValueSource(ints = {211, 312, 123})
    void plaatsZetsteenNeer_CorrectePositie_VoegtToe(int positie) {
        Zetsteen zetsteen = new Zetsteen(resultatenGebied);
        resultatenGebied.plaatsZetsteenNeer(zetsteen,positie);
        assertTrue(resultatenGebied.getZetstenen().contains(zetsteen));
    }
    
    @ParameterizedTest
    @ValueSource(ints = {999, 700, 85}) 
    void plaatsZetsteenNeer_OnmogelijkePositie_WerptException(int positie) {
        Zetsteen zetsteen = new Zetsteen(resultatenGebied);
        assertThrows(IllegalArgumentException.class, () -> resultatenGebied.plaatsZetsteenNeer(zetsteen,positie));
    }
    
    @Test
    void plaatsZetsteenNeer_PositiereedsBezet_WerptException() {
        Zetsteen zetsteen1 = new Zetsteen(resultatenGebied);
        Zetsteen zetsteen2 = new Zetsteen(resultatenGebied);
        resultatenGebied.plaatsZetsteenNeer(zetsteen1,CORRECTE_POSITIE);
        assertThrows(IllegalArgumentException.class, () -> resultatenGebied.plaatsZetsteenNeer(zetsteen2,CORRECTE_POSITIE));
    }
}