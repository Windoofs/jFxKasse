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

class DBController
{

	private Connection connection;

	private String DB_PATH_Linux = System.getProperty("user.home")
			+ "/bin/jFxKasse/";

	public String dbname;
	
	private Main main;

	// private Cryption crypo = new Cryption();
	private String schluessel; // Für Ver-/Entschlüsselung
	
	boolean databasepresent = false;

	public void main()
	{
		try {
				connection = DriverManager
						.getConnection("jdbc:sqlite:" + DB_PATH_Linux + dbname + ".db");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	DBController(Main main)
	{
		this.main = main;
	}

	public void verbindeDatenbank()
	{ // Verbinde mit der Datenbank-Datei
		System.out.println("Verbinde ... DB name: " + dbname);
		try {
			if (connection != null)
				return;
			
				connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH_Linux + dbname + ".db");
			
		
			if (!connection.isClosed())
				System.out.println("DB Datei-Verbindung erstellt");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run()
			{
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

	public void fuellenPositionen(int pID, String pName, float pValue,
			String pColor)
	{ // Neuen Eintrag erstellen
		System.out.println("Erstelle neuen positionen eintrag");
		try {
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO positionen VALUES (?, ?, ?, ?);");
			ps.setInt(1, pID); // Primärschlässel
			ps.setString(2, pName);
			ps.setFloat(3, pValue);
			ps.setString(4, pColor);
		

			ps.addBatch();
			connection.setAutoCommit(false);
			ps.executeBatch(); // SQL ausführen
			connection.setAutoCommit(true);
			// connection.close();
		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
		}
	}

	public void erstelleTabellePositionen()
	{ // Erstelle Tabelle mit Reihen
		System.out.println("Erstelle Tabelle Positionen");
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("DROP TABLE IF EXISTS positionen;");
			stmt.executeUpdate(
					"CREATE TABLE positionen (id, name, value, color);");
		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
		}
		
		for(int i = 0; i < 25; i++) {
			fuellenPositionen(i+1, "Noch frei", (float) 0.00, "#FAF0E6");
		}
		
	}
	
	public void erstelleTabelleJobs()
	{ // Erstelle Tabelle mit Reihen
		System.out.println("Erstelle Tabelle Jobs");
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("DROP TABLE IF EXISTS jobs;");
			stmt.executeUpdate(
					"CREATE TABLE jobs (id, time, positionen, state, value);");
		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
		}
	}

	public boolean existiertDatenbank(String pPfad)
	{ // Prüft ob die Datenbank existiert
		File varTmpDir = new File(pPfad);
		if (!varTmpDir.exists()) {
			return false;
		} else {
			return true;
		}
	}

	public void ausgebenSysoPositionen()
	{ // Debugging Ausgabe der kompletten Tabelle
		System.out.println("Print positionen");
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM positionen;");
			while (rs.next()) {
				System.out.println("id = " + rs.getString("id"));
				System.out.println("name = " + rs.getString("name"));
				System.out.println("vale = " + rs.getString("value"));
				System.out.println("color = " + rs.getString("color"));
				System.out.println(" ");
			}

		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
		}
	}

	public String getName(int pID)
	{ // Gibt das Datum zurück
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, name FROM positionen WHERE id = " + pID + ";");
			return rs.getString("name");
		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
			return "Error 404";
		}
	}

	public String getValue(int pID)
	{ // Gibt das Konto zurück
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, value FROM positionen WHERE id = " + pID + ";");
			return rs.getString("value");
		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
			return "Error 404";
		}
	}

	public String getColor(int pID)
	{ // Gibt den Nutzernamen zurück
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, color FROM positionen WHERE id = " + pID + ";");
			return rs.getString("color");
		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
			return "Error 404";
		}
	}

	public String getEmail(int pID)
	{ // Gibt die Email zurück
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, email FROM konten WHERE id = " + pID + ";");
			return rs.getString("email");

		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
			return "Error 404";
		}
	}

	public String getPasswort(int pID)
	{ // Gibt das Passwort zurück
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, passwort FROM konten WHERE id = " + pID + ";");
			return rs.getString("passwort");

		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
			return "Error 404";
		}

	}

	public void setName(int pID, String pName)
	{ // Setzt das Datum
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE positionen SET name = '" + pName
					+ "'WHERE id =" + pID + ";");
		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
		}
	}

	public void setValue(int pID, String pValue)
	{ // Setzt das Konto
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE positionen SET value = '" + pValue
					+ "'WHERE id =" + pID + ";");
		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
		}
	}

	public void setColor(int pID, String pColor)
	{ // Setzt den Nutzername
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE positionen SET color = '" + pColor
					+ "'WHERE id =" + pID + ";");
		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
		}
	}

	public void setEmail(int pID, String pEmail)
	{ // Setzt die Email
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE konten SET email = '" + pEmail
					+ "'WHERE id =" + pID + ";");
		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
		}
	}

	public void setPasswort(int pID, String pPasswort)
	{ // Setzt das Passwort
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE konten SET passwort = '" + pPasswort
					+ "'WHERE id =" + pID + ";");
		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
		}
	}

	public void loeschenEintrag(int pID)
	{ // Löscht den Eintrag
		int alteOrdnung = getNeueID(); // Speichert die ID des letzten Eintrags
		try {
			if (pID == getNeueID() - 1) { // Falls letzter Eintrag gelöscht werden
													// soll
				PreparedStatement ps = connection
						.prepareStatement("DELETE FROM konten WHERE id=?");
				ps.setInt(1, pID);
				ps.executeUpdate();
			} else { // Wenn ein Eintrag in mitten der DB gelöscht wird, dann wird
						// hochkopiert
				while (!(pID + 1 == alteOrdnung - 1)) {
					int pIDneu = pID + 1;
					Statement stmt = connection.createStatement();
					ResultSet rs = stmt.executeQuery(
							"SELECT id, datum, konto, nutzername, email, passwort FROM konten WHERE id = "
									+ pIDneu + ";");

					String pDatum = rs.getString("datum");
					String pKonto = rs.getString("konto");
					String pNutzername = rs.getString("nutzername");
					String pEmail = rs.getString("email");
					String pPasswort = rs.getString("passwort");

					stmt.executeUpdate("UPDATE konten SET datum = '" + pDatum
							+ "', konto = '" + pKonto + "', nutzername = '"
							+ pNutzername + "', email = '" + pEmail + "', passwort = '"
							+ pPasswort + "'WHERE id =" + pID + ";");
					pID = pID + 1;
				}
				PreparedStatement ps = connection
						.prepareStatement("DELETE FROM konten WHERE id=?");
				ps.setInt(1, alteOrdnung - 1);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
		}
	}

	public int getNeueID()
	{ // Gibt die ID des nächsten Eintrags zurück
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

	public ArrayList<tableDataPositionen> ladeTabellePositionen()
	{ // Gibt ein Objekt daten mit allen Einträgen der DB zurück
		ArrayList<tableDataPositionen> daten = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM positionen;");
			while (rs.next()) {
				try {
					// Entschlüsselte Daten werden als Datenobjekt gespeichert
					daten.add(new tableDataPositionen(rs.getInt("id"), rs.getString("name"), rs.getString("value"), rs.getString("color")));
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

	public void setSchluessel(String pSchluessel)
	{ // Setzt den Schlüssel für die Ver-/Entschlüsslung
		schluessel = pSchluessel;
	}
	

}
