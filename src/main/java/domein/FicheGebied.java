package domein;

import java.util.ArrayList;
import java.util.List;

/**
 * De klasse {@code FicheGebied} stelt een gebied voor waarop {@code Fiche}-objecten geplaatst kunnen worden.
 * Er kunnen maximaal 6 posities zijn (1 t.e.m. 6), waarbij elke positie slechts één fiche mag bevatten.
 * 
 * <p>Deze klasse biedt methoden om fiches te plaatsen, te verwijderen en te controleren of een positie vrij is.</p>
 */
public class FicheGebied implements Placeable {
	
	/** De lijst van geplaatste fiches in dit gebied. */
	private List<Fiche> fiches; 

	/**
     * Maakt een nieuw {@code FicheGebied} zonder fiches.
     */
	public FicheGebied() {
		fiches = new ArrayList<>();
	}
	
	
	
	 /**
     * Plaatst een fiche op een bepaalde positie in het gebied.
     * 
     * @param fiche  De fiche die geplaatst moet worden.
     * @param positie De positie waarop de fiche geplaatst moet worden (tussen 1 en 6).
     * @throws IllegalArgumentException als de positie buiten het bereik valt of al bezet is.
     */
	public void plaatsFicheNeer(Fiche fiche,int positie) {
        if (!isPlaatsBaar(positie)) { 
            throw new IllegalArgumentException("Deze positie is onmogelijk! ficheGebied");
        }
        fiche.setPositie(positie);
        fiches.add(fiche);
		
	}
	
	
	 /**
     * Verwijdert een fiche uit het gebied en reset haar positie naar 0.
     * 
     * @param f De fiche die verwijderd moet worden.
     */
	public void haalFicheWeg(Fiche f){
		fiches.remove(f);
		f.setPositie(0);
	}
	
	 /**
     * Controleert of een fiche geplaatst kan worden op een bepaalde positie.
     * 
     * @param positie De positie om te controleren.
     * @return {@code true} als de positie geldig is (tussen 1 en 6) en nog niet bezet is; anders {@code false}.
     */
	@Override
	public boolean isPlaatsBaar(int positie) {
		if(positie <= 0 || positie > 6) {
		return false;
		}
		for(Fiche f : fiches) {
			if(f.getPositie() == positie) {
				return false;
				}
		}
		return true;
	}
	
	 /**
     * Geeft de lijst van alle geplaatste fiches terug.
     * @return Een lijst van geplaatste {@code Fiche}-objecten.
     */
	public List<Fiche> getGezettefiches() { 
		return fiches;
	}
	

}
