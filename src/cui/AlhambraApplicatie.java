package cui;

import domein.DomeinController;

public class AlhambraApplicatie {

	DomeinController dc = new DomeinController();
	
	public AlhambraApplicatie(DomeinController dc) {
		this.dc = dc;
	}
	
	public void startApplicatie() {
		System.out.println("Test");
	}
	
}
