package main;

import cui.AlhambraApplicatie;
import domein.DomeinController;

public class StartUp {

	public static void main(String[] args) {
		DomeinController dc = new DomeinController();  /**Er wordt al een dc gemaakt in AlhambraApp*/
		dc.setGUIMode(false);
		AlhambraApplicatie app = new AlhambraApplicatie(dc);
		app.startApplicatie();
	}
}
