package exceptions;

public class GebruikersnaamInGebruikException extends RuntimeException 
{

	public GebruikersnaamInGebruikException() {
		super("gebruikersnaam.gebruik");
	}

	public GebruikersnaamInGebruikException(String message) {
		super(message);
	}
}
