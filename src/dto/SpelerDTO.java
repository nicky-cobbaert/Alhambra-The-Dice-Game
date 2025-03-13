package dto;

import utils.Kleuren;

public record SpelerDTO(String gebruikersnaam, int geboortejaar, int aantalGespeeld, int aantalGewonnen, Kleuren kleur) {

}
