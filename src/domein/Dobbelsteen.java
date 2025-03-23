package domein;

import java.security.SecureRandom;

import utils.DobbelsteenKleur;

public class Dobbelsteen {

	private DobbelsteenKleur kleur;

	public Dobbelsteen() {

	}

	public DobbelsteenKleur getDobbelsteenKleur() {
		return this.kleur;
	}

	public void dobbel() {
		int index = new SecureRandom().nextInt(1, DobbelsteenKleur.values().length);
		this.kleur = DobbelsteenKleur.values()[index];
	}

}
