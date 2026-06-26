package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import utils.SpelerKleur;

/**
 * De klasse {@code Speler} stelt een speler voor die al dan niet meedoet met een spel.
 */

public class Speler {
	
	/**
	 * De lijst van alle zetstenen waarover de speler beschikt.
	 */
	private List<Zetsteen> zetstenen;
	
	/**
	 * De lijst van alle gebouwstenen waarover de speler beschikt.
	 */
	private List<Gebouwsteen> gebouwstenen;
	
	/**
	 * Dit is de variabele waarmee we kijken of de juiste leeftijd is om dit spel te spelen.
	 */
	private final static int HUIDIG_JAAR = 2025;
	
	/**
	 * De gebruikersnaam van de speler.
	 */
	private String gebruikersnaam;
	
	/**
	 * Het geboortjejaar van de speler.
	 */
	private int geboortejaar;
	
	/**
	 * Hoeveel keer de speler al gewonnen heeft.
	 */
	private int aantalGewonnen;
	
	/**
	 * Hoeveel keer de speler al gespeeld heeft.
	 */
	private int aantalGespeeld;
	
	/**
	 * De kleur van de speler die hij selecteerd.
	 */
	private SpelerKleur kleur;
	
	/**
	 * De punten van de speler, beginnende op 0.
	 */
	private int punten = 0;
	
	/**
	 * Toont aan of deze speler de startspeler is van de ronde.
	 */
	private boolean isStartSpeler;
	
	/**
	 * Dit maakt een nieuwe speler aan met de opgegeven gebruikersnaam en geboortejaar
	 * 
	 * @param gebruikersnaam is de naam van de speler
	 * @param geboortejaar is het geboortejaar van de speler
	 */
	public Speler(String gebruikersnaam, int geboortejaar) {
		this(gebruikersnaam, geboortejaar, 0, 0);
	}
	
	/**
	 * Dit maakt een nieuwe speler aan met de opgegeven gebruikersnaam, geboortejaar, aantal keer gewonnen en het aantal keer gespeeld.
	 * 
	 * @param gebruikersnaam is de naam van de speler.
	 * @param geboortejaar is het geboortejaar van de speler.
	 * @param aantalGewonnen is het aantal keer dat deze nieuwe speler al gewonnen heeft.
	 * @param aantalGespeeld is het aantal keer dat deze nieuwe speler al een spel gespeeld heeft.
	 */
	public Speler(String gebruikersnaam, int geboortejaar, int aantalGewonnen, int aantalGespeeld) {
		setGebruikersnaam(gebruikersnaam);
		setGeboortejaar(geboortejaar);
		setAantalGewonnen(aantalGewonnen);
		setAantalGespeeld(aantalGespeeld);
		setIsStartSpeler(false);
	}
	
	/**
	 * Dit geeft de gebruikersnaam van de speler terug.
	 * 
	 * @return De gebruikersnaam van de speler.
	 */
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	/**
	 * Hiermee kan je de gebruikersnaam dat is meegegeven controleren. Als deze niet volgens de regels is wordt er een exceptie gegooid
	 * 
	 * @param gebruikersnaam is de gebruikersnaam van de speler
	 */
	private void setGebruikersnaam(String gebruikersnaam) {
		if (gebruikersnaam == null || gebruikersnaam.isEmpty() || gebruikersnaam.isBlank()
				|| gebruikersnaam.length() < 6) {
			throw new IllegalArgumentException("gebruikersnaam.teKort");
		}

		this.gebruikersnaam = gebruikersnaam;
	}

	/**
	 * Dit geeft het geboortejaar van de speler terug.
	 * 
	 * @return Het geboortejaar van de speler.
	 */
	public int getGeboortejaar() {
		return geboortejaar;
	}

	/**
	 * Hiermee kan je het geboortejaar dat is meegegeven controleren. Als deze niet volgens de regels is wordt er een exceptie gegooid.
	 * 
	 * @param geboortejaar is het geboortejaar van de speler
	 */
	private void setGeboortejaar(int geboortejaar) {
		if (geboortejaar + 6 > HUIDIG_JAAR || geboortejaar + 100 < HUIDIG_JAAR) {
			throw new IllegalArgumentException(
					"geboortedatum.ongeldig");
		}
		this.geboortejaar = geboortejaar;
	}

