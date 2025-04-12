package domein;

import java.util.ArrayList;
import java.util.List;

public class FicheGebied implements Placeable {
	
	private List<Fiche> gezettefiches; // naam veranderen naar gewoon fiches?

	public FicheGebied() {
		gezettefiches = new ArrayList<>();
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

        gezettefiches.add(fiche);
		
	}

	@Override
	public boolean isPlaatsBaar(int positie) {
		if(positie >= 0 && positie <= 6) {
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
//		Fiche f=null;
//		
//		for (Fiche gezet : gezettefiches) {
//			if (gezet.getPositie()==positie) {
//				f=gezet;
//			}
//		}
//		if (f instanceof Bonusfiche bonus) {
//			return bonus.getWaarde(); 
//		} else {
//			return 0; //startspelerfiche!
//		}
		
		for (Fiche fich : gezettefiches) {
			if (fich.getPositie()==positie) {
				if (fich instanceof Bonusfiche bonus) {
					return bonus.getWaarde(); 
				} else {
					return 0; //startspelerfiche!
				}
			}
		}
		return 0;
	}

}
