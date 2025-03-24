package domein;

import java.util.List;

public class GebouwpuntenGebied implements Placeable{
	
	
	private List<Gebouwsteen> gezetteGebouwstenen;

	public GebouwpuntenGebied() {
		
	}

	public void plaatsGebouwsteenNeer(Gebouwsteen gebouwsteen) {
		  int positie = gebouwsteen.getPositie();

	        if (!isPlaatsBaar(positie)) { 
	            throw new IllegalArgumentException("Deze positie is onmogelijk!");
	        }

	        for (Gebouwsteen g : gezetteGebouwstenen) {
	            if (g.getPositie() == positie) {
	                throw new IllegalArgumentException("Er is al een gebouwsteen op deze positie!");
	            }
	        }

	        gezetteGebouwstenen.add(gebouwsteen);
		
	}
	
	@Override
	public boolean isPlaatsBaar(int positie) {
		int honderdtal = positie / 100;  
	    int rest = positie % 100;    
	    
	    if(honderdtal >= 1 && honderdtal <= 6 && rest >= 1 && rest <= 12) {
	    	return true;  //nog aanpassen naargelang uc4
	    } else {
	    	return false;
	    }
		
	}

	public List<Gebouwsteen> getGebouwstenen() { //getter voor test
		return gezetteGebouwstenen;
	}

}