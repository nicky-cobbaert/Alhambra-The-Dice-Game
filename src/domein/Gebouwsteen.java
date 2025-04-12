package domein;

public class Gebouwsteen {

	private int positie;
	private int volgorde;
	private GebouwpuntenGebied gebouwpuntGebied;
	
	public Gebouwsteen(GebouwpuntenGebied gebouwpuntenGebied) {
		this.gebouwpuntGebied = gebouwpuntenGebied;
	}
	
	public int getPositie(){ //toegevoegd voor mijn methode  
		return positie;
	}
	
	
	public void plaatsNeer(int positie) {
		this.positie = positie;
		this.volgorde = 1;
		gebouwpuntGebied.plaatsGebouwsteenNeer(this);
	}
	public int getVolgorde() {
		return this.volgorde;
	}
	public void plaatsNaast(int volgordeAndere) {
		this.volgorde = 1 + volgordeAndere; 
	}
}
