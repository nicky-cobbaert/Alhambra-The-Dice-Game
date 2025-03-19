package testen;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Spel;
import domein.SpelerRepository;
import persistentie.SpelerMapper;

class SpelTest {

	

	
	@Test
	void startSpel_AantalSpelersVeelTeLaag_WerptExceptie() {

		
		
		Spel spel = new Spel();


		assertThrows(IllegalArgumentException.class, () -> spel.startSpel());
	}

	// code geschreven met Enum Kleuren indien daar gebruik van zal gemaakt worden
	@Test
	void startSpel_AantalSpelersNetTeLaag_WerptExceptie() {

		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();
		SpelerRepository spelerRepo = new SpelerRepository();
		spel.setBeschikbareSpelers(spelerRepo.geefAlleSpelers());

		spel.kiesSpeler( 1 , "Blauw");
		spel.kiesSpeler(5, "WIT");

		assertThrows(IllegalArgumentException.class, () -> spel.startSpel());
	}

	@Test
	void startSpel_AantalSpelersNetGenoeg_StartSpel() {

		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();
		SpelerRepository spelerRepo = new SpelerRepository();
		spel.setBeschikbareSpelers(spelerRepo.geefAlleSpelers());
		
		spel.kiesSpeler(1, "BLAUW");
		spel.kiesSpeler(3, "WIT");
		spel.kiesSpeler(4, "GEEL");

		assertEquals(3, spel.getGekozenSpelers().size());
	}

	@Test
	void startSpel_AantalSpelersMeerDanGenoeg_StartSpel() {

		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();
		SpelerRepository spelerRepo = new SpelerRepository();
		spel.setBeschikbareSpelers(spelerRepo.geefAlleSpelers());

		spel.kiesSpeler(2, "BLAUW");
		spel.kiesSpeler(1, "WIT");
		spel.kiesSpeler(0, "GEEL");
		spel.kiesSpeler(3, "GROEN");

		assertEquals(4, spel.getGekozenSpelers().size());
	}

	@Test
	void startSpel_MaximumAantalSpelers_StartSpel() {

		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();
		SpelerRepository spelerRepo = new SpelerRepository();
		spel.setBeschikbareSpelers(spelerRepo.geefAlleSpelers());

		spel.kiesSpeler(0, "BLAUW");
		spel.kiesSpeler(1, "WIT");
		spel.kiesSpeler(2, "ROOD");
		spel.kiesSpeler(3, "GROEN");
		spel.kiesSpeler(4, "ORANJE");
		spel.kiesSpeler(5, "GEEL");

		assertEquals(6, spel.getGekozenSpelers().size());
	}

	@Test
	void startSpel_AantalSpelersNetTeVeel_WerptExceptie() {

		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();
		SpelerRepository spelerRepo = new SpelerRepository();
		spel.setBeschikbareSpelers(spelerRepo.geefAlleSpelers());

		spel.kiesSpeler(0, "BLAUW");
		spel.kiesSpeler(1, "WIT");
		spel.kiesSpeler(2, "ROOD");
		spel.kiesSpeler(3, "GROEN");
		spel.kiesSpeler(4, "ORANJE");
		spel.kiesSpeler(5, "GEEL");

		assertThrows(IllegalArgumentException.class, () -> spel.kiesSpeler(6, "GEEL"));
	}

	/** deze code loopt normaal al fout bij de 7de aanroep, misschien aanpassen */
	@Test
	void startSpel_AantalSpelersVeelTeVeel_WerptExceptie() {

		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();
		SpelerRepository spelerRepo = new SpelerRepository();
		spel.setBeschikbareSpelers(spelerRepo.geefAlleSpelers());

		spel.kiesSpeler(0, "BLAUW");
		spel.kiesSpeler(1, "WIT");
		spel.kiesSpeler(2, "ROOD");
		spel.kiesSpeler(3, "GROEN");
		spel.kiesSpeler(4, "ORANJE");
		spel.kiesSpeler(5, "GEEL");

		assertThrows(IllegalArgumentException.class, () -> {
			spel.kiesSpeler(6, "ROOD");
			spel.kiesSpeler(7, "GEEL");
		});

	}

	@ParameterizedTest
	@ValueSource(strings = { "blablabla", "Appelblauwzeegroen" })
	void startSpel_KleurOnbestaand_WerptExceptie(String kleur) {
		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();

		assertThrows(IllegalArgumentException.class, () -> {
			spel.kiesSpeler(0, kleur);
		});

	}

	@NullAndEmptySource
	@ParameterizedTest
	void startSpel_KleurNullOfLeeg_WerptExceptie(String kleur) {
		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();

		assertThrows(IllegalArgumentException.class, () -> {
			spel.kiesSpeler(0, kleur);
		});

	}

