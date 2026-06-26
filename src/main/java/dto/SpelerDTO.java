package dto;

import java.util.List;

import utils.SpelerKleur;

public record SpelerDTO(String gebruikersnaam, int geboortejaar, int aantalGespeeld, int aantalGewonnen, SpelerKleur kleur,List<ZetsteenDTO> zetstenen,List<GebouwsteenDTO> gebouwtsenen, int punten) {

}
