package domein;

import java.util.Objects;

import utils.Kleuren;

public class Speler 
{
    private String gebruikersnaam;
    private int geboortejaar;
    private int aantalGewonnen, aantalGespeeld;
    private Kleuren kleur; //waarom geen getters/setters?
    
   

	private final static int HUIDIG_JAAR = 2025; //moet dit niet op dcd?    

    public Speler(String gebruikersnaam, int  geboortejaar) 
    {
    	this(gebruikersnaam,geboortejaar,0,0,Kleuren.BLAUW);
    }
    
    public Speler(String gebruikersnaam,int  geboortejaar, int aantalGewonnen, int aantalGespeeld, Kleuren kleur) 
    {
    	setGebruikersnaam(gebruikersnaam);
    	setGeboortejaar(geboortejaar);
    	setAantalGewonnen(aantalGewonnen);
    	setAantalGespeeld(aantalGespeeld);
    	setKleur(kleur);
    }
    
    

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	private void setGebruikersnaam(String gebruikersnaam) {
		if (gebruikersnaam==null || gebruikersnaam.isEmpty() || gebruikersnaam.isBlank() || gebruikersnaam.length()<6) {
			throw new IllegalArgumentException("Ongeldige gebruikersnaam");
		}
		
		this.gebruikersnaam = gebruikersnaam;
	}

	public int getGeboortejaar() {
		return geboortejaar;
	}

	private void setGeboortejaar(int geboortejaar) {
		if (geboortejaar+6>HUIDIG_JAAR || geboortejaar+100<HUIDIG_JAAR) {
			throw new IllegalArgumentException("Je moet tussen de leeftijd van 6 en 100 zijn om dit spel te mogen spelen");
		}
		this.geboortejaar = geboortejaar;
	}

	public int getAantalGewonnen() {
		return aantalGewonnen;
	}

	private void setAantalGewonnen(int aantalGewonnen) {
		if (aantalGewonnen<0) {
			throw new IllegalArgumentException("Je moet een positief getal (of 0) aantal gewonnen spellen hebben");
		}
		this.aantalGewonnen = aantalGewonnen;
	}

	public int getAantalGespeeld() {
		return aantalGespeeld;
	}

	private void setAantalGespeeld(int aantalGespeeld) {
		if (aantalGespeeld<0) {
			throw new IllegalArgumentException("Je moet een positief getal (of 0) aantal gespeelde spellen hebben");
		}
		this.aantalGespeeld = aantalGespeeld;
	}
	
	    public Kleuren getKleur() {
			return kleur;
		}

		public void setKleur(Kleuren kleur) { // probleem bij klasse Spel dus public
			this.kleur = kleur;
		}

		@Override
		public int hashCode() {
			return Objects.hash(gebruikersnaam);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Speler other = (Speler) obj;
			return Objects.equals(gebruikersnaam, other.gebruikersnaam);
		}

		@Override
		public String toString() {
			return "Speler [gebruikersnaam=" + gebruikersnaam + ", geboortejaar=" + geboortejaar + "]";
		}
}
//Dit is een commit van Jelle Van Horen
//Dit is een commit van Sverre Lippens
// Dit is een commit van Wout Gheysels
// Dit is een commit van Lars De Wever
//Dit is een commit van Nicky Cobbaert
