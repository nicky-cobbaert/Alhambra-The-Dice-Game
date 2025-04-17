package domein;

import java.security.SecureRandom;

import utils.DobbelsteenKleur;

public class Dobbelsteen {

	private DobbelsteenKleur kleur;
	private boolean nogRollen;

	public Dobbelsteen() {
		this.nogRollen = true;
	}
	
	public void setNogRollen(boolean nogRollen) {
		this.nogRollen = nogRollen;
	}
	
	public boolean getNogRollen() {
		return this.nogRollen;
	}

	public DobbelsteenKleur getDobbelsteenKleur() {
		return this.kleur;
	}

	public void dobbel() {
		if(nogRollen) {
			int index = new SecureRandom().nextInt(0, DobbelsteenKleur.values().length);
			this.kleur = DobbelsteenKleur.values()[index];
		}
	}

}
