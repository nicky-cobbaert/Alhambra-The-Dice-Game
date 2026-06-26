package domein;


/**
 * Interface die aangeeft dat een object kan controleren of een bepaalde positie plaatsbaar is.
 * <p>
 * Dit wordt gebruikt in spelcomponenten waarin objecten zoals zetstenen enkel op geldige posities mogen worden geplaatst.
 * 
 * @author 
 */
public interface Placeable {

	 /**
     * Controleert of een gegeven positie geldig is om iets te plaatsen.
     * 
     * @param positie De te controleren positie (bijv. een numerieke codering zoals kleur-rij-kolom).
     * @return {@code true} als de positie geldig is, anders {@code false}.
     */
	public boolean isPlaatsBaar(int positie);

}
