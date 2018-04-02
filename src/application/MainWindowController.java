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
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;

import javafx.scene.control.ChoiceBox;

//import com.sun.java.swing.action.NewAction;

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

import javax.security.auth.callback.Callback;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.TitlePaneLayout;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
	private AnchorPane paneDB;

	@FXML
	private TreeTableView<tableData> tableCurrentOrder;

	@FXML
	private TreeTableColumn<tableData, String> columnQuantity;

	@FXML
	private TreeTableColumn<tableData, String> columnPosition;

	@FXML
	private TreeTableColumn<tableData, Integer> idSpalte01 = new TreeTableColumn<>(
			"tableCurrentOrder");

	@FXML
	private TreeTableView<tableData> jobsTreeTable;

	@FXML
	private TreeTableColumn<tableData, String> columnJobValue;

	@FXML
	private TreeTableColumn<tableData, String> columnState;

	@FXML
	private TreeTableColumn<tableData, String> columnPositions;

	@FXML
	private TreeTableColumn<tableData, String> columnTime;

	@FXML
	private TreeTableColumn<tableData, String> columnJobNumber;

	@FXML
	private TreeTableColumn<tableData, Integer> idSpalte02 = new TreeTableColumn<>(
			"jobsTreeTable");

	@FXML
	private TreeTableView<tableDataPositionen> entryTreeTable;

	@FXML
	private TreeTableColumn<tableDataPositionen, String> columnColor;

	@FXML
	private TreeTableColumn<tableDataPositionen, String> columnPrize;

	@FXML
	private TreeTableColumn<tableDataPositionen, String> columnPositionsEdit;

	@FXML
	private TreeTableColumn<tableDataPositionen, Integer> columnPosnumber;

	@FXML
	private ChoiceBox<String> colorChoise;

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

	@FXML
	private Button btnDeleteSelectedPosition;

	@FXML
	private Button btnPrintBill;

	@FXML
	private Button btnLock;

	@FXML
	private Button btnReprintJob;

	@FXML
	private Button btnCancelJob;

	@FXML
	private Button btnCalcStats;

	@FXML
	private Button btnSaveEntry;

	@FXML
	private Button btnClearEntry;

	@FXML
	private Button btnCreateNewDatabase;

	@FXML
	private Button btnOpenFolder;

	@FXML
	private Label labelAllPrize;

	@FXML
	private Label labelJobCounter;

	@FXML
	private Label labelTime;

	@FXML
	private Label lableJobCount;

	@FXML
	private Label labelAvgJob;

	@FXML
	private Label lableAllValue;

	@FXML
	private Label lableNewPosition;

	@FXML
	private Label labelNewValue;

	@FXML
	private Label lableNewColor;

	@FXML
	private Label labelDBStatus;

	@FXML
	private Label labelDBName;

	@FXML
	private TitledPane titlePaneStats;

	@FXML
	private TextField tftNewPosition;

	@FXML
	private TextField tftNewValue;

	@FXML
	private TextField tftNewDBName;

	private Main main;

	private DBController dbc;

	private String filepathXMLLinux = System.getProperty("user.home")
			+ "/bin/jFxKasse/config.xml"; // Pfad wo die XML liegt

	private int idPositionen = 0;

	private String selectedColorName;

	private String databaseName;

	// private ObservableList<String> color =
	// FXCollections.observableArrayList("English", "Deutsch");

	@FXML
	TreeItem<tableData> rootCurrentJob = new TreeItem<>(
			new tableData(0, "0", "0"));

	@FXML
	TreeItem<tableData> rootJobs = new TreeItem<>(new tableData(0, "0", "0"));

	@FXML
	TreeItem<tableDataPositionen> rootPositionen = new TreeItem<>(
			new tableDataPositionen(0, "0", "0", "0"));

	Properties props = new Properties();

	@FXML
	public void ueberbtnAction(ActionEvent event)
	{ // Öffnet den Über-Dialog

		// Erstellt einen Dialog
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Über jFxKasse");
		dialog.setHeaderText(
				"Informationen und Lizenzen - Version 0.7 - UI Techdemo");

		// Erzeugt den Button
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);

		// Erzeugt die Textfelder und Label
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		grid.add(new Label("Datenbank: sqlite.org - Public Domain"
				+ " \nUI Design: eclipse.org/efxclipse/install.html - Eclipse Public License 1.0"
				+ " \nUI - Datenbank Integration: basierend auf Project-HomeFlix - github.com/Seil0/Project-HomeFlix - GPLv3 \n"
				+ " \nMaintainer: hendrik.schutter@coptersicht.de"
				+ " \n(c) 2018 Hendrik Schutter"), 0, 0);

		dialog.getDialogPane().setContent(grid); // Setzt die GridPane auf die
		dialog.setResizable(true); // DialogPane
		dialog.showAndWait();
	}

	@FXML
	public void btnOpenFolderAction(ActionEvent event) throws IOException
	{
		Runtime.getRuntime().exec(
				"xdg-open " + System.getProperty("user.home") + "/bin/jFxKasse");

	}

	@FXML
	public void btnCreateNewDatabaseAction(ActionEvent event) throws Exception
	{
		System.out.println("Button!");

		System.out.println(tftNewDBName.getText());

		setDatabaseName(tftNewDBName.getText());
		dbc.dbname = getDatabaseName();
		dbc.verbindeDatenbank(); // Verbindet mit der Datenbank-Datei
		dbc.erstelleTabellePositionen();
		dbc.erstelleTabelleJobs();

		try {
			saveSettings(getDatabaseName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setDBLabel();
		blockUI(false);
		fuelleTabllePositionen();
		initUI(); // Startet die UI

	}

	@FXML
	public void btnSaveEntryAction(ActionEvent event)
	{
		System.out.println("Speichere Eintrag!");

		dbc.setName(idPositionen, tftNewPosition.getText());
		dbc.setValue(idPositionen, tftNewValue.getText());
		dbc.setColor(idPositionen, getColorCodes(selectedColorName));

		fuelleTabllePositionen();

	}

	@FXML
	public void btnClearEntryAction(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void btnCalcStatsAction(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void btnReprintJobAction(ActionEvent event)
	{
		// System.out.println("fuelleTabellePositionen");
		// fuelleTabllePositionen();
	}

	@FXML
	public void btnCancelJobAction(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void btnLockAction(ActionEvent event)
	{
		System.out.println("Button!");
		dbc.ausgebenSysoPositionen();
	}

	@FXML
	public void btnDeleteSelectedPositionAction(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void btnPrintBillAction(ActionEvent event)
	{
		System.out.println("Button!");
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

	public void fuelleTabllePositionen()
	{ // Lädt die Datenbank in die Tabelle

		// dbc.setSchluessel(schluessel);

		rootPositionen.getChildren().remove(0,
				rootPositionen.getChildren().size());

		for (int i = 0; i < dbc.ladeTabellePositionen().size(); i++) {
			tableDataPositionen helpTableData = new tableDataPositionen(
					dbc.ladeTabellePositionen().get(i).getID(),
					dbc.ladeTabellePositionen().get(i).getName(),
					dbc.ladeTabellePositionen().get(i).getValue(),
					getColorNames(dbc.ladeTabellePositionen().get(i).getColor()));

			rootPositionen.getChildren()
					.add(new TreeItem<tableDataPositionen>(helpTableData));
		}
	}

	public void initUI()
	{
		System.out.println("initUI");

		tftNewDBName.setText(getDatabaseName());

		initPositionen();

	}

	private void initPositionen()
	{
		entryTreeTable.setRoot(rootPositionen);
		entryTreeTable.setShowRoot(false);
		entryTreeTable.setEditable(false);

		ObservableList<String> color = FXCollections.observableArrayList("Rot",
				"Orange", "Braun", "Weiß", "Gelb", "Gr\u00fcn", "Blau", "Indigo");
		colorChoise.setItems(color);
		colorChoise.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> ov,
							Number value, Number new_value)
					{
						selectedColorName = colorChoise.getItems()
								.get((int) new_value).toString();
						System.out.println("Ausgewählte Farbe: " + selectedColorName);
					}
				});

		columnPosnumber.setCellValueFactory(
				cellData -> cellData.getValue().getValue().idProperty().asObject());

		columnPositionsEdit.setCellValueFactory(
				cellData -> cellData.getValue().getValue().nameProperty());

		columnPrize.setCellValueFactory(
				cellData -> cellData.getValue().getValue().valueProperty());

		columnColor.setCellValueFactory(
				cellData -> cellData.getValue().getValue().colorProperty());

		entryTreeTable.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Object>() {
					@Override
					public void changed(ObservableValue<?> observable, Object oldVal,
							Object newVal)
					{
						// last = selected; //for auto-play
						int selected = entryTreeTable.getSelectionModel()
								.getSelectedIndex(); // get selected item
						idPositionen = columnPosnumber.getCellData(selected); // Ausgewählte
						// Spalte

						System.out.println(
								"Positionen - Ausgewaehlte Spalte: " + idPositionen);

						try { // Setzt den Inhalt in die Textfelder

							tftNewPosition.setText(dbc.getName(idPositionen));
							tftNewValue.setText(dbc.getValue(idPositionen));
							colorChoise.getSelectionModel()
									.select(getColorID(dbc.getColor(idPositionen)));

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

	public void saveSettings(String databasename) throws Exception
	{
		OutputStream outputStream; // new output-stream
		try {
			props.setProperty("databasename", databasename); // writes dbname into
																				// property

			outputStream = new FileOutputStream(filepathXMLLinux);

			props.storeToXML(outputStream, "jFxKasse settings"); // writes new .xml
			outputStream.close();
		} catch (IOException e) {
		}
	}

	public boolean loadSettings() throws Exception
	{ // Ladt die Daten aus der XML
		InputStream inputStream;
		try {

			inputStream = new FileInputStream(filepathXMLLinux);

			props.loadFromXML(inputStream);
			setDatabaseName(props.getProperty("databasename"));
			// = crypo.entschluesseln(props.getProperty("key"),
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

	public void starteDB()
	{ // Startet die Datenbank
		dbc.verbindeDatenbank();
	}

	public String getDatabaseName()
	{
		return databaseName;
	}

	public void setDatabaseName(String NewDatabaseName)
	{
		databaseName = NewDatabaseName;
	}

	public void setDBLabel() throws Exception
	{
		if (loadSettings() == true) {
			labelDBStatus
					.setText("Geladene Datenbank: " + getDatabaseName() + ".db");
			btnCreateNewDatabase.setDisable(true);
			tftNewDBName.setDisable(true);
			labelDBName.setTooltip(new Tooltip(
					"Um eine neue Datenbank zu erzeugen muss die vorherige config.xml und "
							+ getDatabaseName()
							+ ".db gelöscht werden! Anwendung danach neustarten!"));
			labelDBStatus.setTooltip(new Tooltip(
					"Um eine neue Datenbank zu erzeugen muss die vorherige config.xml und "
							+ getDatabaseName()
							+ ".db gelöscht werden! Anwendung danach neustarten!"));
		} else {
			labelDBStatus.setText("Keine Datenbank gefunden!");
		}

	}

	private String getColorCodes(String pColorName)
	{
		switch (pColorName) {
		case "Rot":
			return "#FF0000";
		case "Orange":
			return "#FF4500";
		case "Braun":
			return "#8B4513";
		case "Weiß":
			return "#FAF0E6";
		case "Gelb":
			return "#FFD700";
		case "Gr\u00fcn":
			return "#556B2F";
		case "Blau":
			return "#00BFFF";
		case "Indigo":
			return "#4B0082";

		default:
			return "#FFFFFF";
		}
	}

	private String getColorNames(String pColorCode)
	{
		switch (pColorCode) {
		case "#FF0000":
			return "Rot";
		case "#FF4500":
			return "Orange";
		case "#8B4513":
			return "Braun";
		case "#FAF0E6":
			return "Weiß";
		case "#FFD700b":
			return "Gelb";
		case "#556B2F":
			return "Gr\u00fcn";
		case "#00BFFF":
			return "Blau";
		case "#4B0082":
			return "Indigo";

		default:
			return "Farbe";
		}
	}

	private Integer getColorID(String pColorCode)
	{
		switch (pColorCode) {
		case "#FF0000":
			return 0;
		case "#FF4500":
			return 1;
		case "#8B4513":
			return 2;
		case "#FAF0E6":
			return 3;
		case "#FFD700b":
			return 4;
		case "#556B2F":
			return 5;
		case "#00BFFF":
			return 6;
		case "#4B0082":
			return 7;

		default:
			return 0;
		}

	}

	public void blockUI(boolean pState)
	{

		btnCalcStats.setDisable(pState);
		btnClearEntry.setDisable(pState);
		btnDeleteSelectedPosition.setDisable(pState);
		btnPrintBill.setDisable(pState);
		btnReprintJob.setDisable(pState);
		btnSaveEntry.setDisable(pState);
		btnCancelJob.setDisable(pState);
		gridButton01.setDisable(pState);
		gridButton02.setDisable(pState);
		gridButton03.setDisable(pState);
		gridButton04.setDisable(pState);
		gridButton05.setDisable(pState);
		gridButton06.setDisable(pState);
		gridButton07.setDisable(pState);
		gridButton08.setDisable(pState);
		gridButton09.setDisable(pState);
		gridButton10.setDisable(pState);
		gridButton11.setDisable(pState);
		gridButton12.setDisable(pState);
		gridButton13.setDisable(pState);
		gridButton14.setDisable(pState);
		gridButton15.setDisable(pState);
		gridButton16.setDisable(pState);
		gridButton17.setDisable(pState);
		gridButton18.setDisable(pState);
		gridButton19.setDisable(pState);
		gridButton20.setDisable(pState);
		gridButton21.setDisable(pState);
		gridButton22.setDisable(pState);
		gridButton23.setDisable(pState);
		gridButton24.setDisable(pState);
		gridButton25.setDisable(pState);

		tftNewPosition.setDisable(pState);
		tftNewValue.setDisable(pState);
		colorChoise.setDisable(pState);

		tableCurrentOrder.setDisable(pState);
		jobsTreeTable.setDisable(pState);
		entryTreeTable.setDisable(pState);

		labelAllPrize.setVisible(!pState);
		labelJobCounter.setVisible(!pState);

		titlePaneStats.setVisible(!pState);

	}

}
