package domein;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Klasse die het resultatengebied van het spel voorstelt.
 * 
 * In dit gebied kunnen {@link Zetsteen} objecten geplaatst worden op posities
 * die voldoen aan specifieke geldigheidsvoorwaarden. Elke positie is een
 * getal van drie cijfers: kleur (1–6), rij (1–8), kolom (1–3).
 
 */
public class ResultatenGebied implements Placeable{ 

	
	 /** De lijst van geplaatste zetstenen. */
	private List<Zetsteen> zetstenen; 

	
	/**
	 * Maakt een nieuw, leeg resultatengebied aan.
	 */
	public ResultatenGebied() {
		zetstenen = new ArrayList<Zetsteen>();
	}

	
	  /**
     * Plaatst een zetsteen op de opgegeven positie in het gebied.
     * 
     * @param zetsteen De zetsteen die geplaatst moet worden.
     * @param positie  De doelpositie, bestaande uit drie cijfers (kleur-rij-kolom).
     * @throws IllegalArgumentException als de positie ongeldig is of al bezet is.
     */
	public void plaatsZetsteenNeer(Zetsteen zetsteen,int positie) {	

        if (!isPlaatsBaar(positie)) { 
            throw new IllegalArgumentException("Deze positie is onmogelijk!");
        }

        for (Zetsteen z : zetstenen) {
            if (z.getPositie() == positie) {
                throw new IllegalArgumentException("Er is al een zetsteen op deze positie!");
            }
        }
        
        zetsteen.setPositie(positie);
        zetstenen.add(zetsteen);
		
	}
	

    /**
     * Verwijdert alle zetstenen uit het gebied en van het spelbord.
     */
	public void maakHetGebiedLeeg() {
		for(Zetsteen z: zetstenen) {
			z.gaVanHetSpelbord();
		}
		this.zetstenen = new ArrayList<Zetsteen>();
	}

    /**
     * Controleert of een positie geldig is binnen het resultatengebied.
     * <p>
     * Een geldige positie voldoet aan:
     * <ul>
     *   <li>Kleur (honderdtal): 1–6</li>
     *   <li>Rij (tiental): 1–8</li>
     *   <li>Kolom (eenheid): 1–3</li>
     * </ul>
     * 
     * @param positie De positie die gecontroleerd moet worden.
     * @return {@code true} als de positie geldig is, anders {@code false}.
     */
	@Override
	public boolean isPlaatsBaar(int positie) {
		int honderdtal = positie / 100;        
	    int tiental = (positie / 10) % 10;    
	    int eenheid = positie % 10;
	    
	    
	    if(honderdtal >= 1 && honderdtal <= 6 && tiental >= 1 && tiental <= 8 && eenheid >= 1 && eenheid <= 3) {
	    	return true; 
	    } else {
	    	return false;
	    }
		
	}

	
	/**
     * Geeft een lijst van alle geplaatste zetstenen.
     * 
     * @return Een lijst met alle {@link Zetsteen} objecten in dit gebied.
     */
	public List<Zetsteen> getZetstenen() { 
		return zetstenen;
	}
	
    /**
     * Berekent een sorteerwaarde voor een kolompositie.
     * <p>
     * Kolom 1 krijgt voorrang op 2 en 3.
     * 
     * @param getal De kolomweergave van een positie (1–3).
     * @return Een getal dat prioriteit aangeeft (3 → hoogste).
     */
	private int hulp(int getal) {
		switch(getal) {
		case 1 -> {return 3;}
		case 2 -> {return 2;}
		case 3 -> {return 1;}
		default -> {return 0;}
		}
	}
	
	
	   /**
     * Geeft de twee voorste zetstenen van een bepaalde kleur.
     * <p>
     * De sortering gebeurt eerst op rij (tiental) in aflopende volgorde,
     * en vervolgens op kolom met aangepaste voorrang (via {@code hulp()}).
     * 
     * @param positieKleur De kleur (1–6) waarvoor de voorste zetstenen gezocht worden.
     * @return Een lijst van maximaal twee {@link Zetsteen} objecten.
     */
	public List<Zetsteen> getVoorsteZetstenen(int positieKleur) {
		List<Zetsteen> voorsteZetstenen = zetstenen.stream().
				filter(e -> e.getPositie()/100 == positieKleur).
				sorted((z2,z1) -> Integer.compare(z1.getPositie()%100/10*10+hulp(z1.getPositie()%10), z2.getPositie()%100/10*10+hulp(z2.getPositie()%10)))
				.limit(2)
				.toList();
//		for(Zetsteen z:zetstenen) {
//			int currPositie = z.getPositie();
//			if(currPositie/100 == positieKleur) {
//				if(voorsteZetstenen.size() == 2) {
//					for(int index = 0;index < 2;index ++) {
//						if(currPositie/ 10 % 10 > voorsteZetstenen.get(index).getPositie()/ 10 % 10) {
//							voorsteZetstenen.add(index,z);
//							voorsteZetstenen.removeLast();
//							break;
//						}
//						if(currPositie/ 10 % 10 == voorsteZetstenen.get(index).getPositie()/ 10 % 10 && currPositie % 10 < voorsteZetstenen.get(index).getPositie() % 10) {
//							voorsteZetstenen.add(index,z);
//							voorsteZetstenen.removeLast();
//							break;
//						}
//					}
//				}
//				
//				if(voorsteZetstenen.size() == 1) {
//					if(currPositie/ 10 % 10 > voorsteZetstenen.get(0).getPositie()/ 10 % 10) {
//						voorsteZetstenen.addFirst(z);
//					}else {
//						if(currPositie/ 10 % 10 == voorsteZetstenen.get(0).getPositie()/ 10 % 10 && currPositie % 10 < voorsteZetstenen.get(0).getPositie() % 10) {
//							voorsteZetstenen.addFirst(z);
//						}else {
//							voorsteZetstenen.addLast(z);
//						}
//					}
//				}
//				if(voorsteZetstenen.size() == 0) {
//					voorsteZetstenen.addFirst(z);
//				}
//				
//			}
//			
//		}
		return voorsteZetstenen;
	}

	

}
