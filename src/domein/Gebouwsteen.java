package domein;

public class Gebouwsteen {

	private int positie;
	private int volgorde;
	private GebouwpuntenGebied gebouwpuntGebied;
	
	public Gebouwsteen(GebouwpuntenGebied gebouwpuntGebied) {
		this.gebouwpuntGebied = gebouwpuntGebied;
	}
	
	public int getPositie(){ //toegevoegd voor mijn methode  
		return positie;
	}
	
	public void setPositie(int positie) {
		this.positie = positie;
	}
	
	
	public void plaatsNeer(int positie) {
		gebouwpuntGebied.plaatsGebouwsteenNeer(this,positie);
	}
	
	public void verplaats(int verplaatsing) {
		if(verplaatsing <= 0||verplaatsing > 3) {
			throw new IllegalArgumentException("de verplaatsing is te klein (<=0) of te groot (>3)");
		}
		gebouwpuntGebied.verplaatsGebouwsteen(this, verplaatsing);
	}
	public int getVolgorde() {
		return this.volgorde;
	}
	public void setVolgorde(int volgorde) {
		this.volgorde = volgorde;
	}
	public void plaatsNaast(int volgordeAndere) {
		setVolgorde(volgordeAndere + 1);
	}
}
