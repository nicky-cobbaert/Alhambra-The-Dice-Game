package domein;

import java.util.List;

public class GebouwpuntenGebied implements Placeable{ //relatie met Gebouwsteen nog niet in orde op dcd? NOG EENS HERBEKIJKEN NIET AANGEPAST
	
	
	private List<Gebouwsteen> gebouwstenen;

	public GebouwpuntenGebied() {
		
	}

	public void plaatsGebouwsteen(Gebouwsteen gebouwsteen) {
		if(isPlaatsBaar(gebouwsteen.getPositie()) == true) { 
			gebouwstenen.add(gebouwsteen);
		} else {
			throw new IllegalArgumentException("Deze positie is onmogelijk!");
		}
		
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

}