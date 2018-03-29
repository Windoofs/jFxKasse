package application;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.util.Optional;
import java.util.Properties;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class MainWindowController
{
	private static final String String = null;

	Toolkit toolkit = Toolkit.getDefaultToolkit();

	Clipboard clipboard = toolkit.getSystemClipboard();

	@FXML
	private AnchorPane mainAnchorpane;

	@FXML
	private TreeTableView<tableData> mainTreeTable;

	@FXML
	private TreeTableColumn<tableData, String> datumSpalte;

	@FXML
	private TreeTableColumn<tableData, String> kontoSpalte;

	@FXML
	private TreeTableColumn<tableData, Integer> idSpalte = new TreeTableColumn<>(
			"");

	@FXML
	private Button ueberbtn;

	@FXML
	private Button gridButton01;

	@FXML
	private Button gridButton02;

	@FXML
	private Button gridButton03;

	@FXML
	private Button gridButton04;

	@FXML
	private Button gridButton05;

	@FXML
	private Button gridButton06;

	@FXML
	private Button gridButton07;

	@FXML
	private Button gridButton08;

	@FXML
	private Button gridButton09;

	@FXML
	private Button gridButton10;

	@FXML
	private Button gridButton11;

	@FXML
	private Button gridButton12;

	@FXML
	private Button gridButton13;

	@FXML
	private Button gridButton14;

	@FXML
	private Button gridButton15;

	@FXML
	private Button gridButton16;

	@FXML
	private Button gridButton17;

	@FXML
	private Button gridButton18;

	@FXML
	private Button gridButton19;

	@FXML
	private Button gridButton20;

	@FXML
	private Button gridButton21;

	@FXML
	private Button gridButton22;

	@FXML
	private Button gridButton23;

	@FXML
	private Button gridButton24;

	@FXML
	private Button gridButton25;

	private Main main;

	private DBController dbc;

	private String filepathXMLWin = "C:/ProgramData/PWMaster/config.xml"; // Pfad
																									// wo
																									// die
																									// XML
																									// liegt

	private String filepathXMLLinux = System.getProperty("user.home")
			+ "/bin/PWMaster/config.xml"; // Pfad wo die XML liegt

	private boolean showPasswort = false;

	private String schluessel;

	private String base32Secret;

	private int id;

	@FXML
	TreeItem<tableData> root = new TreeItem<>(new tableData(0, "0", "0"));

	Properties props = new Properties();

	@FXML
	public void ueberbtnAction(ActionEvent event)
	{ // Öffnet den Über-Dialog

		// Erstellt einen Dialog
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Über PWMaster");
		dialog.setHeaderText("Informationen und Lizenzen - Version 0.5");

		// Erzeugt den Button
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);

		// Erzeugt die Textfelder und Label
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		grid.add(new Label("Datenbank: sqlite.org - Public Domain"
				+ "\nBASE64Decoder: java2s.com/Code/Jar/s/DownloadsunmiscBASE64Decoderjar.htm - GPLv2"
				+ "\nCrypo: blog.axxg.de/ - Copyright  2013 AxxG  Alexander Grösel"
				+ " \nzwei Faktoren: github.com/j256/two-factor-auth - ISC License"
				+ " \nUI Design: eclipse.org/efxclipse/install.html - Eclipse Public License 1.0"
				+ " \nUI - Datenbank Integration: basierend auf Project-HomeFlix - github.com/Seil0/Project-HomeFlix - GPLv3 \n"
				+ " \nMaintainer: hendrik.schutter@icloud.com"
				+ " \n(c) 2017 Hendrik Schutter"), 0, 0);

		dialog.getDialogPane().setContent(grid); // Setzt die GridPane auf die
																// DialogPane
		dialog.showAndWait();
	}

	@FXML
	public void gridButton01Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton02Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton03Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton04Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton05Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton06Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton07Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton08Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton09Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton10Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton11Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton12Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton13Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton14Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton15Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton16Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton17Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton18Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton19Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton20Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton21Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton22Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton23Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton24Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void gridButton25Action(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML

	public void fuelleTablle()
	{ // Lädt die Datenbank in die Tabelle

		dbc.setSchluessel(schluessel);
		for (int i = 0; i < dbc.ladeTabelle().size(); i++) {
			tableData helpTableData = new tableData(
					dbc.ladeTabelle().get(i).getID(),
					dbc.ladeTabelle().get(i).getDatum(),
					dbc.ladeTabelle().get(i).getKonto());
			root.getChildren().add(new TreeItem<tableData>(helpTableData));
		}
	}

	public void initUI()
	{
		mainTreeTable.setRoot(root);
		mainTreeTable.setShowRoot(false);
		mainTreeTable.setEditable(false);
		// Setzt die Textfelder

		idSpalte.setCellValueFactory(
				cellData -> cellData.getValue().getValue().idProperty().asObject());
		datumSpalte.setCellValueFactory(
				cellData -> cellData.getValue().getValue().datumProperty());
		kontoSpalte.setCellValueFactory(
				cellData -> cellData.getValue().getValue().kontoProperty());
		mainTreeTable.getColumns().add(idSpalte);
		mainTreeTable.getColumns().get(2).setVisible(false);
		mainTreeTable.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Object>() {
					@Override
					public void changed(ObservableValue<?> observable, Object oldVal,
							Object newVal)
					{
						// last = selected; //for auto-play
						int selected = mainTreeTable.getSelectionModel()
								.getSelectedIndex(); // get selected item
						id = idSpalte.getCellData(selected); // Ausgewählte Spalte
						showPasswort = false;

						try { // Setzt den entschlüsselten Inhalt in die Textfelder
							// tf01.setText("Verschlüsseltes Passwort von " +
							// crypo.entschluesseln(dbc.getKonto(id), schluessel) + " :
							// " + dbc.getPasswort(id));
							// tfNutzername.setText(crypo.entschluesseln(dbc.getNutzername(id),
							// schluessel));
							// tfEmail.setText(crypo.entschluesseln(dbc.getEmail(id),
							// schluessel));
							// tfPasswort.setText(crypo.entschluesseln(dbc.getPasswort(id),
							// schluessel));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
	}

	public void setMain(Main main, DBController dbc)
	{
		this.main = main;
		this.dbc = dbc;
	}

	public String getSystemDatum()
	{ // Gibt das System-Datum zurück
		java.util.Date now = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"dd.MM.yyyy");
		String heutigesDatum = sdf.format(now);
		return heutigesDatum;
	}

	public void saveSettings(String schluessel, String base32Secret)
			throws Exception
	{
		OutputStream outputStream; // new output-stream
		try {
			// props.setProperty("key", crypo.verschluesseln(schluessel,
			// crypo.getProgrammSchluessel())); //writes path into property
			// props.setProperty("TOTPkey", crypo.verschluesseln(base32Secret,
			// crypo.getProgrammSchluessel())); //writes path into property
			if (System.getProperty("os.name").equals("Linux")) {
				outputStream = new FileOutputStream(filepathXMLLinux);
			} else {
				outputStream = new FileOutputStream(filepathXMLWin);
			}
			props.storeToXML(outputStream, "PWMaster settings"); // writes new .xml
			outputStream.close();
		} catch (IOException e) {
		}
	}

	public boolean loadSettings() throws Exception
	{ // Ladt die Daten aus der XML
		InputStream inputStream;
		try {
			if (System.getProperty("os.name").equals("Linux")) {
				inputStream = new FileInputStream(filepathXMLLinux);
			} else {
				inputStream = new FileInputStream(filepathXMLWin);
			}
			props.loadFromXML(inputStream);
			// schluessel = crypo.entschluesseln(props.getProperty("key"),
			// crypo.getProgrammSchluessel()); //liest schluessel von property
			// base32Secret = crypo.entschluesseln(props.getProperty("TOTPkey"),
			// crypo.getProgrammSchluessel()); //liest schluessel von property
			inputStream.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getSchluesselXML()
	{ // Gibt den Schlüssel zurück für die Main
		return schluessel;
	}

	public void starteDB()
	{ // Startet die Datenbank
		dbc.verbindeDatenbank();
	}

	public void erzeugeDB()
	{ // Erzeuge die Datenbank
		dbc.erstelleDatenbank();
		dbc.verbindeDatenbank();
	}

	public String getbase32Secret()
	{ // Gibt den base32Secret zurück für die Main
		return base32Secret;
	}
}
