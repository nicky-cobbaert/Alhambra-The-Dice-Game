package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.GebouwpuntenGebied;
import domein.Gebouwsteen;

public class GebouwsteenTest {

	private GebouwpuntenGebied gebouwpuntenGebied;
	private  static final int CORRECTE_POSITIE = 405;
	
	@BeforeEach
	void setUp() {
		gebouwpuntenGebied = new GebouwpuntenGebied();
	}
	
	@ParameterizedTest
	@ValueSource(ints = {100,101,109,302,CORRECTE_POSITIE,601,609,600})
	void correctePositie_PlaatsNeer_AssertsEquals(int positie) {
		Gebouwsteen g = new Gebouwsteen(gebouwpuntenGebied);
		g.plaatsNeer(positie);
		assertEquals(positie, g.getPositie());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {0,-1,-103,-606,99,110,599,610,701})
	void verkeerdePositie_PlaatsNeer_AssertsThrows(int positie) {
		Gebouwsteen g = new Gebouwsteen(gebouwpuntenGebied);
		assertThrows(IllegalArgumentException.class, () -> g.plaatsNeer(positie));
	}
	
	@Test
	void volgorde_Wordt_CorrectAangepast_MetMeerdereOpZelfdePlaats_AssertsEquals() {
		Gebouwsteen g1 = new Gebouwsteen(gebouwpuntenGebied);
		g1.plaatsNeer(CORRECTE_POSITIE);
		Gebouwsteen g2 = new Gebouwsteen(gebouwpuntenGebied);
		g2.plaatsNeer(CORRECTE_POSITIE);
		assertEquals(1, g1.getVolgorde());
		assertEquals(2, g2.getVolgorde());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1,2,3})
	void verplaatsen_CorrecteVerplaatsingPositieWordtCorrectAangepast_AssertEquals(int verplaatsing) {
		Gebouwsteen g = new Gebouwsteen(gebouwpuntenGebied);
		g.plaatsNeer(CORRECTE_POSITIE);
		g.verplaats(verplaatsing);
		assertEquals(CORRECTE_POSITIE + verplaatsing,g.getPositie());
	}
	@ParameterizedTest
	@ValueSource(ints = {-3,0,4,7})
	void verplaatsen_VerkeerdeVerplaatsing_AssertThrows(int verplaatsing) {
		Gebouwsteen g = new Gebouwsteen(gebouwpuntenGebied);
		g.plaatsNeer(CORRECTE_POSITIE);
		assertThrows(IllegalArgumentException.class, () -> g.verplaats(verplaatsing));
	}
	@Test
	void volgorde_Wordt_CorrectAangepast_AlsErWordtVerplaats_AssertsEquals() {
		Gebouwsteen g1 = new Gebouwsteen(gebouwpuntenGebied);
		Gebouwsteen g2 = new Gebouwsteen(gebouwpuntenGebied);
		Gebouwsteen g3 = new Gebouwsteen(gebouwpuntenGebied);
		Gebouwsteen g4 = new Gebouwsteen(gebouwpuntenGebied);
		
		g1.plaatsNeer(CORRECTE_POSITIE);
		g2.plaatsNeer(CORRECTE_POSITIE);
		g3.plaatsNeer(CORRECTE_POSITIE);
		g4.plaatsNeer(CORRECTE_POSITIE);
		
		g2.verplaats(1);
		g3.verplaats(1);
		
		assertEquals(1, g1.getVolgorde());
		assertEquals(1, g2.getVolgorde());
		assertEquals(2, g3.getVolgorde());
		assertEquals(2, g4.getVolgorde());
		}
	
}
