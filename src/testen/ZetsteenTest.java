package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Zetsteen;

public class ZetsteenTest {

	/**
	 * Zetsteen moet nog een invoercontrole voor het 683-positie principe te
	 * krijgen:
	 * 
	 * Kleur moet tussen 1-6 liggen 
	 * Zetsteen positie Y moet tussen 1-8 liggen
	 * Zetsteen positie X moet tussen 1-3 liggen 
	 * 			
	 * 			~sverre lippens en Jelle Van Horen 2025~
	 */

	//kleur-----------------------------------------------------------
	
	@ParameterizedTest
	@ValueSource(ints = { 022, -121, 031 })
	void plaatsNeer_KleurTeLaag_werptExceptie(int positie) {

		Zetsteen zetsteen = new Zetsteen();

		assertThrows(IllegalArgumentException.class, () -> zetsteen.plaatsNeer(positie));

	}

	@ParameterizedTest
	@ValueSource(ints = { 121, 322, 633 })
	void plaatsNeer_KleurGrootGenoeg_KentPositieToe(int positie) {

		Zetsteen zetsteen = new Zetsteen();
		zetsteen.plaatsNeer(positie);

		assertEquals(positie, zetsteen.getPositie());

	}
	
	@ParameterizedTest
	@ValueSource(ints = { 722, 821, 931 })
	void plaatsNeer_KleurTeGroot_werptExceptie(int positie) {

		Zetsteen zetsteen = new Zetsteen();

		assertThrows(IllegalArgumentException.class, () -> zetsteen.plaatsNeer(positie));

	}
	
	//vertical-----------------------------------------

	@ParameterizedTest
	@ValueSource(ints = { 502, -321 })
	void plaatsNeer_VerticalePositieTeLaag_werptExceptie(int positie) {

		Zetsteen zetsteen = new Zetsteen();

		assertThrows(IllegalArgumentException.class, () -> zetsteen.plaatsNeer(positie));

	}

	@ParameterizedTest
	@ValueSource(ints = { 111, 352, 683 })
	void plaatsNeer_VerticalePositieGenoeg_KentPositieToe(int positie) {

		Zetsteen zetsteen = new Zetsteen();
		zetsteen.plaatsNeer(positie);

		assertEquals(positie, zetsteen.getPositie());

	}
	
	@ParameterizedTest
	@ValueSource(ints = { 592, 3101 })
	void plaatsNeer_VerticalePositieTeGroot_werptExceptie(int positie) {

		Zetsteen zetsteen = new Zetsteen();

		assertThrows(IllegalArgumentException.class, () -> zetsteen.plaatsNeer(positie));

	}
	
	//horizontal-------------------------------------------------------------

	@ParameterizedTest
	@ValueSource(ints = { 220, -121 })
	void plaatsNeer_HorizontalePositieTeLaag_werptExceptie(int positie) {

		Zetsteen zetsteen = new Zetsteen();

		assertThrows(IllegalArgumentException.class, () -> zetsteen.plaatsNeer(positie));

	}

	@ParameterizedTest
	@ValueSource(ints = { 121, 322, 633 })
	void plaatsNeer_HorizontalePositieGrootGenoeg_KentPositieToe(int positie) {

		Zetsteen zetsteen = new Zetsteen();
		zetsteen.plaatsNeer(positie);
		
		assertEquals(positie, zetsteen.getPositie());

	}
	
	@ParameterizedTest
	@ValueSource(ints = { 224, 126 , 539 })
	void plaatsNeer_HorizontalePositieTeGroot_werptExceptie(int positie) {

		Zetsteen zetsteen = new Zetsteen();

		assertThrows(IllegalArgumentException.class, () -> zetsteen.plaatsNeer(positie));

	}

}