	/**
	 * Dit geeft het aantal gewonnen spelletjes van de speler terug.
	 * 
	 * @return Het aantal gewonnen spelletjes.
	 */
	public int getAantalGewonnen() {
		return aantalGewonnen;
	}

	/**
	 * Hiermee kan je het aantal gewonnen spelletjes dat is meegegeven controleren. Als deze niet volgens de regels is wordt er een exceptie gegooid.
	 * 
	 * @param aantalGewonnen is het aantal gewonnen spelletjes van de speler
	 */
	private void setAantalGewonnen(int aantalGewonnen) {
		if (aantalGewonnen < 0) {
			throw new IllegalArgumentException("Je moet een positief getal (of 0) aantal gewonnen spellen hebben");
		}
		this.aantalGewonnen = aantalGewonnen;
	}

	/**
	 * Dit geeft het aantal gespeelde spelletjes van de speler terug.
	 * 
	 * @return Het aantal gespeelde spelletjes.
	 */
	public int getAantalGespeeld() {
		return aantalGespeeld;
	}
	
	/**
	 * Hiermee kan je het aantal gespeelde spelletjes dat is meegegeven controleren. Als deze niet volgens de regels is wordt er een exceptie gegooid.
	 * 
	 * @param aantalGespeeld is het aantal gespeelde spelletjes van de speler
	 */
	private void setAantalGespeeld(int aantalGespeeld) {
		if (aantalGespeeld < 0) {
			throw new IllegalArgumentException("Je moet een positief getal (of 0) aantal gespeelde spellen hebben");
		}
		this.aantalGespeeld = aantalGespeeld;
	}

	/**
	 * Hiermee kan je het aantal punten van de speler verhogen met het meegegeven aantal punten.
	 * 
	 * @param punten zijn de punten die de speler erbij krijgt.
	 */
	public void voegPuntenToe(int punten) {
		this.punten += punten;
	}

	/**
	 * Dit geeft de huidige aantal punten van de speler terug.
	 * 
	 * @return Het aantal punten van de speler.
	 */
	public int getPunten() {
		return punten;
	}

	/**
	 * Dit geeft alle kleuren uit de enumeratie mee.
	 * 
	 * @return Alle mogelijke spelerkleuren.
	 */
	public static List<SpelerKleur> geefAlleKleuren() {
		List<SpelerKleur> spelerKleuren = new ArrayList<SpelerKleur>();
		for (SpelerKleur kleur : SpelerKleur.values()) {
			spelerKleuren.add(kleur);
		}
		return spelerKleuren;
	}

	/**
	 * Dit geeft de kleur van de speler terug.
	 * 
	 * @return De kleur van de speler
	 */
	public SpelerKleur getKleur() {
		return kleur;
	}
	
	/**
	 * Hiermee kan je de kleur van de speler op de meegegeven kleur zetten.
	 * 
	 * @param kleur is de nieuwe kleur van de speler
	 */
	public void setKleur(SpelerKleur kleur) {
		this.kleur = kleur;
	}

	/**
	 * Dit geeft de alle zetstenen van de speler terug.
	 * 
	 * @return Alle zetstenen van de speler.
	 */
	public List<Zetsteen> getZetstenen() {
		return this.zetstenen;
	}

	/**
	 * Hiermee kan je alle zetstenen van de speler met de meegegeven zetstenen vervangen.
	 * 
	 * @param zetstenenen zijn de nieuwe zetstenen voor de speler.
	 */
	private void setZetstenen(List<Zetsteen> zetstenenen) {
		this.zetstenen = zetstenenen;
	}
	
	/**
	 * Hiermee kan je alle gebouwstenen van de speler met de meegegeven zetstenen vervangen.
	 * 
	 * @param gebouwstenen zijn de nieuwe gebouwstenen voor de speler.
	 */
	private void setGebouwstenen(List<Gebouwsteen> gebouwstenen) {
		this.gebouwstenen = gebouwstenen;
	}

