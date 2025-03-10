package cui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domein.DomeinController;
import domein.Speler;

public class AlhambraApplicatie {
	
	Scanner input = new Scanner(System.in);

	DomeinController dc = new DomeinController();
	
	List<Speler> gekozenSpelers = new ArrayList<>(); //Moet overal in de applicatie beschikbaar zijn dus zet ik deze hier en niet in de methode zelf!
	
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
		System.out.printf("Bedankt om te spelen. Hopelijk tot een volgende keer!");
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
		
		List<Speler> alleBeschikbareSpelers = dc.geefAlleSpelers();
		List<String> alleBeschikbareKleuren = dc.geefBeschikbareKleuren();
		
		do {
			System.out.printf("Wilt u nog een speler toevoegen?%nTyp '1' voor ja \t Typ '2' voor neen%n jouw keuze > ");
			keuzeNieuw = input.nextInt();

			if (keuzeNieuw != 1 && keuzeNieuw != 2) {
				System.out.println("Dit was niet een van de opties, probeer opnieuw!");
			}
			if (keuzeNieuw == 1) {
				Speler speler = geefKeuzeSpeler(alleBeschikbareSpelers);
				String kleur = geefKeuzeKleur(alleBeschikbareKleuren);
				
				gekozenSpelers.add(speler);
				dc.kiesSpelerEnKleur(speler, kleur);
				
				alleBeschikbareSpelers.remove(speler);
				alleBeschikbareKleuren.remove(kleur);
			}
			
		} while (keuzeNieuw != 2&&gekozenSpelers.size()<6 /*|| gekozenSpelers.size()>3*/); // Er moeten 3 spelers meespelen, dit kan pas nadat kleuren is geïmplementeerd!
		
		System.out.println("Spel is gespeeld!");
	}
	private Speler geefKeuzeSpeler(List<Speler> lijstVanSpelers) {
		int keuze;
		do {
			System.out.println("Kies uit 1 van volgende spelers:");
			//dit geeft de lijst van spelers mee aan de console
			 for (int index = 1; index <= lijstVanSpelers.size(); index ++) {
				System.out.printf("%d. %s%n", index , lijstVanSpelers.get(index-1).toString()); //Alleen gebruikersnaam genoeg? 
			}
			System.out.printf("Geef hier het nummer voor de speler die je wilt selecteren voor dit spel in > ");
			keuze = input.nextInt();
			if(keuze > lijstVanSpelers.size() || keuze < 1) {
				System.out.println("Foutieve waarde ingegeven, probeer opnieuw! ");
			}
		}while (keuze > lijstVanSpelers.size() || keuze < 1)/* 7 moet worden vervangen door size van de lijst zodat er kan worden gekozen voor een beschikbare speler*/;
		return lijstVanSpelers.get(keuze-1);
	}
	
	private String geefKeuzeKleur(List<String> kleuren){
		/* nog niet duidelijk of we een enum gaan gebruiken en hoe de kleur keuze gaat werken 
		 * dus enkel begin code*/
		int keuze;
		do {
			System.out.println("Kies uit 1 van volgende beschikbare kleuren:");
			/*zelfde principe als bij geefKeuzeSpeler()*/
			for (int index = 0; index < kleuren.size(); index ++) {
				System.out.printf("%d. %s%n", index+1 , kleuren.get(index).toString());
			}
			System.out.printf("Geef hier het nummer voor de kleur die je wilt selecteren voor dit spel in >");
			keuze = input.nextInt();
		}while(keuze < 1||keuze > kleuren.size());
		return kleuren.get(keuze-1);//Even iets ingevuld zodat ik verder kon testen, mag uiteraard aangepast worden!
	}
}
