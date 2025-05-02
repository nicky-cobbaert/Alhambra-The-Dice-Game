package domein;

import java.util.Comparator;

public class Zetsteen{

	private int positie;
	private ResultatenGebied resultatenGebied;

	public Zetsteen(ResultatenGebied resultatenGebied) {
		this.resultatenGebied = resultatenGebied;
		
	}

	/**
	 * Kleur moet tussen 1-6 liggen. 
	 * Zetsteen positie Y moet tussen 1-8 liggen.
	 * Zetsteen positie X moet tussen 1-3 liggen.
	 * 
	 */

	
	public void plaatsNeer(int positie) throws IllegalArgumentException {

		if (positie < 111 || positie > 683) {
			throw new IllegalArgumentException(
					"De positie moet een kleur tussen [1,6] , verticale positie tussen [1,8] en een horizontale positie tussen [1,3] hebben!");
		}
		if (((positie / 100) % 10) < 1 || ((positie / 100) % 10) > 6) {
			throw new IllegalArgumentException("De kleur moet tussen 1 en 6 liggen!");
		}

		if (((positie / 10) % 10) < 1 || ((positie / 10) % 10) > 8) {
			throw new IllegalArgumentException("De verticale positie moet tussen 1 en 8 liggen!");
		}

		if ((positie % 10) < 1 || (positie % 10) > 3) {
			throw new IllegalArgumentException("De horizontale positie moet tussen 1 en 3 liggen!");
		}

		resultatenGebied.plaatsZetsteenNeer(this,positie);
	}
	
	public void gaVanHetSpelbord() {
		this.positie = 0;
	}
	
	public void setPositie(int positie) {
		this.positie = positie;
	}

	public int getPositie() { // Snel getter toegevoegd voor mijn methode
		return positie;
	}
	
}
