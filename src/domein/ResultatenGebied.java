package domein;

import java.util.List;

public class ResultatenGebied implements Placeable{ // relatie met Zetsteen nog niet in orde op dcd? --> aangepast in dcd

	private List<Zetsteen> zetstenen; // relatie moet in pijl op dcd en niet als atribuut

	public ResultatenGebied() {

	}

	public void plaatsZetsteenNeer(Zetsteen zetsteen) {	
		int positie = zetsteen.getPositie();

        if (!isPlaatsBaar(positie)) { 
            throw new IllegalArgumentException("Deze positie is onmogelijk!");
        }

        for (Zetsteen z : zetstenen) {
            if (z.getPositie() == positie) {
                throw new IllegalArgumentException("Er is al een gebouwsteen op deze positie!");
            }
        }

        zetstenen.add(zetsteen);
		
	}

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

	public List<Zetsteen> getZetstenen() { //methode voor testen
		return zetstenen;
	}

	

}
