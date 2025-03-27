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
    
    @BeforeEach
    void setUp() {
        resultatenGebied = new ResultatenGebied();
    }
    
    @ParameterizedTest
    @ValueSource(ints = {211, 312, 123})
    void plaatsZetsteenNeer_CorrectePositie_VoegtToe(int positie) {
        Zetsteen zetsteen = new Zetsteen();
        zetsteen.plaatsNeer(positie);
        resultatenGebied.plaatsZetsteenNeer(zetsteen);
        assertTrue(resultatenGebied.getZetstenen().contains(zetsteen));
    }
    
    @ParameterizedTest
    @ValueSource(ints = {999, 700, 85}) 
    void plaatsZetsteenNeer_OnmogelijkePositie_WerptException(int positie) {
        Zetsteen zetsteen = new Zetsteen();
        zetsteen.plaatsNeer(positie);
        assertThrows(IllegalArgumentException.class, () -> resultatenGebied.plaatsZetsteenNeer(zetsteen));
    }
    
    @Test
    void plaatsZetsteenNeer_PositiereedsBezet_WerptException() {
        Zetsteen zetsteen1 = new Zetsteen();
        zetsteen1.plaatsNeer(201);
        Zetsteen zetsteen2 = new Zetsteen();
        zetsteen2.plaatsNeer(201);
        resultatenGebied.plaatsZetsteenNeer(zetsteen1);
        assertThrows(IllegalArgumentException.class, () -> resultatenGebied.plaatsZetsteenNeer(zetsteen2));
    }
}