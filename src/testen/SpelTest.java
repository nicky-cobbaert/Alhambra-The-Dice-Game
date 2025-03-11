package testen;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import domein.Spel;
import persistentie.SpelerMapper;
import utils.Kleuren;

class SpelTest {

	@Test
	void startSpel_AantalSpelersVeelTeLaag_WerptExceptie() {

		Spel spel = new Spel();

		assertThrows(IllegalArgumentException.class, () -> spel.startSpel());
	}

	
	//code geschreven met Enum Kleuren indien daar gebruik van zal gemaakt worden
	@Test
	void startSpel_AantalSpelersNetTeLaag_WerptExceptie() {

		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();

		spel.kiesSpeler(sm.geefSpeler("AeersteInDeRijTest"), Kleuren.BLAUW);
		spel.kiesSpeler(sm.geefSpeler("ZlaatsteInDeRijTest"), Kleuren.WIT);

		assertThrows(IllegalArgumentException.class, () -> spel.startSpel());
	}

	@Test
	void startSpel_AantalSpelersNetGenoeg_StartSpel() {

		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();

		spel.kiesSpeler(sm.geefSpeler("AeersteInDeRijTest"), Kleuren.BLAUW);
		spel.kiesSpeler(sm.geefSpeler("ZlaatsteInDeRijTest"), Kleuren.WIT);
		spel.kiesSpeler(sm.geefSpeler("ada lovelace"), Kleuren.GEEL);

		assertEquals(3, spel.getGekozenSpelers().size());
	}

	@Test
	void startSpel_AantalSpelersMeerDanGenoeg_StartSpel() {

		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();

		spel.kiesSpeler(sm.geefSpeler("AeersteInDeRijTest"), Kleuren.BLAUW);
		spel.kiesSpeler(sm.geefSpeler("ZlaatsteInDeRijTest"), Kleuren.WIT);
		spel.kiesSpeler(sm.geefSpeler("ada lovelace"), Kleuren.GEEL);
		spel.kiesSpeler(sm.geefSpeler("Kathleen Booth"), Kleuren.GROEN);

		assertEquals(3, spel.getGekozenSpelers().size());
	}

	@Test
	void startSpel_MaximumAantalSpelers_StartSpel() {

		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();

		spel.kiesSpeler(sm.geefSpeler("AeersteInDeRijTest"), Kleuren.BLAUW);
		spel.kiesSpeler(sm.geefSpeler("ZlaatsteInDeRijTest"), Kleuren.WIT);
		spel.kiesSpeler(sm.geefSpeler("JelleVanHoren"), Kleuren.ROOD);
		spel.kiesSpeler(sm.geefSpeler("WoutGHEYSELS"), Kleuren.GROEN);
		spel.kiesSpeler(sm.geefSpeler("Sverre"), Kleuren.ORANJE);
		spel.kiesSpeler(sm.geefSpeler("ada lovelace"), Kleuren.GEEL);

		assertEquals(6, spel.getGekozenSpelers().size());
	}

	@Test
	void startSpel_AantalSpelersNetTeVeel_WerptExceptie() {

		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();

		spel.kiesSpeler(sm.geefSpeler("AeersteInDeRijTest"), Kleuren.BLAUW);
		spel.kiesSpeler(sm.geefSpeler("ZlaatsteInDeRijTest"), Kleuren.WIT);
		spel.kiesSpeler(sm.geefSpeler("JelleVanHoren"), Kleuren.ROOD);
		spel.kiesSpeler(sm.geefSpeler("WoutGHEYSELS"), Kleuren.GROEN);
		spel.kiesSpeler(sm.geefSpeler("Sverre"), Kleuren.ORANJE);
		spel.kiesSpeler(sm.geefSpeler("Kathleen Booth"), Kleuren.GEEL);

		assertThrows(IllegalArgumentException.class,
				() -> spel.kiesSpeler(sm.geefSpeler("ada lovelace"), Kleuren.GEEL));
	}

	
	
//	deze code loopt normaal al fout bij de 7de aanroep, misschien aanpassen
	@Test
	void startSpel_AantalSpelersVeelTeVeel_WerptExceptie() {
		
		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();
		
		spel.kiesSpeler(sm.geefSpeler("AeersteInDeRijTest"), Kleuren.BLAUW);
		spel.kiesSpeler(sm.geefSpeler("ZlaatsteInDeRijTest"), Kleuren.WIT);
		spel.kiesSpeler(sm.geefSpeler("JelleVanHoren"), Kleuren.ROOD);
		spel.kiesSpeler(sm.geefSpeler("WoutGHEYSELS"), Kleuren.GROEN);
		spel.kiesSpeler(sm.geefSpeler("Sverre"), Kleuren.ORANJE);
		spel.kiesSpeler(sm.geefSpeler("Kathleen Booth"), Kleuren.GEEL);
		spel.kiesSpeler(sm.geefSpeler("Terry Davis"), Kleuren.ROOD );
		
		assertThrows(IllegalArgumentException.class, ()-> 
			spel.kiesSpeler(sm.geefSpeler("ada lovelace"), Kleuren.BLAUW ));
	}

}


