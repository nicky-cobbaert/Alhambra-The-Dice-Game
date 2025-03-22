package domein;

import java.util.List;

public class FicheGebied implements Placeable { // relatie met Fiche nog niet in orde op dcd? NOG NIET AANGEPAST IN DCD
	
	private List<Fiche> gezettefiches; // naam veranderen naar gewoon fiches?

	public FicheGebied() {

	}
	
	public void plaatsFicheNeer(Fiche fiche) {
		if(isPlaatsBaar(fiche.getPositie()) == true) { 
			gezettefiches.add(fiche);
		}
		
	}

	@Override
	public boolean isPlaatsBaar(int positie) {
		return true; // nog aanpassen naargelang uc4
	}

}