	/**
	 * Hier worden de zetstenen aangemaakt, in de lijst van zetstenen gestoken, toegevoegd aan het {@link ResultatenGebied} en uiteindelijk wordt de lijst van zetstenen met behulp van de setter aan de speler meegegeven.
	 * 
	 * @param aantalZetstenen is het aantal zetstenen dat er aangemaakt moet worden.
	 * @param resultatenGebied is het resultaatgebied waar het aan toegevoegd wordt.
	 */
	public void maakZetstenenAan(int aantalZetstenen, ResultatenGebied resultatenGebied) {
		List<Zetsteen> zetstenen = new ArrayList<Zetsteen>();

		for (int i = 0; i < aantalZetstenen; i++) {
			Zetsteen zetsteen = new Zetsteen(resultatenGebied);
			zetstenen.add(zetsteen);
		}
		setZetstenen(zetstenen);
	}
	
	/**
	 * Hiermee kan je een zetsteen plaatsen op een meegegeven positie, er wordt ook gecontroleerd dat deze positie niet 0 is.
	 * 
	 * @param positie is de nieuwe positie van de zetsteen.
	 */
	public void plaatsEenZetsteenNeer(int positie) {
		for(Zetsteen z:zetstenen) {
			if(z.getPositie() == 0) {
				z.plaatsNeer(positie);
				break;
			}
		}
	}

	/**
	 * Hier wordt de gebouwsteen aangemaakt voor elk {@link GebouwpuntenGebied}. Deze wordt in een lijst gestoken en met behulp van de setter meegegeven aan de speler
	 * 
	 * @param gebouwpuntenGebied
	 */
	public void maakGebouwstenenAan(GebouwpuntenGebied gebouwpuntenGebied) {
		List<Gebouwsteen> gebouwstenen = new ArrayList<Gebouwsteen>();

		for (int i = 1; i <= 6; i++) {
			Gebouwsteen gebouwsteen = new Gebouwsteen(gebouwpuntenGebied);
			gebouwsteen.plaatsNeer(i*100);
			gebouwstenen.add(gebouwsteen);
		}
		setGebouwstenen(gebouwstenen);
	}
	
	/**
	 * Hiermee worden de gebouwstenen verplaatst op het gebied dat wordt meegegeven met positie en hoeveel hij verplaatst moet worden met verplaatsing.
	 * 
	 * @param positie is in welk gebouwpuntenGebied deze gebouwsteen staat. 
	 * @param verplaatsing is met hoeveel deze gebouwsteen verplaatst zal worden.
	 */
	public void verplaatsGebouwsteen(int positie, int verplaatsing) {
		for(Gebouwsteen g :gebouwstenen) {
			if (g.getPositie()/100 == positie) {
				g.verplaats(verplaatsing);
				break;
			}
		}
	}
	
	/**
	 * Hier worden de zetstenen na een ronde van het bord verwijderd.
	 */
	public void cleanUpNaRonde() {
		for(Zetsteen z:zetstenen) {
			z.gaVanHetSpelbord();
		}
	}

	/**
	 * Hiermee kan je de punten van de speler op het meegegeven aantal punten zetten. 
	 * 
	 * @param punten zijn de punten die de speler heeft
	 */
	private void setPunten(int punten) {
		this.punten = punten;
	}
	
	/**
	 * Dit zijn alle gebouwstenen van de speler.
	 * 
	 * @return Alle gebouwstenen van de speler.
	 */
	public List<Gebouwsteen> getGebouwstenen() {
		return gebouwstenen;
	}
	
	/**
	 * Dit toont aan of deze speler de startspeler van de ronde is.
	 * 
	 * @return Of deze speler de startspeler van de ronde is.
	 */
	public boolean getIsStartSpeler() {
		return isStartSpeler;
	}
	
	/**
	 * Hiermee wordt er bepaald of je startspeler bent of niet. Het zal ingesteld worden op de meegegeven waarde.
	 * 
	 * @param isStartSpeler is of deze speler startspeler is of niet.
	 */
	public void setIsStartSpeler(boolean isStartSpeler) {
		this.isStartSpeler = isStartSpeler;
	}

	/**
	 * Dit geeft de hashcode van deze integer.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(gebruikersnaam);
	}

	/**
	 * Hiermee bekijken we of de gebruikersnaam overeen komt met een andere gebruikersnaam.
	 */
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

	/**
	 * Hiermee wordt deze speler zijn gegevens mooi in 1 string geplaatst.
	 */
	@Override
	public String toString() {
		return "Speler [gebruikersnaam=" + gebruikersnaam + ", geboortejaar=" + geboortejaar + "]";
	}
}
