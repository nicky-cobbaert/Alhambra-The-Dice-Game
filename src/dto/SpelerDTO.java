package dto;

import utils.SpelerKleur;

public record SpelerDTO(String gebruikersnaam, int geboortejaar, int aantalGespeeld, int aantalGewonnen,SpelerKleur kleur) {

}
