package testen;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Spel;
import domein.Speler;
import domein.SpelerRepository;
import persistentie.SpelerMapper;
import utils.SpelerKleur;

class SpelTest {

	private Spel spel;
	
	@BeforeEach
	void setUp() {
		spel = new Spel(new SpelerRepository().geefAlleSpelers());
	}
	@Test
	void startSpel_AantalSpelersVeelTeLaag_WerptExceptie() {

		

		assertThrows(IllegalArgumentException.class, () -> spel.startSpel());
	}

	// code geschreven met Enum Kleuren indien daar gebruik van zal gemaakt worden
	@Test
	void startSpel_AantalSpelersNetTeLaag_WerptExceptie() {


		spel.kiesSpeler(1, SpelerKleur.BLAUW);
		spel.kiesSpeler(5, SpelerKleur.WIT);

		assertThrows(IllegalArgumentException.class, () -> spel.startSpel());
	}

	@Test
	void startSpel_AantalSpelersNetGenoeg_StartSpel() {


		spel.kiesSpeler(1, SpelerKleur.BLAUW);
		spel.kiesSpeler(3, SpelerKleur.WIT);
		spel.kiesSpeler(4, SpelerKleur.GEEL);

		assertEquals(3, spel.getGekozenSpelers().size());
	}

	@Test
	void startSpel_AantalSpelersMeerDanGenoeg_StartSpel() {


		spel.kiesSpeler(2, SpelerKleur.BLAUW);
		spel.kiesSpeler(1, SpelerKleur.WIT);
		spel.kiesSpeler(0, SpelerKleur.GEEL);
		spel.kiesSpeler(3, SpelerKleur.GROEN);

		assertEquals(4, spel.getGekozenSpelers().size());
	}

	@Test
	void startSpel_MaximumAantalSpelers_StartSpel() {


		spel.kiesSpeler(0, SpelerKleur.BLAUW);
		spel.kiesSpeler(1, SpelerKleur.WIT);
		spel.kiesSpeler(2, SpelerKleur.ROOD);
		spel.kiesSpeler(3, SpelerKleur.GROEN);
		spel.kiesSpeler(4, SpelerKleur.ORANJE);
		spel.kiesSpeler(5, SpelerKleur.GEEL);

		assertEquals(6, spel.getGekozenSpelers().size());
	}

//	@Test
//	void startSpel_AantalSpelersNetTeVeel_WerptExceptie() {
//
//		SpelerMapper sm = new SpelerMapper();
//		Spel spel = new Spel();
//		SpelerRepository spelerRepo = new SpelerRepository();
//		spel.setBeschikbareSpelers(spelerRepo.geefAlleSpelers());
//
//		spel.kiesSpeler(0, SpelerKleur.BLAUW);
//		spel.kiesSpeler(1, SpelerKleur.WIT);
//		spel.kiesSpeler(2, SpelerKleur.ROOD);
//		spel.kiesSpeler(3, SpelerKleur.GROEN);
//		spel.kiesSpeler(4, SpelerKleur.ORANJE);
//		spel.kiesSpeler(5, SpelerKleur.GEEL);
//
//		assertThrows(IllegalArgumentException.class, () -> spel.kiesSpeler(6, SpelerKleur.GEEL));
//	}

