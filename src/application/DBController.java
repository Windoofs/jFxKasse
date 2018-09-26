package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.File;

class DBController
{
	private Connection connection;

	private String DB_PATH_Linux = System.getProperty("user.home")
			+ "/bin/jFxKasse/";

	public String dbname;

	@SuppressWarnings("unused")
	private Main main;

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

	public void connectDatabase()
	{ // connect to database
		System.out.println("Verbinde ... DB name: " + dbname);
		try {
			if (connection != null)
				return;
			connection = DriverManager
					.getConnection("jdbc:sqlite:" + DB_PATH_Linux + dbname + ".db");
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

	public boolean existiertDatenbank(String pPfad)
	{ // Prüft ob die Datenbank existiert
		File varTmpDir = new File(pPfad);
		if (!varTmpDir.exists()) {
			return false;
		} else {
			return true;
		}
	}

	// table Position section
	public void createTablePositionen()
	{ // create table position
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

		// create 25 demo/default data entries
		for (int i = 0; i < 25; i++) {
			fillPositionen(i + 1, "Noch frei", (float) 0.00, "#ad0000");
		}
	}

	public void createTableCategory()
	{ // create table position
		System.out.println("Erstelle Tabelle Kategorie");
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("DROP TABLE IF EXISTS category;");
			stmt.executeUpdate("CREATE TABLE category (id, name);");
		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
		}

		for(int i = 0; i < 5; i++) {
			fillCategory(i, "Standard");
		}
		
	}
	
	public String getCategoryName(int pID) {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, name FROM category WHERE id = " + pID + ";");
			return rs.getString("name");
		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
			return "Error 404";
		}
	}
	
	public void setCategoryName(int pID, String pName)
	{ // Setzte den Namen
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE category SET name = '" + pName
					+ "'WHERE id =" + pID + ";");
		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
		}
	}

	public void fillCategory(int pID, String pName)
	{

		System.out.println("Erstelle neuen Kategorie Eintrag");
		try {
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO category VALUES (?, ?);");
			ps.setInt(1, pID); // primary
			ps.setString(2, pName);
			ps.addBatch();
			connection.setAutoCommit(false);
			ps.executeBatch(); // SQL execution
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
		}

	}

	public void fillPositionen(int pID, String pName, float pValue,
			String pColor)
	{ // create new data in table
		System.out.println("Erstelle neuen positionen eintrag");
		try {
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO positionen VALUES (?, ?, ?, ?);");
			ps.setInt(1, pID); // primary
			ps.setString(2, pName);
			ps.setFloat(3, pValue);
			ps.setString(4, pColor);

			ps.addBatch();
			connection.setAutoCommit(false);
			ps.executeBatch(); // SQL execution
			connection.setAutoCommit(true);
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

	public ArrayList<tableDataPositionen> ladeTabellePositionen()
	{ // Gibt ein Objekt daten mit allen Einträgen der DB zurück
		ArrayList<tableDataPositionen> daten = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM positionen;");
			while (rs.next()) {
				try {
					// Entschlüsselte Daten werden als Datenobjekt gespeichert
					daten.add(new tableDataPositionen(rs.getInt("id"),
							rs.getString("name"), rs.getString("value"),
							rs.getString("color")));
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

	// table Jobs section
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

}
