package domein;

import java.util.ArrayList;
import java.util.List;

public class FicheGebied implements Placeable {
	
	private List<Fiche> fiches; // naam veranderen naar gewoon fiches?

	public FicheGebied() {
		fiches = new ArrayList<>();
	}
	
	public void plaatsFicheNeer(Fiche fiche,int positie) {
//		if(isPlaatsBaar(fiche.getPositie()) == true) { 
//			gezettefiches.add(fiche);
//		} else {
//			throw new IllegalArgumentException("Deze positie is onmogelijk!");
//		}

        if (!isPlaatsBaar(positie)) { 
            throw new IllegalArgumentException("Deze positie is onmogelijk! ficheGebied");
        }
        fiche.setPositie(positie);
        fiches.add(fiche);
		
	}
	
	public void haalFicheWeg(Fiche f){
		fiches.remove(f);
		f.setPositie(0);
	}

	@Override
	public boolean isPlaatsBaar(int positie) {
		if(positie < 0 || positie > 6) {
		return false;
		}
		for(Fiche f : fiches) {
			if(f.getPositie() == positie) {
				return false;
				}
		}
		return true;
	}

	public List<Fiche> getGezettefiches() { //methode voor testen
		return fiches;
	}
	

}
