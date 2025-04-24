package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import utils.SpelerKleur;

public class Speler {
	private String gebruikersnaam;
	private int geboortejaar;
	private int aantalGewonnen, aantalGespeeld;
	private SpelerKleur kleur;
	private List<Zetsteen> zetstenen;
	private List<Gebouwsteen> gebouwstenen;
	private int punten;
	private boolean isStartSpeler;

	/*
	 * private List<Gebouwsteen> gebouwstenen
	 */

	private final static int HUIDIG_JAAR = 2025;

	public Speler(String gebruikersnaam, int geboortejaar) {
		this(gebruikersnaam, geboortejaar, 0, 0);
	}

	public Speler(String gebruikersnaam, int geboortejaar, int aantalGewonnen, int aantalGespeeld) {
		setGebruikersnaam(gebruikersnaam);
		setGeboortejaar(geboortejaar);
		setAantalGewonnen(aantalGewonnen);
		setAantalGespeeld(aantalGespeeld);
		setIsStartSpeler(false);
	}

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	private void setGebruikersnaam(String gebruikersnaam) {
		if (gebruikersnaam == null || gebruikersnaam.isEmpty() || gebruikersnaam.isBlank()
				|| gebruikersnaam.length() < 6) {
			throw new IllegalArgumentException("Ongeldige gebruikersnaam, uw gebruikersnaam moet minstens 6 karakters hebben!");
		}

		this.gebruikersnaam = gebruikersnaam;
	}

	public int getGeboortejaar() {
		return geboortejaar;
	}

	private void setGeboortejaar(int geboortejaar) {
		if (geboortejaar + 6 > HUIDIG_JAAR || geboortejaar + 100 < HUIDIG_JAAR) {
			throw new IllegalArgumentException(
					"Je moet tussen de leeftijd van 6 en 100 zijn om dit spel te mogen spelen");
		}
		this.geboortejaar = geboortejaar;
	}

	public int getAantalGewonnen() {
		return aantalGewonnen;
	}

	private void setAantalGewonnen(int aantalGewonnen) {
		if (aantalGewonnen < 0) {
			throw new IllegalArgumentException("Je moet een positief getal (of 0) aantal gewonnen spellen hebben");
		}
		this.aantalGewonnen = aantalGewonnen;
	}

	public int getAantalGespeeld() {
		return aantalGespeeld;
	}

	public void voegPuntenToe(int punten) {
		this.punten += punten;
	}

	public int getPunten() {
		return punten;
	}

	private void setAantalGespeeld(int aantalGespeeld) {
		if (aantalGespeeld < 0) {
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

	public void setKleur(SpelerKleur kleur) {
		this.kleur = kleur;
	}

	public List<Zetsteen> getZetstenen() {
		return this.zetstenen;
	}

	private void setZetstenen(List<Zetsteen> zetstenenen) {
		this.zetstenen = zetstenenen;
	}
	private void setGebouwstenen(List<Gebouwsteen> gebouwstenen) {
		this.gebouwstenen = gebouwstenen;
	}

	public void maakZetstenenAan(int aantalZetstenen, ResultatenGebied resultatenGebied) {
		List<Zetsteen> zetstenen = new ArrayList<Zetsteen>();

		for (int i = 0; i < aantalZetstenen; i++) {
			Zetsteen zetsteen = new Zetsteen(resultatenGebied);
			zetstenen.add(zetsteen);
		}
		setZetstenen(zetstenen);
	}
	public void plaatsEenZetsteenNeer(int positie) {
		for(Zetsteen z:zetstenen) {
			if(z.getPositie() == 0) {
				z.plaatsNeer(positie);
				break;
			}
		}
	}

	public void maakGebouwstenenAan(GebouwpuntenGebied gebouwpuntenGebied) {
		List<Gebouwsteen> gebouwstenen = new ArrayList<Gebouwsteen>();

		for (int i = 1; i <= 6; i++) {
			Gebouwsteen gebouwsteen = new Gebouwsteen(gebouwpuntenGebied);
			gebouwsteen.plaatsNeer(i*100);
			gebouwstenen.add(gebouwsteen);
		}
		setGebouwstenen(gebouwstenen);
	}
	
	public void verplaatsGebouwsteen(int positie, int verplaatsing) {
		for(Gebouwsteen g :gebouwstenen) {
			if (g.getPositie()/100 == positie) {
				g.verplaats(verplaatsing);
			}
		}
	}

	public void clearAttributenNaSpel() {
		setKleur(null);
		setZetstenen(null);
		setGebouwstenen(null);
		setPunten(0);
		setIsStartSpeler(false);
		
	}
	private void setPunten(int punten) {
		this.punten = punten;
	}
	public List<Gebouwsteen> getGebouwstenen() {
		return gebouwstenen;
	}
	public boolean getIsStartSpeler() {
		return isStartSpeler;
	}
	public void setIsStartSpeler(boolean isStartSpeler) {
		this.isStartSpeler = isStartSpeler;
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
