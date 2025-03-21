package domein;

public class Fiche {

	int positie;
	
	public Fiche(int positie) {
		setPositie(positie);
		
	}

	public int getPositie() {
		return positie;
	}

	public void setPositie(int positie) {
		
	        if (positie / 100 < 1 || positie / 100 > 6) {
	            throw new IllegalArgumentException("Ongeldige kleur: " + (positie / 100));
	        }
	        if ((positie / 10) % 10 < 1 || (positie / 10) % 10 > 8) {
	            throw new IllegalArgumentException("Ongeldig aantal gebouwen: " + ((positie / 10) % 10));
	        }
	        if (positie % 10 < 1 || positie % 10 > 3) {
	            throw new IllegalArgumentException("Ongeldig aantal worpen: " + (positie % 10));
	        }

	        this.positie = positie;
	    }
	}
	

