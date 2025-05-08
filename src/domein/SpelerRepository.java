package domein;

import java.util.ArrayList;
import java.util.List;

import exceptions.GebruikersnaamInGebruikException;
import persistentie.SpelerMapper;

/**
 * De klasse {@code SpelerRepository} beheert een verzameling van {@link Speler}-objecten.
 * <p>
 * Het dient als tussenlaag tussen het domein en de persistente opslag, en staat in voor het ophalen,
 * toevoegen en bijwerken van spelersgegevens.
 */

public class SpelerRepository {

	/**
	 * Lijst van spelers die beheerd worden door deze repository
	 */
	private final List<Speler> spelers;
	
	/**
	 * De mapper die instaat voor communicatie met de databron
	 */

	private final SpelerMapper mapper;
	

    /**
     * Maakt een nieuwe instantie van {@code SpelerRepository} aan en laadt alle bestaande spelers.
     */
	public SpelerRepository() {
		mapper = new SpelerMapper();
		spelers = this.mapper.geefAlleSpelers();
	}
	
    /**
     * Voegt een nieuwe speler toe aan de databron.
     *
     * @param speler de toe te voegen speler
     * @throws GebruikersnaamInGebruikException als de gebruikersnaam al in gebruik is
     */

	public void voegToe(Speler speler) {
		if (bestaatSpeler(speler.getGebruikersnaam())) {
			throw new GebruikersnaamInGebruikException();
		}

		mapper.voegToe(speler);
	}
	
    /**
     * Controleert of er al een speler bestaat met de opgegeven gebruikersnaam.
     *
     * @param gebruikersnaam de gebruikersnaam om op te zoeken
     * @return {@code true} als de speler bestaat, anders {@code false}
     */

	private boolean bestaatSpeler(String gebruikersnaam) {
		return mapper.geefSpeler(gebruikersnaam) != null;
	}
	
    /**
     * Geeft een lijst terug met alle spelers uit de databron.
     *
     * @return een lijst van alle spelers
     */

	public List<Speler> geefAlleSpelers() {


		return mapper.geefAlleSpelers();
	}
	
    /**
     * Verhoogt het aantal gewonnen spellen van de speler met de opgegeven gebruikersnaam.
     *
     * @param gebruikersnaam de gebruikersnaam van de speler
     */

	public void updateGewonnen(String gebruikersnaam) {
		mapper.updateGewonnen(gebruikersnaam);
	}
	
    /**
     * Verhoogt het aantal gespeelde spellen van de speler met de opgegeven gebruikersnaam.
     *
     * @param gebruikersnaam de gebruikersnaam van de speler
     */

	public void updateGespeeld(String gebruikersnaam) {
		mapper.updateGespeeld(gebruikersnaam);
	}
	
    /**
     * Start de offline modus waarin geen gegevens worden opgehaald of opgeslagen via de databron.
     */

	public void startOfflineModus() {
		mapper.startOfflineModus();
	}
	
	/**
	 * Geeft een lijst terug van maximaal vijf spelers die het hoogst scoren in het leaderboard.
	 * <p>
	 * De ranking is gebaseerd op prestaties zoals het aantal gewonnen spellen.
	 *
	 * @return een lijst van de top 5 spelers gesorteerd volgens het leaderboardcriterium
	 */
	public List<Speler> geefLeaderboard(){
		return mapper.geefLeaderboard();
	}

}