	/** deze code loopt normaal al fout bij de 7de aanroep, misschien aanpassen */
	@Test
	void startSpel_AantalSpelersVeelTeVeel_WerptExceptie() {


		spel.kiesSpeler(0, SpelerKleur.BLAUW);
		spel.kiesSpeler(1, SpelerKleur.WIT);
		spel.kiesSpeler(2, SpelerKleur.ROOD);
		spel.kiesSpeler(3, SpelerKleur.GROEN);
		spel.kiesSpeler(4, SpelerKleur.ORANJE);
		spel.kiesSpeler(5, SpelerKleur.GEEL);

		assertThrows(IllegalArgumentException.class, () -> {
			spel.kiesSpeler(6, SpelerKleur.ROOD);
			spel.kiesSpeler(7, SpelerKleur.GEEL);
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
	 * 
	 * @Test void startSpel_VeelTeWeinigDobbelstenen_WerptExceptie() {
	 * 
	 * 
	 *       SpelerMapper sm = new SpelerMapper(); Spel spel = new Spel();
	 * 
	 *       spel.kiesSpeler(1, SpelerKleur.BLAUW); spel.kiesSpeler(3,
	 *       SpelerKleur.WIT); spel.kiesSpeler(4, SpelerKleur.GEEL);
	 * 
	 *       List<Dobbelsteen> dobbelstenen = new ArrayList<>();
	 * 
	 *       for (int i = 0 ; i <3 ; i++ ) { dobbelstenen.add(new Dobbelsteen); }
	 * 
	 *       assertThrows(VerkeerdAantalDobbelstenenException.class, () ->
	 *       {spel.startSpel() ;});
	 * 
	 *       }
	 * 
	 * @Test void startSpel_NetTeWeinigDobbelstenen_WerptExceptie() {
	 * 
	 * 
	 *       SpelerMapper sm = new SpelerMapper(); Spel spel = new Spel();
	 * 
	 *       spel.kiesSpeler(1, SpelerKleur.BLAUW); spel.kiesSpeler(3,
	 *       SpelerKleur.WIT); spel.kiesSpeler(4, SpelerKleur.GEEL);
	 * 
	 *       List<Dobbelsteen> dobbelstenen = new ArrayList<>();
	 * 
	 *       for (int i = 0 ; i <7 ; i++ ) { dobbelstenen.add(new Dobbelsteen); }
	 * 
	 *       assertThrows(VerkeerdAantalDobbelstenenException.class, () ->
	 *       {spel.startSpel() ;});
	 * 
	 *       }
	 * 
	 * @Test void startSpel_GenoegDobbelstenen_StartSpel() {
	 * 
	 * 
	 *       SpelerMapper sm = new SpelerMapper(); Spel spel = new Spel();
	 * 
	 *       spel.kiesSpeler(1, SpelerKleur.BLAUW); spel.kiesSpeler(3,
	 *       SpelerKleur.WIT); spel.kiesSpeler(4, SpelerKleur.GEEL);
	 * 
	 *       List<Dobbelsteen> dobbelstenen = new ArrayList<>();
	 * 
	 *       for (int i = 0 ; i <8 ; i++ ) { dobbelstenen.add(new Dobbelsteen); }
	 * 
	 *       assertEquals(8, spel.geefDobbelstenen().size());
	 * 
	 *       }
	 * 
	 * @Test void startSpel_NetTeVeelDobbelstenen_WerptExceptie() {
	 * 
	 * 
	 *       SpelerMapper sm = new SpelerMapper(); Spel spel = new Spel();
	 * 
	 *       spel.kiesSpeler(1, SpelerKleur.BLAUW); spel.kiesSpeler(3,
	 *       SpelerKleur.WIT); spel.kiesSpeler(4, SpelerKleur.GEEL);
	 * 
	 *       List<Dobbelsteen> dobbelstenen = new ArrayList<>();
	 * 
	 *       for (int i = 0 ; i <8 ; i++ ) { dobbelstenen.add(new Dobbelsteen); }
	 * 
	 *       assertThrows(VerkeerdAantalDobbelstenenException.class, () ->
	 *       {dobbelstenen.add(new Dobbelsteen);});
	 * 
	 *       }
	 * 
	 * @Test void startSpel_VeelTeVeelDobbelstenen_WerptExceptie() {
	 * 
	 * 
	 *       SpelerMapper sm = new SpelerMapper(); Spel spel = new Spel();
	 * 
	 *       spel.kiesSpeler(1, SpelerKleur.BLAUW); spel.kiesSpeler(3,
	 *       SpelerKleur.WIT); spel.kiesSpeler(4, SpelerKleur.GEEL);
	 * 
	 *       List<Dobbelsteen> dobbelstenen = new ArrayList<>();
	 * 
	 *       for (int i = 0 ; i <8 ; i++ ) { dobbelstenen.add(new Dobbelsteen); }
	 * 
	 *       assertThrows(VerkeerdAantalDobbelstenenException.class, () -> {
	 *       dobbelstenen.add(new Dobbelsteen); dobbelstenen.add(new Dobbelsteen);s
	 *       dobbelstenen.add(new Dobbelsteen);});
	 * 
	 *       }
	 */

	@Test
	void maakZetStenenAan_BijDrieSpelers_WijstVijfZetstenenToe() {


		spel.kiesSpeler(2, SpelerKleur.ROOD);
		spel.kiesSpeler(3, SpelerKleur.GROEN);
		spel.kiesSpeler(4, SpelerKleur.ORANJE);

		spel.startSpel();

		assertEquals(5, spel.getAantalZetstenen());

	}

	@Test
	void maakZetStenenAan_BijVierSpelers_WijstVierZetstenenToe() {


		spel.kiesSpeler(2, SpelerKleur.ROOD);
		spel.kiesSpeler(3, SpelerKleur.GROEN);
		spel.kiesSpeler(4, SpelerKleur.ORANJE);
		spel.kiesSpeler(5, SpelerKleur.BLAUW);

		spel.startSpel();

		assertEquals(4, spel.getAantalZetstenen());

	}

	@Test
	void maakZetStenenAan_BijVijfSpelers_WijstDrieZetstenenToe() {


		spel.kiesSpeler(2, SpelerKleur.ROOD);
		spel.kiesSpeler(3, SpelerKleur.GROEN);
		spel.kiesSpeler(4, SpelerKleur.ORANJE);
		spel.kiesSpeler(5, SpelerKleur.BLAUW);
		spel.kiesSpeler(6, SpelerKleur.GEEL);

		spel.startSpel();

		assertEquals(3, spel.getAantalZetstenen());

	}

	@Test
	void maakZetStenenAan_BijZesSpelers_WijstDrieZetstenenToe() {


		spel.kiesSpeler(2, SpelerKleur.ROOD);
		spel.kiesSpeler(3, SpelerKleur.GROEN);
		spel.kiesSpeler(4, SpelerKleur.ORANJE);
		spel.kiesSpeler(5, SpelerKleur.BLAUW);
		spel.kiesSpeler(6, SpelerKleur.GEEL);
		spel.kiesSpeler(7, SpelerKleur.WIT);

		spel.startSpel();

		assertEquals(3, spel.getAantalZetstenen());

	}

	@Test
	void berekenWinnaar_DeWinnaarWordtBepaalt_ReturnedDeWinnaard() {
	
//		int waarde;
//		List<Integer> puntenPerSpeler = new ArrayList<>();
//		
//		
//		Spel spel = new Spel();
//		SpelerRepository spelerRepo = new SpelerRepository();
//		spel.setBeschikbareSpelers(spelerRepo.geefAlleSpelers());
//
//		spel.kiesSpeler(2, SpelerKleur.ROOD);
//		spel.kiesSpeler(3, SpelerKleur.GROEN);
//		spel.kiesSpeler(4, SpelerKleur.ORANJE);
//		
//		for (Speler speler : spel.getGekozenSpelers()) {
//			waarde = new SecureRandom().nextInt(1, 100);
//			speler.voegPuntenToe(waarde);
//			puntenPerSpeler.add(spel.getGekozenSpelers().indexOf(speler), waarde);
//		}
//		
//		assertEquals(puntenPerSpeler, null);
		
		SpelerRepository spelerRepo = new SpelerRepository();
		spel.setBeschikbareSpelers(spelerRepo.geefAlleSpelers());
		
		spel.kiesSpeler(2, SpelerKleur.ROOD);
		spel.kiesSpeler(3, SpelerKleur.GROEN);
		spel.kiesSpeler(4, SpelerKleur.ORANJE);
		
		spel.getGekozenSpelers().get(0).voegPuntenToe(50);  
	    spel.getGekozenSpelers().get(1).voegPuntenToe(70);  
	    spel.getGekozenSpelers().get(2).voegPuntenToe(70);
	    
	    List<Speler> winnaars = spel.berekenWinnaar();
	    
	    assertEquals(2, winnaars.size());
	    assertTrue(winnaars.contains(spel.getGekozenSpelers().get(1)));
	    assertTrue(winnaars.contains(spel.getGekozenSpelers().get(2)));
	    assertFalse(winnaars.contains(spel.getGekozenSpelers().get(0)));
		
	}
	
	@Test
	void veranderHuidigeSpeler_veranderdCorrectVanStartSpelerNaar_DeVolgendeInDeLijstGekozenSpeler_AssertEquals() {
		
		spel.kiesSpeler(1, SpelerKleur.ROOD);
		spel.kiesSpeler(1, SpelerKleur.GROEN);
		spel.kiesSpeler(1, SpelerKleur.ORANJE);
		
		spel.startSpel();
		
		Speler startSpeler = spel.getGekozenSpelers().get(0);
		Speler volgendeSpeler = spel.getGekozenSpelers().get(1);
		
		spel.setStartSpeler(startSpeler);
		spel.veranderHuidigeSpeler(1);
		spel.rolDobbelstenen();
		
		spel.beïndigBeurt(spel.getDobbelstenen().getFirst().getDobbelsteenKleur());
		
		assertTrue(startSpeler.getIsStartSpeler());
		assertEquals(startSpeler,spel.getStartSpeler());
		assertEquals(volgendeSpeler, spel.getHuidigeSpeler());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {2,3,4,5,6})
	void veranderHuidigeSpeler_veranderdCorrectVanStartSpelerNaar_DeJuisteGekozenSpeler_AssertEquals(int aantalOproepen) {
		
		spel.kiesSpeler(1, SpelerKleur.ROOD);
		spel.kiesSpeler(1, SpelerKleur.GROEN);
		spel.kiesSpeler(1, SpelerKleur.ORANJE);
		spel.kiesSpeler(1, SpelerKleur.GEEL);
		
		spel.startSpel();
		
		Speler startSpeler = spel.getGekozenSpelers().get(0);
		Speler volgendeSpeler = spel.getGekozenSpelers().get(aantalOproepen % 4);
		
		spel.setStartSpeler(startSpeler);
		spel.veranderHuidigeSpeler(1);
		for(int ind = 1;ind <= aantalOproepen;ind ++) {
			spel.rolDobbelstenen();
			
			spel.beïndigBeurt(spel.getDobbelstenen().getFirst().getDobbelsteenKleur());
		}
		
		assertTrue(startSpeler.getIsStartSpeler());
		assertEquals(startSpeler,spel.getStartSpeler());
		assertEquals(volgendeSpeler, spel.getHuidigeSpeler());
	}
	
}
