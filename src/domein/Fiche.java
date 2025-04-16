package domein;

public class Fiche {

	private int positie;
	
	private FicheGebied ficheGebied;
	
	public Fiche(FicheGebied f) {
		ficheGebied = f;
	}

	public int getPositie() {
		return positie;
	}
	
	public void setPositie(int positie) {// methode enkel public voor de testen 'Jelle'
		this.positie = positie;
	}

	public void plaatsNeer(int positie) {
		
//		if (positie < 0 || positie > 6) {
//			throw new IllegalArgumentException(String.format("Ongeldig fiche positie %d", positie));
//		}
		
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
	        ficheGebied.plaatsFicheNeer(this,positie);
	    }
	}
	

