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
		boolean alGevuld = false;
		for(Gebouwsteen g:gebouwstenen) {
			int currVakje = g.getPositie()%10;
			int currVolgorde = g.getVolgorde();
			if(g.getPositie()/100 == kleur) {
				if(!(g.getPositie()%10 == 0)) {
					if(alGevuld) {
						/*
						 * als het al gevuld is 
						 * */
						if(currVakje > eerstePlaats) {
							voorsteGebouwstenen.removeLast();
							voorsteGebouwstenen.addFirst(g);
							eerstePlaats = currVakje;
						}
						if(currVakje == eerstePlaats) {
							if(!(currVolgorde < voorsteGebouwstenen.get(0).getVolgorde())) {
							for(int index = 1;index < ronde;index ++) {
								if(currVakje > voorsteGebouwstenen.get(index).getPositie()%10) {
									voorsteGebouwstenen.removeLast();
									voorsteGebouwstenen.add(index,g);
									break;
								}
								if(currVakje == voorsteGebouwstenen.get(index).getPositie()%10) {
									if(currVolgorde < voorsteGebouwstenen.get(index).getVolgorde()) {
										voorsteGebouwstenen.removeLast();
										voorsteGebouwstenen.add(index, g);
										break;
									}
									
								}
								
							}
							if(currVolgorde < voorsteGebouwstenen.get(0).getVolgorde()) {
								voorsteGebouwstenen.removeLast();
								voorsteGebouwstenen.addFirst(g);
							}
							}
						}
						if(currVakje < eerstePlaats) {
							for(int index = 1;index < ronde;index ++) {
								if(currVakje > voorsteGebouwstenen.get(index).getPositie()%10) {
									voorsteGebouwstenen.removeLast();
									voorsteGebouwstenen.add(index, g);
									break;
								}
								if(currVakje == voorsteGebouwstenen.get(index).getPositie()%10&&currVolgorde < voorsteGebouwstenen.get(index).getVolgorde()) {
									voorsteGebouwstenen.removeLast();
									voorsteGebouwstenen.add(index, g);
									break;
									
									
								}
								
							}
						}
						
					}
					else{
						if(voorsteGebouwstenen.size() == 0) {
							voorsteGebouwstenen.addFirst(g);
							eerstePlaats = currVakje;
						}else {
							if(currVakje > eerstePlaats) {
								voorsteGebouwstenen.addFirst(g);
								eerstePlaats = currVakje;
							}
						
							if(currVakje < eerstePlaats) {
								int index = 0;
								for(Gebouwsteen vooresteGebouwsteen:voorsteGebouwstenen) {
									if(currVakje > vooresteGebouwsteen.getPositie()%10) {
										voorsteGebouwstenen.add(index, g);
										break;
									}
									if(vooresteGebouwsteen.getPositie()%10 == currVakje && g.getVolgorde() < vooresteGebouwsteen.getVolgorde()) {
										voorsteGebouwstenen.add(index,g);
										break;
									}
									if(index + 1 == voorsteGebouwstenen.size()) {
										voorsteGebouwstenen.addLast(g);
										break;
									}
									index ++;
								}
							}
							if(currVakje == eerstePlaats) {
								int index = 0;
								for(Gebouwsteen voorsteGebouwsteen:voorsteGebouwstenen) {
									if(index + 1 == voorsteGebouwstenen.size()) {
										voorsteGebouwstenen.addLast(g);
										break;
									}
									if(voorsteGebouwsteen.getPositie()%10 == currVakje && g.getVolgorde() < voorsteGebouwsteen.getVolgorde()) {
										voorsteGebouwstenen.add(index,g);
										break;
									}
									
									index ++;
								}
							}
						}		
					}
				}
			}
			if(!alGevuld && ronde == voorsteGebouwstenen.size()) {
				alGevuld = true;
			}
		}
				
			
			
		
		return voorsteGebouwstenen;
	}
	
}