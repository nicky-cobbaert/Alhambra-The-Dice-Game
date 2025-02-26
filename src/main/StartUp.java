package main;

import cui.AlhambraApplicatie;
import domein.DomeinController;

public class StartUp {

	public static void main(String[] args) {
		DomeinController dc = new DomeinController();
		AlhambraApplicatie app = new AlhambraApplicatie(dc);
		app.startApplicatie();
	}
}
