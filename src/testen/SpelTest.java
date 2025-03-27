package testen;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import domein.Spel;
import domein.Speler;
import domein.SpelerRepository;
import persistentie.SpelerMapper;
import utils.SpelerKleur;

class SpelTest {

	// TODO in spelltest moet nog met enums worden gewerkt

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

		spel.kiesSpeler(1, SpelerKleur.BLAUW);
		spel.kiesSpeler(5, SpelerKleur.WIT);

		assertThrows(IllegalArgumentException.class, () -> spel.startSpel());
	}

	@Test
	void startSpel_AantalSpelersNetGenoeg_StartSpel() {

		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();
		SpelerRepository spelerRepo = new SpelerRepository();
		spel.setBeschikbareSpelers(spelerRepo.geefAlleSpelers());

		spel.kiesSpeler(1, SpelerKleur.BLAUW);
		spel.kiesSpeler(3, SpelerKleur.WIT);
		spel.kiesSpeler(4, SpelerKleur.GEEL);

		assertEquals(3, spel.getGekozenSpelers().size());
	}

	@Test
	void startSpel_AantalSpelersMeerDanGenoeg_StartSpel() {

		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();
		SpelerRepository spelerRepo = new SpelerRepository();
		spel.setBeschikbareSpelers(spelerRepo.geefAlleSpelers());

		spel.kiesSpeler(2, SpelerKleur.BLAUW);
		spel.kiesSpeler(1, SpelerKleur.WIT);
		spel.kiesSpeler(0, SpelerKleur.GEEL);
		spel.kiesSpeler(3, SpelerKleur.GROEN);

		assertEquals(4, spel.getGekozenSpelers().size());
	}

	@Test
	void startSpel_MaximumAantalSpelers_StartSpel() {

		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();
		SpelerRepository spelerRepo = new SpelerRepository();
		spel.setBeschikbareSpelers(spelerRepo.geefAlleSpelers());

		spel.kiesSpeler(0, SpelerKleur.BLAUW);
		spel.kiesSpeler(1, SpelerKleur.WIT);
		spel.kiesSpeler(2, SpelerKleur.ROOD);
		spel.kiesSpeler(3, SpelerKleur.GROEN);
		spel.kiesSpeler(4, SpelerKleur.ORANJE);
		spel.kiesSpeler(5, SpelerKleur.GEEL);

		assertEquals(6, spel.getGekozenSpelers().size());
	}

	@Test
	void startSpel_AantalSpelersNetTeVeel_WerptExceptie() {

		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();
		SpelerRepository spelerRepo = new SpelerRepository();
		spel.setBeschikbareSpelers(spelerRepo.geefAlleSpelers());

		spel.kiesSpeler(0, SpelerKleur.BLAUW);
		spel.kiesSpeler(1, SpelerKleur.WIT);
		spel.kiesSpeler(2, SpelerKleur.ROOD);
		spel.kiesSpeler(3, SpelerKleur.GROEN);
		spel.kiesSpeler(4, SpelerKleur.ORANJE);
		spel.kiesSpeler(5, SpelerKleur.GEEL);

		assertThrows(IllegalArgumentException.class, () -> spel.kiesSpeler(6, SpelerKleur.GEEL));
	}

	/** deze code loopt normaal al fout bij de 7de aanroep, misschien aanpassen */
	@Test
	void startSpel_AantalSpelersVeelTeVeel_WerptExceptie() {

		SpelerMapper sm = new SpelerMapper();
		Spel spel = new Spel();
		SpelerRepository spelerRepo = new SpelerRepository();
		spel.setBeschikbareSpelers(spelerRepo.geefAlleSpelers());

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

		Spel spel = new Spel();
		SpelerRepository spelerRepo = new SpelerRepository();
		spel.setBeschikbareSpelers(spelerRepo.geefAlleSpelers());

		spel.kiesSpeler(2, SpelerKleur.ROOD);
		spel.kiesSpeler(3, SpelerKleur.GROEN);
		spel.kiesSpeler(4, SpelerKleur.ORANJE);

		spel.startSpel();

		assertEquals(5, spel.geefAantalZetstenen());

	}

	@Test
	void maakZetStenenAan_BijVierSpelers_WijstVierZetstenenToe() {

		Spel spel = new Spel();
		SpelerRepository spelerRepo = new SpelerRepository();
		spel.setBeschikbareSpelers(spelerRepo.geefAlleSpelers());

		spel.kiesSpeler(2, SpelerKleur.ROOD);
		spel.kiesSpeler(3, SpelerKleur.GROEN);
		spel.kiesSpeler(4, SpelerKleur.ORANJE);
		spel.kiesSpeler(5, SpelerKleur.BLAUW);

		spel.startSpel();

		assertEquals(4, spel.geefAantalZetstenen());

	}

	@Test
	void maakZetStenenAan_BijVijfSpelers_WijstDrieZetstenenToe() {

		Spel spel = new Spel();
		SpelerRepository spelerRepo = new SpelerRepository();
		spel.setBeschikbareSpelers(spelerRepo.geefAlleSpelers());

		spel.kiesSpeler(2, SpelerKleur.ROOD);
		spel.kiesSpeler(3, SpelerKleur.GROEN);
		spel.kiesSpeler(4, SpelerKleur.ORANJE);
		spel.kiesSpeler(5, SpelerKleur.BLAUW);
		spel.kiesSpeler(6, SpelerKleur.GEEL);

		spel.startSpel();

		assertEquals(3, spel.geefAantalZetstenen());

	}

	@Test
	void maakZetStenenAan_BijZesSpelers_WijstDrieZetstenenToe() {

		Spel spel = new Spel();
		SpelerRepository spelerRepo = new SpelerRepository();
		spel.setBeschikbareSpelers(spelerRepo.geefAlleSpelers());

		spel.kiesSpeler(2, SpelerKleur.ROOD);
		spel.kiesSpeler(3, SpelerKleur.GROEN);
		spel.kiesSpeler(4, SpelerKleur.ORANJE);
		spel.kiesSpeler(5, SpelerKleur.BLAUW);
		spel.kiesSpeler(6, SpelerKleur.GEEL);
		spel.kiesSpeler(7, SpelerKleur.WIT);

		spel.startSpel();

		assertEquals(3, spel.geefAantalZetstenen());

	}

	@Test
	void berekenWinnaar_DeWinnaarWordtBepaalt_ReturnedDeWinnaard() {
	
		int waarde;
		List<Integer> puntenPerSpeler = new ArrayList<>();
		
		
		Spel spel = new Spel();
		SpelerRepository spelerRepo = new SpelerRepository();
		spel.setBeschikbareSpelers(spelerRepo.geefAlleSpelers());

		spel.kiesSpeler(2, SpelerKleur.ROOD);
		spel.kiesSpeler(3, SpelerKleur.GROEN);
		spel.kiesSpeler(4, SpelerKleur.ORANJE);
		
		for (Speler speler : spel.getGekozenSpelers()) {
			waarde = new SecureRandom().nextInt(1, 100);
			speler.voegPuntenToe(waarde);
			puntenPerSpeler.add(spel.getGekozenSpelers().indexOf(speler), waarde);
		}
		
		assertEquals(puntenPerSpeler, null);
		
		
	}
	
}
