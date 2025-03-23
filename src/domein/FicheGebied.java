package domein;

import java.util.List;

public class FicheGebied implements Placeable { // relatie met Fiche nog niet in orde op dcd? NOG NIET AANGEPAST IN DCD
	
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
                throw new IllegalArgumentException("Er is al een gebouwsteen op deze positie!");
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

}
