package domein;

import java.util.ArrayList;
import java.util.List;

import exceptions.GebruikersnaamInGebruikException;
import persistentie.SpelerMapper;

public class SpelerRepository {

	private final List<Speler> spelers;

	private final SpelerMapper mapper;

	public SpelerRepository() {
		mapper = new SpelerMapper();
		spelers = new ArrayList<Speler>();
	}

	public void voegToe(Speler speler) {
		if (bestaatSpeler(speler.getGebruikersnaam())) {
			throw new GebruikersnaamInGebruikException();
		}

		mapper.voegToe(speler);
	}

	private boolean bestaatSpeler(String gebruikersnaam) {
		return mapper.geefSpeler(gebruikersnaam) != null;
	}

	public List<Speler> geefAlleSpelers() {

		List<Speler> alleSpelers = mapper.geefAlleSpelers();

		return alleSpelers;
	}

	public void updateGewonnen(String gebruikersnaam) {
		mapper.updateGewonnen(gebruikersnaam);
	}

	public void updateGespeeld(String gebruikersnaam) {
		mapper.updateGespeeld(gebruikersnaam);
	}

	public void startOfflineModus() {
		mapper.startOfflineModus();
	}

}
