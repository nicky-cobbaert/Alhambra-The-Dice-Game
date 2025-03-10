# Relationeel model Use Case 2:

Speler (<u>gebruikersnaam</u>, geboortejaar, aantalGewonnen, aantalGespeeld)

Deelnemer (<u>gebruikersnaam, kleur</u>, spelid)
<br>IR: Vreemde sleutel **gebruikersnaam** verwijst naar gebruikersnaam van **Speler** en is **verplicht**.
<br>IR: Vreemde sleutel **spelid** verwijst naar spelid van **Spel** en is **optioneel**.

Spel (<u>spelid</u>, gebruikersnaam, kleur)
<br>IR: Vreemde sleutel **gebruikersnaam, kleur** verwijst naar gebruikersnaam, kleur van **Deelnemer** (startspeler) en is **optioneel**.
