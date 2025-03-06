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
				case 2 -> System.out.println("nog niet klaar");
			}
			
			keuze = menu();
		} 
	}
	
	private int menu() {
		int keuze;
		
		do {
			System.out.printf("1. Registreer nieuwe speler %n2. Start nieuw spel %n3. Afsluiten %nUw Keuze > ");
			keuze = input.nextInt();
			
			input.nextLine();
		} while (keuze<1||keuze>3);
		
		return keuze;
	}
	
	private void registreerNieuweSpeler() {
		String gebruikersnaam;
		int geboortejaar;
		
		System.out.printf("Geef uw gebruikersnaam in > ");
		gebruikersnaam = input.nextLine();
		
		System.out.printf("Geef uw geboortejaar in > ");
		geboortejaar = input.nextInt();
		
		dc.registreerSpeler(gebruikersnaam, geboortejaar);
	}
	
	
	// volgende code is een work in progress
	private void startNieuwSpel() {
		int keuzeNieuw;
		do {
			System.out.printf("Wilt u nog een speler toevoegen?%n typ '1' voor ja     typ '2' voor neen%n jouw keuze > ");
			keuzeNieuw = input.nextInt();
			// dc.voegToeAanSpel(geefKeuzeSpeler(/*lijstVanBeschikbareSpelers*/),geefKeuzeKleur(/*lijstVanKLeuren*/));
		
		}while (keuzeNieuw < 1 /*|| dc.spelDeelnemers.size < 7*/); // zolang het aantal spelers niet groter is dan toegelaten kan men spelers toevoegen
	}
	private int/*Speler*/ geefKeuzeSpeler(/*lijstVanSpelers*/) {
		int keuze;
		do {
			System.out.println("kies uit 1 van volgende spelers:");
			/*dit geeft de lijst van spelers mee aan de console
			 * for (int index = 1; index < 7; index ++) {
				System.out.printf("%d. %s", index , lijstVanSpelers.get(index - 1).toString());
			}*/
			System.out.printf("geef hier het nummer voor de speler die je wilt selecteren voor dit spel in > ");
			keuze = input.nextInt();
			if(keuze > 7 || keuze < 1) {
				System.out.println("foutiefe waarde ingegeven probeer opnieuw");
			}
		}while (keuze > 7 || keuze < 1)/* 7 moet worden vervangen door de lijst + 1 zodat er kan worden gekozen voor een beschikbare speler*/;
		return /*lijstVanSpelers.get(*/keuze;
	}
	
	private String geefKeuzeKleur(){
		/* nog niet duidelijk of we een enum gaan gebruiken en hoe de kleur keuze gaat werken 
		 * dus enkel begin code*/
		do {
			System.out.println("kies uit 1 van volgende beschikbare kleuren:");
			/*zelfde principe als bij geefKeuzeSpeler()*/
		}while(false);
		return null;
	}
}
