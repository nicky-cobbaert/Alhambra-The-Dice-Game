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
				case 2 -> startNieuwSpel();
			}
			
			keuze = menu();
		} 
		System.out.println("Bedankt om te spelen. Hopelijk tot een volgende keer!");
	}
	
	private int menu() {
		int keuze;
		
		do {
			System.out.printf("1. Registreer nieuwe speler %n2. Start nieuw spel %n3. Afsluiten %nUw Keuze > ");
			keuze = input.nextInt();
			
			input.nextLine();
			
			if (keuze<1||keuze>3) {
				System.out.printf("%nOngeldige keuze, probeer opnieuw! %n%n");
			}
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
		dc.maakNieuwSpel();
		int keuzeNieuw;
		do {
			System.out.printf("Wilt u nog een speler toevoegen?%n typ '1' voor ja     typ '2' voor neen%n jouw keuze > ");
			keuzeNieuw = input.nextInt();
			 dc.kiesSpelerEnKleur(geefKeuzeSpeler(dc.geefBeschikbareSpelers()),geefKeuzeKleur(dc.geefBeschikbareKleuren()));
		
		}while (keuzeNieuw < 1 /*|| dc.spelDeelnemers.size < 7*/); // zolang het aantal spelers niet groter is dan toegelaten kan men spelers toevoegen
	}
	private Speler geefKeuzeSpeler(List<Speler> lijstVanSpelers) {
		int keuze;
		do {
			System.out.println("kies uit 1 van volgende spelers:");
			//dit geeft de lijst van spelers mee aan de console
			  for (int index = 1; index < lijstVanSpelers.size(); index ++) {
				System.out.printf("%d. %s%n", index , lijstVanSpelers.get(index - 1).toString());
			}
			System.out.printf("geef hier het nummer voor de speler die je wilt selecteren voor dit spel in > ");
			keuze = input.nextInt();
			if(keuze > lijstVanSpelers.size() || keuze < 1) {
				System.out.println("foutiefe waarde ingegeven probeer het opnieuw");
			}
		}while (keuze > lijstVanSpelers.size() || keuze < 1)/* 7 moet worden vervangen door size van de lijst zodat er kan worden gekozen voor een beschikbare speler*/;
		return lijstVanSpelers.get(keuze);
	}
	
	private String geefKeuzeKleur(List<String> kleuren){
		/* nog niet duidelijk of we een enum gaan gebruiken en hoe de kleur keuze gaat werken 
		 * dus enkel begin code*/
		do {
			System.out.println("kies uit 1 van volgende beschikbare kleuren:");
			/*zelfde principe als bij geefKeuzeSpeler()*/
			for (int index = 1; index < kleuren.size(); index ++) {
				System.out.printf("%d. %s%n", index , kleuren.get(index - 1).toString());
			}
		}while(false);
		return null;
	}
}
