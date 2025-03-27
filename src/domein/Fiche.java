package domein;

public class Fiche {

	int positie;
	
	public Fiche() {
		
	}

	public int getPositie() {
		return positie;
	}

	public void plaatsNeer(int positie) {
		
		if (positie < 1 || positie > 6) {
			throw new IllegalArgumentException(String.format("Ongeldige bonusfiche positie %d", positie));
		}
		
		// perongeluk zetsteen setter gemaakt hier ook verkeerde test
//	        if (positie / 100 < 1 || positie / 100 > 6) {
//	            throw new IllegalArgumentException("Ongeldige kleur: " + (positie / 100));
//	        }
//	        if ((positie / 10) % 10 < 1 || (positie / 10) % 10 > 8) {
//	            throw new IllegalArgumentException("Ongeldig aantal gebouwen: " + ((positie / 10) % 10));
//	        }
//	        if (positie % 10 < 1 || positie % 10 > 3) {
//	            throw new IllegalArgumentException("Ongeldig aantal worpen: " + (positie % 10));
//	        }

	        this.positie = positie;
	    }
	}
	

