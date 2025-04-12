package domein;

import java.util.List;

public class FicheGebied implements Placeable {
	
	private List<Fiche> gezettefiches; // naam veranderen naar gewoon fiches?

	public FicheGebied() {

	}
	
	public void plaatsFicheNeer(Fiche fiche) {
//		if(isPlaatsBaar(fiche.getPositie()) == true) { 
//			gezettefiches.add(fiche);
//		} else {
//			throw new IllegalArgumentException("Deze positie is onmogelijk!");
//		}
		
		
		int positie = fiche.getPositie();

        if (!isPlaatsBaar(positie)) { 
            throw new IllegalArgumentException("Deze positie is onmogelijk!");
        }

        for (Fiche f : gezettefiches) {
            if (f.getPositie() == positie) {
                throw new IllegalArgumentException("Er is al een bonusfiche op deze positie!");
            }
        }

        gezettefiches.add(fiche);
		
	}

	@Override
	public boolean isPlaatsBaar(int positie) {
		if(positie >= 1 && positie <= 6) {
			return true; 
		}
		else {
			return false;
		}
	}

	public List<Fiche> getGezettefiches() { //methode voor testen
		return gezettefiches;
	}
	
	public int geefWaardeVanPositie(int positie) {
		if (gezettefiches.get(positie) instanceof Bonusfiche bonus) {
			return bonus.getWaarde();
		} else {
			return 0; //0 = startspelerfiche
		}
	}

}
