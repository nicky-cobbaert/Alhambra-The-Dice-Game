package domein;

import java.util.ArrayList;
import java.util.List;

public class ResultatenGebied implements Placeable{ // relatie met Zetsteen nog niet in orde op dcd? --> aangepast in dcd

	private List<Zetsteen> zetstenen; // relatie moet in pijl op dcd en niet als atribuut

	public ResultatenGebied() {
		zetstenen = new ArrayList<Zetsteen>();
	}

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
	
	public void maakHetGebiedLeeg() {
		for(Zetsteen z: zetstenen) {
			z.gaVanHetSpelbord();
		}
		this.zetstenen = new ArrayList<Zetsteen>();
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

	public List<Zetsteen> getVoorsteZetstenen(int positieKleur) {
		List<Zetsteen> voorsteZetstenen = new ArrayList<Zetsteen>();
		for(Zetsteen z:zetstenen) {
			int currPositie = z.getPositie();
			if(currPositie/100 == positieKleur) {
				if(voorsteZetstenen.size() == 2) {
					for(int index = 0;index < 2;index ++) {
						if(currPositie/ 10 % 10 > voorsteZetstenen.get(index).getPositie()/ 10 % 10) {
							voorsteZetstenen.add(index,z);
							voorsteZetstenen.removeLast();
							break;
						}
						if(currPositie/ 10 % 10 == voorsteZetstenen.get(index).getPositie()/ 10 % 10 && currPositie % 10 < voorsteZetstenen.get(index).getPositie() % 10) {
							voorsteZetstenen.add(index,z);
							voorsteZetstenen.removeLast();
							break;
						}
					}
				}
				
				if(voorsteZetstenen.size() == 1) {
					if(currPositie/ 10 % 10 > voorsteZetstenen.get(0).getPositie()/ 10 % 10) {
						voorsteZetstenen.addFirst(z);
					}else {
						if(currPositie/ 10 % 10 == voorsteZetstenen.get(0).getPositie()/ 10 % 10 && currPositie % 10 < voorsteZetstenen.get(0).getPositie() % 10) {
							voorsteZetstenen.addFirst(z);
						}else {
							voorsteZetstenen.addLast(z);
						}
					}
				}
				if(voorsteZetstenen.size() == 0) {
					voorsteZetstenen.addFirst(z);
				}
				
			}
			
		}
		return voorsteZetstenen;
	}

	

}
