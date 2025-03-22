package domein;

import java.util.List;

public class ResultatenGebied implements Placeable{ // relatie met Zetsteen nog niet in orde op dcd? --> aangepast in dcd

	private List<Zetsteen> zetstenen; // relatie moet in pijl op dcd en niet als atribuut

	public ResultatenGebied() {

	}

	public void plaatsZetsteenNeer(Zetsteen zetsteen) {
		if(isPlaatsBaar(zetsteen.getPositie()) == true) { 
			zetstenen.add(zetsteen);
		}
		
	}

	@Override
	public boolean isPlaatsBaar(int positie) {
		
		return true; // nog aanpassen naargelang uc4
	}

}
