package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import utils.SpelerKleur;

public class Speler 
{
    private String gebruikersnaam;
    private int geboortejaar;
    private int aantalGewonnen, aantalGespeeld;
    private SpelerKleur kleur;
    private List<Zetsteen> zetstenen;

    /* 
    private List<Gebouwsteen> gebouwstenen
    */
    
	private final static int HUIDIG_JAAR = 2025;   

    public Speler(String gebruikersnaam, int  geboortejaar) 
    {
    	this(gebruikersnaam,geboortejaar,0,0);
    }
    
    public Speler(String gebruikersnaam,int  geboortejaar, int aantalGewonnen, int aantalGespeeld) 
    {
    	setGebruikersnaam(gebruikersnaam);
    	setGeboortejaar(geboortejaar);
    	setAantalGewonnen(aantalGewonnen);
    	setAantalGespeeld(aantalGespeeld);
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
	
	public static List<SpelerKleur> geefAlleKleuren() {
		List<SpelerKleur> spelerKleuren = new ArrayList<SpelerKleur>();
		for (SpelerKleur kleur : SpelerKleur.values()) {
			spelerKleuren.add(kleur);
		}
		return spelerKleuren;
	}
	
	public SpelerKleur getKleur() {
			return kleur;
		}

	public void setKleur(SpelerKleur kleur) { // probleem bij klasse Spel dus public
			this.kleur = kleur;
		}
	
	public List<Zetsteen> getZetstenen(){
		return this.zetstenen;
	}
	
	private void setZetstenen(List<Zetsteen> zetstenenen) {
		this.zetstenen = zetstenenen;
	}
	
	public void maakZetstenenAan(int aantalZetstenen) {
		List<Zetsteen> zetstenen = new ArrayList<Zetsteen>();
		
		for (int i = 0; i < aantalZetstenen;i++) {
			Zetsteen zetsteen = new Zetsteen();
			zetstenen.add(zetsteen);
		}
		setZetstenen(zetstenen);
	}
	
	public void clearAttributenNaSpel() {
		setKleur(null);
		setZetstenen(null);
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
