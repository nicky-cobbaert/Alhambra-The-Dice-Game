package testen;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Spel;
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

		spel.kiesSpeler(sm.geefSpeler("AeersteInDeRijTest"), "BLAUW");
		spel.kiesSpeler(sm.geefSpeler("ZlaatsteInDeRijTest"), "WIT");

		assertThrows(IllegalArgumentException.class, () -> spel.startSpel());
	}

	@Test
	void startSpel_AantalSpelersNetGenoeg_StartSpel() {

		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();

		spel.kiesSpeler(sm.geefSpeler("AeersteInDeRijTest"), "BLAUW");
		spel.kiesSpeler(sm.geefSpeler("ZlaatsteInDeRijTest"), "WIT");
		spel.kiesSpeler(sm.geefSpeler("ada lovelace"), "GEEL");

		assertEquals(3, spel.getGekozenSpelers().size());
	}

	@Test
	void startSpel_AantalSpelersMeerDanGenoeg_StartSpel() {

		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();

		spel.kiesSpeler(sm.geefSpeler("AeersteInDeRijTest"), "BLAUW");
		spel.kiesSpeler(sm.geefSpeler("ZlaatsteInDeRijTest"), "WIT");
		spel.kiesSpeler(sm.geefSpeler("ada lovelace"), "GEEL");
		spel.kiesSpeler(sm.geefSpeler("Kathleen Booth"), "GROEN");

		assertEquals(4, spel.getGekozenSpelers().size());
	}

	@Test
	void startSpel_MaximumAantalSpelers_StartSpel() {

		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();

		spel.kiesSpeler(sm.geefSpeler("AeersteInDeRijTest"), "BLAUW");
		spel.kiesSpeler(sm.geefSpeler("ZlaatsteInDeRijTest"), "WIT");
		spel.kiesSpeler(sm.geefSpeler("JelleVanHoren"), "ROOD");
		spel.kiesSpeler(sm.geefSpeler("WoutGHEYSELS"), "GROEN");
		spel.kiesSpeler(sm.geefSpeler("Sverre"), "ORANJE");
		spel.kiesSpeler(sm.geefSpeler("ada lovelace"), "GEEL");

		assertEquals(6, spel.getGekozenSpelers().size());
	}

	@Test
	void startSpel_AantalSpelersNetTeVeel_WerptExceptie() {

		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();

		spel.kiesSpeler(sm.geefSpeler("AeersteInDeRijTest"), "BLAUW");
		spel.kiesSpeler(sm.geefSpeler("ZlaatsteInDeRijTest"), "WIT");
		spel.kiesSpeler(sm.geefSpeler("JelleVanHoren"), "ROOD");
		spel.kiesSpeler(sm.geefSpeler("WoutGHEYSELS"), "GROEN");
		spel.kiesSpeler(sm.geefSpeler("Sverre"), "ORANJE");
		spel.kiesSpeler(sm.geefSpeler("Kathleen Booth"), "GEEL");

		assertThrows(IllegalArgumentException.class, () -> spel.kiesSpeler(sm.geefSpeler("ada lovelace"), "GEEL"));
	}

	/** deze code loopt normaal al fout bij de 7de aanroep, misschien aanpassen */
	@Test
	void startSpel_AantalSpelersVeelTeVeel_WerptExceptie() {

		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();

		spel.kiesSpeler(sm.geefSpeler("AeersteInDeRijTest"), "BLAUW");
		spel.kiesSpeler(sm.geefSpeler("ZlaatsteInDeRijTest"), "WIT");
		spel.kiesSpeler(sm.geefSpeler("JelleVanHoren"), "ROOD");
		spel.kiesSpeler(sm.geefSpeler("WoutGHEYSELS"), "GROEN");
		spel.kiesSpeler(sm.geefSpeler("Sverre"), "ORANJE");
		spel.kiesSpeler(sm.geefSpeler("Kathleen Booth"), "GEEL");

		assertThrows(IllegalArgumentException.class, () -> {
			spel.kiesSpeler(sm.geefSpeler("Terry Davis"), "ROOD");
			spel.kiesSpeler(sm.geefSpeler("ada lovelace"), "GEEL");
		});

	}

	@ParameterizedTest
	@ValueSource(strings = { "blablabla", "Appelblauwzeegroen" })
	void startSpel_KleurOnbestaand_WerptExceptie(String kleur) {
		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();

		assertThrows(IllegalArgumentException.class, () -> {
			spel.kiesSpeler(sm.geefSpeler("ada lovelace"), kleur);
		});

	}

	@NullAndEmptySource
	@ParameterizedTest
	void startSpel_KleurNullOfLeeg_WerptExceptie(String kleur) {
		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();

		assertThrows(IllegalArgumentException.class, () -> {
			spel.kiesSpeler(sm.geefSpeler("ada lovelace"), kleur);
		});

	}

	/*Er zal nooit een speler of kleur meerdere keren gekozen worden omdat die uit de kieslijst verdwijnt
	 * 
	 * 
	 *@Test
	void startSpel_SpelerAlGekozen_WerptExceptie() {

		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();

		spel.kiesSpeler(sm.geefSpeler("Terry Davis"), "ROOD");
		spel.kiesSpeler(sm.geefSpeler("Kathleen Booth"), "GEEL");
		spel.kiesSpeler(sm.geefSpeler("ada lovelace"), "ORANGJE");

		assertThrows(IllegalArgumentException.class, () -> {
			spel.kiesSpeler(sm.geefSpeler("Terry Davis"), "GROEN");

		});

	}

	void startSpel_KleurAlGekozen_WerptExceptie() {

		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();

		spel.kiesSpeler(sm.geefSpeler("Kathleen Booth"), "GEEL");
		spel.kiesSpeler(sm.geefSpeler("ada lovelace"), "ORANGJE");

		assertThrows(IllegalArgumentException.class, () -> {
			spel.kiesSpeler(sm.geefSpeler("Terry Davis"), "ORANJE");

		});

	}*/
	
	

	
	
}