	/*
	 * Er zal nooit een speler of kleur meerdere keren gekozen worden omdat die uit
	 * de kieslijst verdwijnt
	 * 
	 * 
	 * @Test void startSpel_SpelerAlGekozen_WerptExceptie() {
	 * 
	 * SpelerMapper sm = new SpelerMapper(); Spel spel = new Spel();
	 * 
	 * spel.kiesSpeler(sm.geefSpeler("Terry Davis"), "ROOD");
	 * spel.kiesSpeler(sm.geefSpeler("Kathleen Booth"), "GEEL");
	 * spel.kiesSpeler(sm.geefSpeler("ada lovelace"), "ORANGJE");
	 * 
	 * assertThrows(IllegalArgumentException.class, () -> {
	 * spel.kiesSpeler(sm.geefSpeler("Terry Davis"), "GROEN");
	 * 
	 * });
	 * 
	 * }
	 * 
	 * void startSpel_KleurAlGekozen_WerptExceptie() {
	 * 
	 * SpelerMapper sm = new SpelerMapper(); Spel spel = new Spel();
	 * 
	 * spel.kiesSpeler(sm.geefSpeler("Kathleen Booth"), "GEEL");
	 * spel.kiesSpeler(sm.geefSpeler("ada lovelace"), "ORANGJE");
	 * 
	 * assertThrows(IllegalArgumentException.class, () -> {
	 * spel.kiesSpeler(sm.geefSpeler("Terry Davis"), "ORANJE");
	 * 
	 * });
	 * 
	 * }
	 */
	
	/**

	@Test
	void startSpel_VeelTeWeinigDobbelstenen_WerptExceptie() {

		
		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();

		spel.kiesSpeler(sm.geefSpeler("Kathleen Booth"), "GEEL");
		spel.kiesSpeler(sm.geefSpeler("ada lovelace"), "ORANGJE");
		spel.kiesSpeler(sm.geefSpeler("Terry Davis"), "ROOD");
		
		List<Dobbelsteen> dobbelstenen = new ArrayList<>();
		
		for (int i = 0 ; i <3 ; i++ ) {
			dobbelstenen.add(new Dobbelsteen);
		}

		assertThrows(VerkeerdAantalDobbelstenenException.class, () -> {spel.startSpel() ;}); 

	}

	@Test
	void startSpel_NetTeWeinigDobbelstenen_WerptExceptie() {

		
		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();

		spel.kiesSpeler(sm.geefSpeler("Kathleen Booth"), "GEEL");
		spel.kiesSpeler(sm.geefSpeler("ada lovelace"), "ORANGJE");
		spel.kiesSpeler(sm.geefSpeler("Terry Davis"), "ROOD");
		
		List<Dobbelsteen> dobbelstenen = new ArrayList<>();
		
		for (int i = 0 ; i <7 ; i++ ) {
			dobbelstenen.add(new Dobbelsteen);
		}

		assertThrows(VerkeerdAantalDobbelstenenException.class, () -> {spel.startSpel() ;}); 

	}

	@Test
	void startSpel_GenoegDobbelstenen_StartSpel() {

		
		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();

		spel.kiesSpeler(sm.geefSpeler("Kathleen Booth"), "GEEL");
		spel.kiesSpeler(sm.geefSpeler("ada lovelace"), "ORANGJE");
		spel.kiesSpeler(sm.geefSpeler("Terry Davis"), "ROOD");
		
		List<Dobbelsteen> dobbelstenen = new ArrayList<>();
		
		for (int i = 0 ; i <8 ; i++ ) {
			dobbelstenen.add(new Dobbelsteen);
		}

		assertEquals(8, spel.geefDobbelstenen().size());

	}

	@Test
	void startSpel_NetTeVeelDobbelstenen_WerptExceptie() {

		
		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();

		spel.kiesSpeler(sm.geefSpeler("Kathleen Booth"), "GEEL");
		spel.kiesSpeler(sm.geefSpeler("ada lovelace"), "ORANGJE");
		spel.kiesSpeler(sm.geefSpeler("Terry Davis"), "ROOD");
		
		List<Dobbelsteen> dobbelstenen = new ArrayList<>();
		
		for (int i = 0 ; i <8 ; i++ ) {
			dobbelstenen.add(new Dobbelsteen);
		}

		assertThrows(VerkeerdAantalDobbelstenenException.class, () -> {dobbelstenen.add(new Dobbelsteen);}); 

	}

	@Test
	void startSpel_VeelTeVeelDobbelstenen_WerptExceptie() {

		
		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();

		spel.kiesSpeler(sm.geefSpeler("Kathleen Booth"), "GEEL");
		spel.kiesSpeler(sm.geefSpeler("ada lovelace"), "ORANGJE");
		spel.kiesSpeler(sm.geefSpeler("Terry Davis"), "ROOD");
		
		List<Dobbelsteen> dobbelstenen = new ArrayList<>();
		
		for (int i = 0 ; i <8 ; i++ ) {
			dobbelstenen.add(new Dobbelsteen);
		}

		assertThrows(VerkeerdAantalDobbelstenenException.class, () -> {
			dobbelstenen.add(new Dobbelsteen);
			dobbelstenen.add(new Dobbelsteen);s
			dobbelstenen.add(new Dobbelsteen);}); 

	}
	
	*/
	

}
