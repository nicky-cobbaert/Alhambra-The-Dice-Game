package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import utils.Kleuren;

public class Spel {
    private final List<String> beschikbareKleuren;
    private final List<Speler> gekozenSpelers;
    private Speler startSpeler;

    public Spel() {
        this.beschikbareKleuren = new ArrayList<>();
        Collections.addAll(beschikbareKleuren, "blauw", "groen", "wit", "geel", "oranje", "rood");

        this.gekozenSpelers = new ArrayList<>();
    }

    public List<String> getBeschikbareKleuren() {
        return beschikbareKleuren;
    }

    public List<Speler> getGekozenSpelers() {
        return gekozenSpelers;
    }

    public void kiesSpeler(Speler speler, String kleur) {
        if (gekozenSpelers.size() >= 6) {
            throw new IllegalArgumentException("Er mogen maximaal 6 spelers meedoen.");
        }
        if (!beschikbareKleuren.contains(kleur)) {
            throw new IllegalArgumentException("Deze kleur is niet beschikbaar.");
        }
        
        speler.setKleur(kleur);                   // was niet zichtbaar
        gekozenSpelers.add(speler);
        beschikbareKleuren.remove(kleur);
    }

    public void startSpel() {
        if (gekozenSpelers.size() < 3) {
            throw new IllegalArgumentException("Er moeten minstens 3 spelers zijn om het spel te starten.");
        }

      
        Random rand = new Random();
        startSpeler = gekozenSpelers.get(rand.nextInt(gekozenSpelers.size()));

        System.out.println("Het spel is gestart!");
        System.out.println("Startspeler: " + startSpeler.getGebruikersnaam());

        for (Speler speler : gekozenSpelers) {
            System.out.println("Speler: " + speler.getGebruikersnaam() + 
                               ", Kleur: " + speler.getKleur() +
                               ", Leeftijd: " + speler.getGeboortejaar());
        }
    }

    public Speler getStartSpeler() {
        return startSpeler;
    }
    
    public List<Speler> geefBeschikbareSpelers(List<Speler> alleSpelers){
    	List<Speler> bs = new ArrayList<Speler>();
    	
    	for(Speler s: alleSpelers) {
    		if(!gekozenSpelers.contains(s)) {
    			bs.add(s);
    		}
    	}
    	
    	return bs;
    }
}
