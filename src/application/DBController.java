package application;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.sql.Statement;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;

class DBController { 

	private Connection connection; 
	
	private String DB_PATH_Win = "C:/ProgramData/PWMaster/datenbank.db";
	private String DB_PATH_Linux = System.getProperty("user.home") + "/bin/PWMaster/datenbank.db";
	
	private Main main;
	//private Cryption crypo = new Cryption();
	private String schluessel; //Für Ver-/Entschlüsselung
	
	public void main() {
		try {
			if(System.getProperty("os.name").equals("Linux")) {
				connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH_Linux); 
			} else {
				connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH_Win); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	DBController(Main main){
		this.main = main;
	} 
	public void verbindeDatenbank() { //Verbinde mit der Datenbank-Datei
		try { 
			if (connection != null) 
				return; 
			if(System.getProperty("os.name").equals("Linux")) {
				connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH_Linux); 
			} else {
				connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH_Win); 
			}
			if (!connection.isClosed()) 
				System.out.println(); 
		} catch (SQLException e) { 
			throw new RuntimeException(e); 
		} 
		Runtime.getRuntime().addShutdownHook(new Thread() { 
			public void run() { 
				try { 
					if (!connection.isClosed() && connection != null) { 
						connection.close(); 
						if (connection.isClosed()) 
							System.out.println(); 
					} 
				} catch (SQLException e) { 
					e.printStackTrace(); 
				} 
			} 
		}); 
	} 
	public void fuellenDatenbank(int pID, String pDatum, String pKonto, String pNutzername, String pEmail, String pPasswort) { //Neuen Eintrag erstellen
		try { 
			PreparedStatement ps = connection.prepareStatement("INSERT INTO konten VALUES (?, ?, ?, ?, ?, ?);"); 
			ps.setInt(1, pID); //Primärschlässel
			ps.setString(2, pDatum);
			ps.setString(3, pKonto);
			ps.setString(4, pNutzername);
			ps.setString(5, pEmail);
			ps.setString(6, pPasswort);

			ps.addBatch();
			connection.setAutoCommit(false); 
			ps.executeBatch();  //SQL ausführen
			connection.setAutoCommit(true); 
			//connection.close(); 
		} catch (SQLException e) { 
			System.err.println("Couldn't handle DB-Query"); 
			e.printStackTrace(); 
		} 
	}
	public void erstelleDatenbank(){ //Erstelle Tabelle mit Reihen
		try { 
			Statement stmt = connection.createStatement(); 
			stmt.executeUpdate("DROP TABLE IF EXISTS konten;"); 
			stmt.executeUpdate("CREATE TABLE konten (id, datum, konto, nutzername, email, passwort);");  
		} catch (SQLException e) { 
			System.err.println("Couldn't handle DB-Query"); 
			e.printStackTrace(); 
		} 
	}
	public boolean existiertDatenbank(String pPfad){ //Prüft ob die Datenbank existiert
		File varTmpDir = new File(pPfad);
		if(!varTmpDir.exists()){
			return false;
		}else{
			return true;
		}
	}
	private void ausgebenSyso(){ //Debugging Ausgabe der kompletten Datenbank
		try { 
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM konten;"); 
			while (rs.next()) { 
				System.out.println("Datum = " + rs.getString("datum")); 
				System.out.println("Konto = " + rs.getString("konto")); 
				System.out.println("Nutzername = " + rs.getString("nutzername")); 
				System.out.println("E-Mail = " + rs.getString("email")); 
				System.out.println("Passwort = " + rs.getString("passwort")); 
				System.out.println(" ");
			} 

		} catch (SQLException e) { 
			System.err.println("Couldn't handle DB-Query"); 
			e.printStackTrace(); 
		} 
	}
	public String getDatum(int pID){ //Gibt das Datum zurück
		try { 
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT id, datum FROM konten WHERE id = "+pID+";" ); 
			return rs.getString("datum"); 
		} catch (SQLException e) { 
			System.err.println("Couldn't handle DB-Query"); 
			e.printStackTrace(); 
			return "Error 404";
		} 
	}
	public  String getKonto(int pID){ //Gibt das Konto zurück
		try { 
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT id, konto FROM konten WHERE id = "+pID+";" ); 
			return rs.getString("konto"); 
		} catch (SQLException e) { 
			System.err.println("Couldn't handle DB-Query"); 
			e.printStackTrace(); 
			return "Error 404";
		} 
	}
	public  String getNutzername(int pID){ //Gibt den Nutzernamen zurück
		try { 
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT id, nutzername FROM konten WHERE id = "+pID+";" ); 
			return rs.getString("nutzername"); 
		} catch (SQLException e) { 
			System.err.println("Couldn't handle DB-Query"); 
			e.printStackTrace(); 
			return "Error 404";
		} 
	}
	public  String getEmail(int pID){ //Gibt die Email zurück
		try { 
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT id, email FROM konten WHERE id = "+pID+";" ); 
			return rs.getString("email"); 

		} catch (SQLException e) { 
			System.err.println("Couldn't handle DB-Query"); 
			e.printStackTrace(); 
			return "Error 404";
		} 
	}
	public  String getPasswort(int pID){ //Gibt das Passwort zurück
		try { 
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT id, passwort FROM konten WHERE id = "+pID+";" ); 
			return rs.getString("passwort"); 

		} catch (SQLException e) { 
			System.err.println("Couldn't handle DB-Query"); 
			e.printStackTrace(); 
			return "Error 404";
		} 

	}

	public void setDatum(int pID, String pDatum){ //Setzt das Datum
		try { 
			Statement stmt = connection.createStatement(); 
			stmt.executeUpdate("UPDATE konten SET datum = '"+pDatum+"'WHERE id ="+pID+";");
		} catch (SQLException e) { 
			System.err.println("Couldn't handle DB-Query"); 
			e.printStackTrace(); 
		} 
	}

	public void setKonto(int pID, String pKonto){ //Setzt das Konto
		try { 
			Statement stmt = connection.createStatement(); 
			stmt.executeUpdate("UPDATE konten SET konto = '"+pKonto+"'WHERE id ="+pID+";");
		} catch (SQLException e) { 
			System.err.println("Couldn't handle DB-Query"); 
			e.printStackTrace(); 
		} 
	}

	public void setNutername(int pID, String pNutername){ //Setzt den Nutzername
		try { 
			Statement stmt = connection.createStatement(); 
			stmt.executeUpdate("UPDATE konten SET nutzername = '"+pNutername+"'WHERE id ="+pID+";");
		} catch (SQLException e) { 
			System.err.println("Couldn't handle DB-Query"); 
			e.printStackTrace(); 
		} 
	}

	public void setEmail(int pID, String pEmail){ //Setzt die Email
		try { 
			Statement stmt = connection.createStatement(); 
			stmt.executeUpdate("UPDATE konten SET email = '"+pEmail+"'WHERE id ="+pID+";");
		} catch (SQLException e) { 
			System.err.println("Couldn't handle DB-Query"); 
			e.printStackTrace(); 
		} 
	}

	public void setPasswort(int pID, String pPasswort){ //Setzt das Passwort
		try { 
			Statement stmt = connection.createStatement(); 
			stmt.executeUpdate("UPDATE konten SET passwort = '"+pPasswort+"'WHERE id ="+pID+";");
		} catch (SQLException e) { 
			System.err.println("Couldn't handle DB-Query"); 
			e.printStackTrace(); 
		} 
	}

	public void loeschenEintrag(int pID){ //Löscht den Eintrag
		int alteOrdnung = getNeueID(); //Speichert die ID des letzten Eintrags
		try {
			if(pID == getNeueID() -1){ //Falls letzter Eintrag gelöscht werden soll
				PreparedStatement ps = connection.prepareStatement("DELETE FROM konten WHERE id=?");
				ps.setInt(1, pID);
				ps.executeUpdate();
			}else{ // Wenn ein Eintrag in mitten der DB gelöscht wird, dann wird hochkopiert
				while(!(pID+1 == alteOrdnung -1)){
					int pIDneu = pID + 1;
					Statement stmt = connection.createStatement(); 
					ResultSet rs = stmt.executeQuery("SELECT id, datum, konto, nutzername, email, passwort FROM konten WHERE id = "+pIDneu+";" );

					String pDatum = rs.getString("datum");
					String pKonto = rs.getString("konto");
					String pNutzername = rs.getString("nutzername");
					String pEmail = rs.getString("email"); 
					String pPasswort = rs.getString("passwort"); 

					stmt.executeUpdate("UPDATE konten SET datum = '"+pDatum+"', konto = '"+pKonto+"', nutzername = '"+pNutzername+"', email = '"+pEmail+"', passwort = '"+pPasswort+"'WHERE id ="+pID+";");
					pID = pID + 1;
				}
				PreparedStatement ps = connection.prepareStatement("DELETE FROM konten WHERE id=?");
				ps.setInt(1, alteOrdnung -1);
				ps.executeUpdate();
			}
		} catch (SQLException e) { 
			System.err.println("Couldn't handle DB-Query"); 
			e.printStackTrace(); 
		} 
	}

	public int getNeueID(){ //Gibt die ID des nächsten Eintrags zurück
		int neueID = 0;
		try { 
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM konten;"); 
			while (rs.next()) { 
				neueID = rs.getInt("id") + 1;
			} 
			rs.close(); 
		} catch (SQLException e) { 
			System.err.println("Couldn't handle DB-Query"); 
			e.printStackTrace(); 	
		} 
		return neueID;	
	}

	public ArrayList<tableData> ladeTabelle(){  //Gibt ein Objekt daten mit allen Einträgen der DB zurück
		ArrayList<tableData> daten = new ArrayList<>();
		try { 
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM konten;"); 
			while (rs.next()) {
				try {
					// Entschlüsselte Daten werden als Datenobjekt gespeichert
					//daten.add(new tableData(rs.getInt("id"), crypo.entschluesseln(rs.getString("datum"), schluessel), crypo.entschluesseln(rs.getString("konto"),schluessel)));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		} catch (SQLException e) { 
			System.err.println("Couldn't handle DB-Query"); 
			e.printStackTrace(); 
		} 
		return daten;
	}
	
	public void setSchluessel(String pSchluessel){ //Setzt den Schlüssel für die Ver-/Entschlüsslung
		schluessel = pSchluessel;
	}

}