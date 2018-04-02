package application;

import java.io.File;
import java.io.FileInputStream;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class Main extends Application
{
	private MainWindowController mwc;

	private DBController dbc = new DBController(this);

	private String schluesselNutzer; // Passwort des Nutzers

	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
		mainWindow();
		//this.primaryStage.setResizable(false);
	}

	private void mainWindow()
	{
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("MainWindow.fxml"));
			AnchorPane pane = loader.load();
			// Test
			//Test
			//primaryStage.setWidth(1366);
			//primaryStage.setHeight(732);
			primaryStage.setTitle("jFxKasse"); // Title der Stage

			mwc = loader.getController();
			mwc.setMain(this, dbc);

			firstStart(); // Prüft ob das Programm zuvor gestartet wurde
		
			
			
		//	dbc.main(); // Startet die Datenbank
			
			//mwc.fuelleTablle(); // Ladt die Einträge in die Tabelle

			Scene scene = new Scene(pane);
			scene.getStylesheets()
					.add(Main.class.getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show(); // zeigt die Stage an
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	private void firstStart() throws Exception
	{
		
		if (mwc.loadSettings()) { // Wenn XML gefunden
			System.out.println("XML gefunden!");
			mwc.initUI(); // Startet die UI
			mwc.setDBLabel();
			dbc.dbname = mwc.getDatabaseName();
			dbc.verbindeDatenbank(); // Verbindet mit der Datenbank-Datei
			mwc.fuelleTabllePositionen();
			
			
		} else { // Wenn keine XML gefunden --> erster Start
			System.out.println("keine XML gefunden!");
			mwc.blockUI(true);
			if (System.getProperty("os.name").equals("Linux")) {

				File dir = new File(
						System.getProperty("user.home") + "/bin/jFxKasse"); // Erstellt
																								// den
																								// Unterordner
				dir.mkdir(); // Erstellt den Unterordner
			} else {
				File dir = new File("C:/ProgramData/jFxKasse/"); // Erstellt den
																					// Unterordner
				dir.mkdir(); // Erstellt den Unterordner
			}

		//	mwc.saveSettings(mwc.getDatabaseName(), "dd"); // speichert das Passwort und
																	// den Individueller
																	// Schlüssel für die API in
																	// der XML
			//dbc.verbindeDatenbank(); // Verbindet mit der Datenbank-Datei
			//dbc.erstelleDatenbank(); // Neue Datenbank-Datei wird erstellt
			//System.exit(0); // Programm wird beendet
		}
	}

}
