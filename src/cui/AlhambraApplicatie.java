package cui;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import domein.DomeinController;
import dto.SpelerDTO;
import exceptions.GebruikersnaamInGebruikException;

public class AlhambraApplicatie {
	
	Scanner input = new Scanner(System.in);

	DomeinController dc ;
	
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
				System.err.printf("Je gaf een foute invoer in, lees goed wat je moet ingeven. Probeer opnieuw!%n");
				input.nextLine();
			} catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
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
				System.err.printf("Ongeldige keuze, probeer opnieuw! %n");
			}
		} while (keuze<1||keuze>3);
		
		return keuze;
	}
	
	private void registreerNieuweSpeler() {
		String gebruikersnaam;
		int geboortejaar;
		
		boolean nogHerhalen = true;
		
		do {
			try {
				input.nextLine();
				
				System.out.printf("Geef uw gebruikersnaam in > ");
				gebruikersnaam = input.nextLine();
				
				System.out.printf("Geef uw geboortejaar in > ");
				geboortejaar = input.nextInt(); 
				
				dc.registreerSpeler(gebruikersnaam, geboortejaar);
				
				nogHerhalen = false;
			} catch (InputMismatchException e) {
				System.err.println("Je gaf een foute invoer in, lees goed wat je moet ingeven. Probeer opnieuw!");
			} catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
			} catch (GebruikersnaamInGebruikException e) {
				System.err.println(e.getMessage());
			}
		} while (nogHerhalen);
	}
	
	
	private void startNieuwSpel() {
		dc.maakNieuwSpel();
		int keuzeNieuw = 0;
		boolean nogHerhalen = true;
		
		List<SpelerDTO> alleBeschikbareSpelers = dc.geefBeschikbareSpelers();
		List<String> alleBeschikbareKleuren = dc.geefBeschikbareKleuren();
		
		do {
		try {
		do {
			System.out.printf("Wilt u nog een speler toevoegen?%nTyp '1' voor ja \t\t Typ '2' voor neen%nJouw keuze > ");
			keuzeNieuw = input.nextInt(); 

			if (keuzeNieuw != 1 && keuzeNieuw != 2) {
				System.err.println("Dit was niet een van de opties, probeer opnieuw!");
			} else {
				if (keuzeNieuw == 1) {
					int speler = geefKeuzeSpeler(alleBeschikbareSpelers);
					String kleur = geefKeuzeKleur(alleBeschikbareKleuren);
			
					dc.kiesSpelerEnKleur(speler, kleur);
		
					alleBeschikbareSpelers = dc.geefBeschikbareSpelers(); //dit is een lijst van SpelerDTO
					alleBeschikbareKleuren = dc.geefBeschikbareKleuren(); //dit is een lijst van Strings
				}
			
			}
			if (dc.geefGekozenSpelers().size()==6) {
				break;
			}
		} while (keuzeNieuw != 2); 
		dc.startSpel();
		nogHerhalen=false;
		}catch(InputMismatchException e) {
			System.err.println("Geef het getal in van de keuze dat je wilt maken!");
			input.nextLine();  
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
		}while (nogHerhalen);
		System.out.println("Het spel is gestart!");
		System.out.println("Startspeler: "+ dc.geefStartspeler());
		System.out.println("Volgende spelers nemen deel aan dit spel: ");
		for (SpelerDTO dto : dc.geefGekozenSpelers()) {
			System.out.printf("Speler: %-20s kleur: %-6s geboortejaar: %d aantal zetstenen: %d%n",dto.gebruikersnaam()+",",dto.kleur()+",".toString().toLowerCase(),dto.geboortejaar(),dc.geefAantalZetstenen());
		}
		System.out.println("Het spel is gespeeld!");
	}
	private int geefKeuzeSpeler(List<SpelerDTO> lijstVanSpelers) {
		int keuze = 0;
		boolean isGeldig = false;
		do {
			try {
				System.out.println("Kies uit 1 van volgende spelers:");
			//dit geeft de lijst van spelerDTOs mee aan de console
				for (int index = 1; index <= lijstVanSpelers.size(); index ++) {
					System.out.printf("%d. \t %-20s met als geboortejaar %d%n", index , lijstVanSpelers.get(index-1).gebruikersnaam(),lijstVanSpelers.get(index-1).geboortejaar()); //Alleen gebruikersnaam genoeg? 
			}
				System.out.printf("Geef hier het nummer voor de speler die je wilt selecteren voor dit spel in > ");
			keuze = input.nextInt();
			isGeldig = keuze <= lijstVanSpelers.size() && keuze >= 1;
			if(!isGeldig) {
				throw new IllegalArgumentException();
				}
			}catch(InputMismatchException e) {
				System.err.println("Geef het getal van de speler die je wilt kiezen in!");
				input.nextLine();
			}
			catch(IllegalArgumentException e) {
				System.err.println("Foutieve waarde ingegeven, probeer opnieuw! ");
			}catch(Exception e) {
				System.err.println("Er is iets fout gegaan");
			}
		}while (!isGeldig);
		return keuze-1;
	}
	
	private String geefKeuzeKleur(List<String> kleuren){
		int keuze = 0;
		boolean isGeldig = false;
		do {
			try {
			System.out.println("Kies uit 1 van volgende beschikbare kleuren:");
			//zelfde principe als bij geefKeuzeSpeler()
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
				System.err.println("Enkel getallen kunnen worden meegegeven om een kleur te kiezen!");
				input.nextLine();
			}
			catch(IllegalArgumentException e) {
				System.err.println("Foutieve waarde ingegeven, probeer opnieuw! ");
			}catch(Exception e) {
				System.err.println("er is iets fout gegaan");
			}
		}while(!isGeldig);
		return kleuren.get(keuze-1);
	}
}
