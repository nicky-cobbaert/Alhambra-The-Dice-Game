package domein;

import java.util.List;

public class GebouwpuntenGebied implements Placeable{ //relatie met Gebouwsteen nog niet in orde op dcd? NOG EENS HERBEKIJKEN NIET AANGEPAST
	
	
	private List<Gebouwsteen> gebouwstenen;

	public GebouwpuntenGebied() {
		
	}

	public void plaatsGebouwsteen(Gebouwsteen gebouwsteen) {
		if(isPlaatsBaar(gebouwsteen.getPositie()) == true) { 
			gebouwstenen.add(gebouwsteen);
		}
		
	}
	
	@Override
	public boolean isPlaatsBaar(int positie) {
		return true;  //nog aanpassen naargelang uc4
	}

}