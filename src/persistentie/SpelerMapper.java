package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domein.Speler;

public class SpelerMapper {

    private static final String INSERT_SPELER = "INSERT INTO speler (Gebruikers, Geboortejaar, AantalGewonnen, AantalGespeeld) VALUES (?, ?, ?, ?)";
    
    private static final String GEEF_SPELER = "SELECT * FROM speler WHERE Gebruikers = ?";
    
            
    public void voegToe(Speler speler) 
    {
    	try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(INSERT_SPELER)) 
        {
            query.setString(1, speler.getGebruikersnaam());
            query.setInt(2, speler.getGeboortejaar());
            query.setInt(3, speler.getAantalGewonnen());
            query.setInt(4, speler.getAantalGespeeld());
            
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    
    public Speler geefSpeler(String gebruikersnaam) {
        Speler speler = null;

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_SPELER)) {
            query.setString(1, gebruikersnaam);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) 
                {
                    int geboortejaar = rs.getInt("geboortejaar");
                    int aantalGewonnen = rs.getInt("aantalGewonnen");
                    int aantalGespeeld = rs.getInt("aantalGespeeld");

                    speler = new Speler(gebruikersnaam, geboortejaar, aantalGewonnen, aantalGespeeld);               
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return speler;
    }

    //Dit is voor de DTO te kunnen opstellen. Als de databank werkt mag dit weg!
//    public List<Speler> geefAlleSpelers(){
//    	List<Speler> spelers = new ArrayList<>();
//    	
//    	spelers.add(new Speler("bestaatAl",2003));
//    	spelers.add(new Speler("bestaatOokAl",1999,4,10));
//    	
//    	return spelers;
//    }
}
