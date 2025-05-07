package domein;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * De klasse {@code GebouwpuntenGebied} beheert een verzameling van {@code Gebouwsteen}-objecten
 * die op posities in een spelbord geplaatst kunnen worden. Elke steen kan verplaatst worden
 * met een volgordemechanisme dat bepaalt welke steen "voor" ligt.
 * 
 * <p>Posities worden voorgesteld als getallen waarbij het honderdtal de kleur representeert
 * (1 t.e.m. 6) en de rest (0 t.e.m. 9) de specifieke plaats binnen die kleur.</p>
 */
public class GebouwpuntenGebied implements Placeable{
	
	 /** De lijst van geplaatste gebouwstenen. */
	private List<Gebouwsteen> gebouwstenen;

	
	/**
     * Maakt een nieuw {@code GebouwpuntenGebied} zonder gebouwstenen.
     */
	public GebouwpuntenGebied() {
		gebouwstenen = new ArrayList<Gebouwsteen>();
	}
	

	
	   /**
     * Plaatst een {@code Gebouwsteen} op een specifieke positie.
     * Indien de positie al bezet is, wordt de volgorde van de steen aangepast zodat
     * deze correct geplaatst wordt ten opzichte van bestaande stenen.
     * 
     * @param gebouwsteen De steen die geplaatst moet worden.
     * @param positie De gewenste positie (bijvoorbeeld 203 voor kleur 2, positie 3).
     * @throws IllegalArgumentException als de positie ongeldig of bezet is.
     */
	public void plaatsGebouwsteenNeer(Gebouwsteen gebouwsteen,int positie) {

	        if (!isPlaatsBaar(positie)) { 
	            throw new IllegalArgumentException("Deze positie is onmogelijk! GebouwpuntenGebied");
	        }
	        gebouwsteen.setVolgorde(1);
	        for (Gebouwsteen g : gebouwstenen) {
	            if (g.getPositie() == positie) {
	            	if (g.getVolgorde() >= gebouwsteen.getVolgorde()) {
	            		gebouwsteen.plaatsNaast(g.getVolgorde());
	            	}
	            }
	        }
	        gebouwsteen.setPositie(positie);
	        this.gebouwstenen.add(gebouwsteen);
	}
	
	   /**
     * Verplaatst een {@code Gebouwsteen} met een opgegeven afstand, mits de doelpositie geldig en vrij is.
     * De volgorde van andere stenen op de oorspronkelijke en nieuwe positie wordt bijgewerkt.
     * 
     * @param gebouwsteen De te verplaatsen steen.
     * @param verplaatsing De afstand (1 t.e.m. 3) waarmee de steen moet verschuiven.
     * @throws IllegalArgumentException als de verplaatsing ongeldig is of als de doelpositie niet beschikbaar is.
     */
	public void verplaatsGebouwsteen(Gebouwsteen gebouwsteen, int verplaatsing) {
		int huidigePositie = gebouwsteen.getPositie();
		int huidigeVolgorde = gebouwsteen.getVolgorde();
		if(verplaatsing <= 0||verplaatsing > 3) {
			throw new IllegalArgumentException("de verplaatsing is te klein (<=0) of te groot (>3)");
		}
		if(!isPlaatsBaar(huidigePositie + verplaatsing)) {
			throw new IllegalArgumentException("deze positie is niet bereikbaar");
		}
		gebouwsteen.setPositie(huidigePositie + verplaatsing);
		gebouwsteen.setVolgorde(1);
		for(Gebouwsteen g: gebouwstenen) {
			if(g.getPositie() == huidigePositie &&g.getVolgorde() >= huidigeVolgorde) {
				g.setVolgorde(g.getVolgorde() - 1);
				} else {
					if(g.getPositie() == huidigePositie + verplaatsing && g.getVolgorde() >= gebouwsteen.getVolgorde()&& !(g == gebouwsteen)) {
						gebouwsteen.plaatsNaast(g.getVolgorde());
				}
			}
		}
	}
	
	
	 /**
     * Controleert of een bepaalde positie geldig is binnen het spelbord.
     * Posities zijn alleen geldig als ze in het bereik 100-699 liggen (kleur 1-6, plaats 0-9).
     * 
     * @param positie De te controleren positie.
     * @return {@code true} als de positie geldig is, anders {@code false}.
     */
	@Override
	public boolean isPlaatsBaar(int positie) {
		int honderdtal = positie / 100;  
	    int rest = positie % 100;    
	    
	    if(honderdtal >= 1 && honderdtal <= 6 && rest >= 0 && rest <= 9) {
	    	return true;
	    } else {
	    	return false;
	    }
		
	}

	  /**
     * Geeft alle geplaatste gebouwstenen terug.
     * 
     * @return Een lijst van {@code Gebouwsteen}-objecten.
     */
	public List<Gebouwsteen> getGebouwstenen() { 
		return gebouwstenen;
	}


	  /**
     * Geeft de voorste gebouwstenen voor een bepaalde kleur en ronde.
     * Alleen stenen die niet op een 0-positie staan worden meegenomen.
     * 
     * @param ronde Het maximumaantal stenen dat moet worden teruggegeven.
     * @param kleur De kleur (1 t.e.m. 6) waarvan stenen moeten worden gefilterd.
     * @return Een gesorteerde lijst van {@code Gebouwsteen}-objecten.
     */
	public List<Gebouwsteen> getVoorsteGebouwstenen(int ronde,int kleur) {
		return  gebouwstenen.stream()
				.filter(e -> e.getPositie() %100!= 0)
				.filter(e -> e.getPositie()/100 == kleur)
				.sorted(Comparator.comparing(Gebouwsteen::getPositie).reversed().thenComparing(Gebouwsteen::getVolgorde))
				.limit(ronde)
				.toList();

				
			
	}
	
}