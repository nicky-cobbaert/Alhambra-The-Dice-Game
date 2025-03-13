package cui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import domein.DomeinController;
import domein.Speler;
import exceptions.GebruikersnaamInGebruikException;

public class AlhambraApplicatie {
	
	Scanner input = new Scanner(System.in);

	DomeinController dc = new DomeinController();
	
	List<Speler> gekozenSpelers = new ArrayList<>(); //Moet overal in de applicatie beschikbaar zijn dus zet ik deze hier en niet in de methode zelf!
	
	public AlhambraApplicatie(DomeinController dc) {
		this.dc = dc;
	}
	
	public void startApplicatie() {
		int keuze;
		boolean isGeldig = true;
		
		do {
			try {
				keuze = menu();
				
				while (keuze != 3) {
					switch (keuze) {
						case 1 -> registreerNieuweSpeler();
						case 2 -> startNieuwSpel();
					}
				
					keuze = menu();
				} 
			isGeldig = false;
			
			} catch (InputMismatchException e) {
				System.out.printf("Je gaf een foute invoer in, lees goed wat je moet ingeven. Probeer opnieuw!%n");
				input.nextLine();
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (GebruikersnaamInGebruikException e) {
				System.out.println(e.getMessage());
			}
		} while (isGeldig);
		System.out.printf("Bedankt om te spelen. Hopelijk tot een volgende keer!");
	}
	
	private int menu() {
		int keuze;
		
		do {
			System.out.printf("1. Registreer nieuwe speler %n2. Start nieuw spel %n3. Afsluiten %nUw Keuze > ");
			keuze = input.nextInt();
			
			if (keuze<1||keuze>3) {
				System.out.printf("%nOngeldige keuze, probeer opnieuw! %n%n");
			}
		} while (keuze<1||keuze>3);
		
		return keuze;
	}
	
	private void registreerNieuweSpeler() {
		String gebruikersnaam;
		int geboortejaar;
		
		input.nextLine();
		
		System.out.printf("Geef uw gebruikersnaam in > ");
		gebruikersnaam = input.nextLine();
		
		System.out.printf("Geef uw geboortejaar in > ");
		geboortejaar = input.nextInt(); // parseint gebruikt om buffer te legen (indien try-catch)
		
		dc.registreerSpeler(gebruikersnaam, geboortejaar);
	}
	
	
	// volgende code is een work in progress
	private void startNieuwSpel() {
		dc.maakNieuwSpel();
		int keuzeNieuw = 0;
		
		List<Speler> alleBeschikbareSpelers = dc.geefAlleSpelers();
		List<String> alleBeschikbareKleuren = dc.geefBeschikbareKleuren();
		
		do {
			try {
			System.out.printf("Wilt u nog een speler toevoegen?%nTyp '1' voor ja \t Typ '2' voor neen%nJouw keuze > ");
			keuzeNieuw = input.nextInt(); 

			if (keuzeNieuw != 1 && keuzeNieuw != 2) {
				System.err.println("Dit was niet een van de opties, probeer opnieuw!");
			} else {
				if (keuzeNieuw == 1) {
					Speler speler = geefKeuzeSpeler(alleBeschikbareSpelers);
					String kleur = geefKeuzeKleur(alleBeschikbareKleuren);
				
					gekozenSpelers.add(speler);
					dc.kiesSpelerEnKleur(speler, kleur);
				
					alleBeschikbareSpelers.remove(speler);
					alleBeschikbareKleuren = dc.geefBeschikbareKleuren();
				}
			
				if (keuzeNieuw==2 && gekozenSpelers.size()<3) {
					System.out.println("Er moeten minstens 3 spelers meespelen!");
				}
			}
			if (gekozenSpelers.size()==6) { //Als er 6 spelers geselecteerd zijn gaat hij uit de whileloop en gaat hij verder naar het spel
				break;	
			}
			}catch(InputMismatchException e) {
				System.err.println("Zorg dat je het juiste ingeeft");
				input.nextLine();
			}
		} while (keuzeNieuw != 2 || gekozenSpelers.size()<3); // Er moeten 3 spelers meespelen, dit kan pas nadat kleuren is geïmplementeerd!
									//Bovenstaande code is opgevangen in dc.startSpel hieronder! (staat momenteel in commentaar)
		
		System.out.println("Volgende spelers nemen deel aan het spel: ");
		for (Speler s:dc.geefDeelnemerVanSpel()) {
			System.out.println(s.toString());
		}
		System.out.println();
//		dc.startSpel();
		System.out.println("Het spel is gespeeld!");
	}
	private Speler geefKeuzeSpeler(List<Speler> lijstVanSpelers) {
		int keuze = 0;
		boolean isGeldig = true;
		do {
			try {
				System.out.println("Kies uit 1 van volgende spelers:");
			//dit geeft de lijst van spelers mee aan de console
				for (int index = 1; index <= lijstVanSpelers.size(); index ++) {
					System.out.printf("%d. %s%n", index , lijstVanSpelers.get(index-1).toString()); //Alleen gebruikersnaam genoeg? 
			}
				System.out.printf("Geef hier het nummer voor de speler die je wilt selecteren voor dit spel in > ");
			keuze = input.nextInt();
			isGeldig = keuze <= lijstVanSpelers.size() && keuze >= 1;
			if(!isGeldig|| keuze == -1) {
				throw new IllegalArgumentException();
				}
			}
			catch(InputMismatchException  e) {
				System.err.println("voer AUB een getal in");
				input.nextLine();
			}catch(IllegalArgumentException e) {
				System.err.println("Foutieve waarde ingegeven, probeer opnieuw! ");
			}
			catch(Exception e) {
			System.err.println("er is iets fout gegaan");
		}
		}while (!isGeldig)/* 7 moet worden vervangen door size van de lijst zodat er kan worden gekozen voor een beschikbare speler*/;
		return lijstVanSpelers.get(keuze-1);
	}
	
	private String geefKeuzeKleur(List<String> kleuren){
		/* nog niet duidelijk of we een enum gaan gebruiken en hoe de kleur keuze gaat werken 
		 * dus enkel begin code*/
		int keuze = 0;
		boolean isGeldig = false;
		do {
			try {
			System.out.println("Kies uit 1 van volgende beschikbare kleuren:");
			/*zelfde principe als bij geefKeuzeSpeler()*/
			for (int index = 0; index < kleuren.size(); index ++) {
				System.out.printf("%d. %s%n", index+1 , kleuren.get(index).toString());
			}
			System.out.printf("Geef hier het nummer voor de kleur die je wilt selecteren voor dit spel in >");
			keuze = input.nextInt();
			isGeldig = keuze >= 1 && keuze <= kleuren.size();
			if(!isGeldig) {
				throw new IllegalArgumentException();
			}
			}catch(InputMismatchException e) {
				System.err.println(e.getMessage());
			}
			catch(IllegalArgumentException e) {
				System.err.println("Foutieve waarde ingegeven, probeer opnieuw! ");
			}catch(Exception e) {
				System.err.println("er is iets fout gegaan");
			}
		}while(!isGeldig);
		return kleuren.get(keuze-1);//Even iets ingevuld zodat ik verder kon testen, mag uiteraard aangepast worden!
	}
}
