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
    
    private static final String UPDATE_GEWONNEN = "UPDATE speler SET aantalGewonnen = aantalGewonnen + 1 WHERE gebruikers like ?";
    private static final String UPDATE_GESPEELD = "UPDATE speler SET aantalGespeeld = aantalGespeeld + 1 WHERE gebruikers like ?";
//    private List<Speler> offlineSpelers;
    
    public SpelerMapper() {
//    	offlineSpelers = new ArrayList<Speler>();
//		offlineSpelers.add(new Speler("JelleVH",2004));
//		offlineSpelers.add(new Speler("NickyCob", 2006));
//		offlineSpelers.add(new Speler("SverreL", 2006));
//		offlineSpelers.add(new Speler("LarsDW", 2006));
//		offlineSpelers.add(new Speler("WoutGh", 2006));
//		offlineSpelers.add(new Speler("NickyCob2", 2006));
//		offlineSpelers.add(new Speler("NickyCobExtra", 2006));
	}
            
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

//        for(Speler s: offlineSpelers) {
//        	if(s.getGebruikersnaam() == gebruikersnaam) {
//        		speler = s;
//        		break;
//        	}
//        }
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
//
        return speler;
    }

    
    // er moet nog mogelijk worden gemaakt om alle spelers te krijgen ui de database
    
    public List<Speler> geefAlleSpelers(){
    	
    	//return offlineSpelers;
    	List<Speler> alleSpelers = new ArrayList<Speler>();
    	try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
    			PreparedStatement query = conn.prepareStatement("SELECT * FROM speler")){
    		try (ResultSet rs = query.executeQuery()) {
    			
    			while(rs.next())
    			{
    				String gebruikersnaam = rs.getString("Gebruikers");
    				int geboortejaar = rs.getInt("geboortejaar");
                    int aantalGewonnen = rs.getInt("aantalGewonnen");
                    int aantalGespeeld = rs.getInt("aantalGespeeld");
                    
                    alleSpelers.add(new Speler(gebruikersnaam,geboortejaar,aantalGewonnen,aantalGespeeld));
    			}
    		}
    	}catch (SQLException ex) {
    		throw new RuntimeException(ex);
    	}
    	return alleSpelers;
    }
    
    public void updateGewonnen(String gebruikersnaam) {
    	try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(UPDATE_GEWONNEN)) 
        {
            query.setString(1, gebruikersnaam);
            
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void updateGespeeld(String gebruikersnaam) {
    	try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(UPDATE_GESPEELD)) 
        {
            query.setString(1, gebruikersnaam);
            
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
