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
		
		
		// moet dit niet uit commentaar?
		
//		if (positie < 0 || positie > 6) {
//			throw new IllegalArgumentException(String.format("Ongeldig fiche positie %d", positie));
//		}
		
	        ficheGebied.plaatsFicheNeer(this,positie);
	    }
	}
	

