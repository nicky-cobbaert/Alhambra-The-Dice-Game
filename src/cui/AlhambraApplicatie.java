package cui;

import java.util.List;
import java.util.Scanner;

import domein.DomeinController;
import domein.Speler;

public class AlhambraApplicatie {
	
	Scanner input = new Scanner(System.in);

	DomeinController dc = new DomeinController();
	
	public AlhambraApplicatie(DomeinController dc) {
		this.dc = dc;
	}
	
	public void startApplicatie() {
		int keuze = menu();
		
		while (keuze != 3) {
			switch (keuze) {
				case 1 -> registreerNieuweSpeler();
				case 2 -> System.out.println("Binnenkort beschikbaar!");
			}
			
			keuze = menu();
		} 
	}
	
	private int menu() {
		int keuze;
		
		do {
			System.out.printf("1. Registreer nieuwe speler %n2. Start nieuw spel %n3. Afsluiten %nUw Keuze > ");
			keuze = input.nextInt();
			
		} while (keuze<1||keuze>3);
		
		return keuze;
	}
	
	private void registreerNieuweSpeler() {
		String gebruikersnaam;
		int geboortejaar;
		
		System.out.printf("Geef uw gebruikersnaam in > ");
		gebruikersnaam = input.next();
		
		System.out.printf("Geef uw geboortejaar in > ");
		geboortejaar = input.nextInt();
		
		dc.registreerSpeler(gebruikersnaam, geboortejaar);
	}
}
