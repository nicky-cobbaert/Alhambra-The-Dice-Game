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
		
		for (Fiche fich : fiches) {
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
