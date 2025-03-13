package dto;

import utils.Kleuren;

public record GekozenSpelersDTO(String gebruikersnaam, int geboortejaar, int aantalGespeeld, int aantalGewonnen, Kleuren kleur) {

}
