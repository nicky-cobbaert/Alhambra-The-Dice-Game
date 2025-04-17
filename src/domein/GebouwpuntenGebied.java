package domein;

import java.util.ArrayList;
import java.util.List;

public class GebouwpuntenGebied implements Placeable{
	
	
	private List<Gebouwsteen> gebouwstenen;

	public GebouwpuntenGebied() {
		gebouwstenen = new ArrayList<Gebouwsteen>();
	}
	

	public void plaatsGebouwsteenNeer(Gebouwsteen gebouwsteen,int positie) {

	        if (!isPlaatsBaar(positie)) { 
	            throw new IllegalArgumentException("Deze positie is onmogelijk! GebouwpuntenGebied");
	        }
	        gebouwsteen.setVolgorde(1);
	        for (Gebouwsteen g : gebouwstenen) {
	            if (g.getPositie() == positie) {
	                //throw new IllegalArgumentException("Er is al een gebouwsteen op deze positie!");
	            	if (g.getVolgorde() >= gebouwsteen.getVolgorde()) {
	            		gebouwsteen.plaatsNaast(g.getVolgorde());
	            	}
	            }
	        }
	        gebouwsteen.setPositie(positie);
	        this.gebouwstenen.add(gebouwsteen);
	}
	
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
	
	@Override
	public boolean isPlaatsBaar(int positie) {
		int honderdtal = positie / 100;  
	    int rest = positie % 100;    
	    
	    if(honderdtal >= 1 && honderdtal <= 6 && rest >= 0 && rest <= 9) {
	    	return true;  //nog aanpassen naargelang uc4
	    } else {
	    	return false;
	    }
		
	}

	public List<Gebouwsteen> getGebouwstenen() { //getter voor test
		return gebouwstenen;
	}


	public List<Gebouwsteen> getVoorsteGebouwstenen(int ronde,int kleur) {
		List<Gebouwsteen> voorsteGebouwstenen = new ArrayList<Gebouwsteen>();
		int eerstePlaats = 0;
		for(Gebouwsteen g:gebouwstenen) {
			if(g.getPositie()/100 == kleur) {
				int currVakje = g.getPositie()%10; 
				if(!(currVakje == 0)) {
					if(voorsteGebouwstenen.size() == 0) {
						voorsteGebouwstenen.add(g);
						eerstePlaats = currVakje;
					}else {
						if(voorsteGebouwstenen.size() < ronde) {
							if(currVakje > eerstePlaats) {
								voorsteGebouwstenen.addFirst(g);
								eerstePlaats = currVakje;
							}
							if(currVakje < eerstePlaats) {
								int index = 0;
								for(Gebouwsteen vooresteGebouwsteen:voorsteGebouwstenen) {
									if(vooresteGebouwsteen.getPositie() < currVakje) {
										voorsteGebouwstenen.add(index, g);
									}
									if(vooresteGebouwsteen.getPositie() == currVakje && g.getVolgorde() < vooresteGebouwsteen.getVolgorde()) {
										voorsteGebouwstenen.add(index,g);
									}
									if(index + 1 == voorsteGebouwstenen.size()) {
										voorsteGebouwstenen.add(index, g);
									}
									index ++;
								}
							}
							if(currVakje == eerstePlaats) {
								int index = 0;
								for(Gebouwsteen voorsteGebouwsteen:voorsteGebouwstenen) {
									if(voorsteGebouwsteen.getPositie() == currVakje && g.getVolgorde() < voorsteGebouwsteen.getVolgorde()) {
										voorsteGebouwstenen.add(index,g);
									}
									if(index + 1 == voorsteGebouwstenen.size()) {
										voorsteGebouwstenen.add(index, g);
									}
									index ++;
								}
							}
						}
						//TODO als alle gebouwstenen al gevuld zijn
					}
				}
			}
			
		}
		return voorsteGebouwstenen;
	}

}