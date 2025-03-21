package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Speler;

class SpelerTest 
{
	private Speler speler;
	private final int geboortejaar = 2025;
	
	@Test
	void maakSpeler_alleGegevensCorrect_maaktObject() 
	{
		speler = new Speler("avatars", 2003, 4,25);
		Assertions.assertEquals("avatars", speler.getGebruikersnaam());
		Assertions.assertEquals(2003, speler.getGeboortejaar());
		Assertions.assertEquals(4, speler.getAantalGewonnen());
		Assertions.assertEquals(25, speler.getAantalGespeeld());
	}
	
	@Test //Dit test ook aantal overwinningen/gespeelde spelletjes bij nieuwe spelers!
	void maakSpeler_correcteGebruikersnaamGeboortejaar_maaktObject() 
	{
		speler = new Speler("avatar", 2003);
		Assertions.assertEquals("avatar", speler.getGebruikersnaam());
		Assertions.assertEquals(2003, speler.getGeboortejaar());
		Assertions.assertEquals(0, speler.getAantalGewonnen());
		Assertions.assertEquals(0, speler.getAantalGespeeld());
	}
	
	//Gebruikersnamen testen
	
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"","  ","	","\t\t","\n","ik","nieuw"})
	void maakSpeler_OngeldigeGebruikersnaam_WerptException(String value) {
		assertThrows(IllegalArgumentException.class, ()-> new Speler(value,2003));
	}
	@ParameterizedTest
	@ValueSource(strings = {"Testje","ikGaGeenNaamVerzinnenMaarDitIsLangGenoeg","Spaties ertussen"})
	void maakSpeler_GeldigeGebruikersnaam_MaaktSpeler(String value) {
		speler = new Speler(value,2003);
		assertEquals(value,speler.getGebruikersnaam());
	}
	
	//Geboortejaar testen
	@ParameterizedTest
	@ValueSource(ints = {2020,2024,1924,1907,2026,2050})
	void maakSpeler_OngeldigeGeboortedatum_WerptException(int value) {
		assertThrows(IllegalArgumentException.class,()-> new Speler("Geldig",value));
	}
	@ParameterizedTest
	@ValueSource(ints = {2019,1925,1982})
	void maakSpeler_GeldigeGeboortedatum_MaaktSpeler(int value) {
		speler = new Speler("Geldig",value);
		assertEquals(value,speler.getGeboortejaar());
	}
}
