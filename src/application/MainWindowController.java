package application;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.control.ChoiceBox;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class MainWindowController
{

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
	private TreeTableColumn<tableDataPositionen, String> columnCat;

	@FXML
	private TreeTableColumn<tableDataPositionen, String> columnPrize;

	@FXML
	private TreeTableColumn<tableDataPositionen, String> columnPositionsEdit;

	@FXML
	private TreeTableColumn<tableDataPositionen, Integer> columnPosnumber;

	@FXML
	private ChoiceBox<String> colorChoise;

	@FXML
	private ChoiceBox<String> catChoise;

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
	private Label labelCat01;

	@FXML
	private Label labelCat02;

	@FXML
	private Label labelCat05;

	@FXML
	private Label labelCat04;

	@FXML
	private Label labelCat03;

	@FXML
	private JFXTextField tftKat01;

	@FXML
	private JFXTextField tftKat02;

	@FXML
	private JFXTextField tftKat03;

	@FXML
	private JFXTextField tftKat04;

	@FXML
	private JFXTextField tftKat05;

	@FXML
	private Button btnSaveCat;

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
	private Label lableSelectCat;

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
	private TitledPane titlePaneCat;

	@FXML
	private TextField tftNewPosition;

	@FXML
	private TextField tftNewValue;

	@FXML
	private TextField tftNewDBName;

	@SuppressWarnings("unused")
	private Main main;

	private DBController dbc;

	private String filepathXMLLinux = System.getProperty("user.home")
			+ "/bin/jFxKasse/config.xml"; // Pfad wo die XML liegt

	private int idPositionen = 0;

	private String selectedColorName;

	private String selectedCatName;

	private String databaseName;

	private boolean lockState = false;

	@FXML
	TreeItem<tableData> rootCurrentJob = new TreeItem<>(
			new tableData(0, "0", "0"));

	@FXML
	TreeItem<tableData> rootJobs = new TreeItem<>(new tableData(0, "0", "0"));

	@FXML
	TreeItem<tableDataPositionen> rootPositionen = new TreeItem<>(
			new tableDataPositionen(0, "0", "0", "0", "0"));

	Properties props = new Properties();

	@FXML
	public void ueberbtnAction(ActionEvent event)
	{ // opens the 'Über' dialog

		// creates a dialog
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Über jFxKasse");
		dialog.setHeaderText(
				"Informationen und Lizenzen - Version 0.9.1 - Techdemo");

		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		grid.add(new Label(
				"Einfaches Kassensystem für kleine bis mittel große Veranstaltungen mit Bon-Drucker\n"
						+ "\nUnter Lizenz GPL-3.0 abrufbar auf https://github.com/Windoofs/jFxKasse\n"
						+ "\nDatenbank: sqlite.org - Public Domain"
						+ " \nUI Design01: eclipse.org/efxclipse/install.html - Eclipse Public License 1.0"
						+ " \nUI Design02: http://www.jfoenix.com/ -  Apache License 2.0"
						+ " \nUI - Datenbank Integration: basierend auf Project-HomeFlix - github.com/Seil0/Project-HomeFlix - GPLv3 \n"
						+ " \nMaintainer: hendrik.schutter@coptersicht.de"
						+ " \n(c) 2018 Hendrik Schutter"),
				0, 0);

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
		setDatabaseName(tftNewDBName.getText());
		dbc.dbname = getDatabaseName();
		dbc.connectDatabase(); // establish DB connection
		dbc.createTablePositionen(); // Create new table
		dbc.erstelleTabelleJobs(); // Create new table
		dbc.createTableCategory(); // Create new table
		try {
			saveSettings(getDatabaseName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDBLabel(); // Set new databese labels
		blockUI(false); // unlock UI elements that need DB
		fillTablePositionen(); // fill TreeTable 'Positionen'
		fillCategory();
		initUI(); // Starting the UI elements

	}

	@FXML
	public void btnSaveEntryAction(ActionEvent event)
	{
		System.out.println("Speichere Eintrag!");

		dbc.setName_Positionen(idPositionen, tftNewPosition.getText());
		dbc.setValue_Positionen(idPositionen, tftNewValue.getText());
		dbc.setColor_Positionen(idPositionen, getColorCodes(selectedColorName));

		System.out.println("refill pos");
		fillTablePositionen(); // fill TreeTable 'Positionen'
		loadGridButtons();

	}

	@FXML
	public void btnClearEntryAction(ActionEvent event)
	{
		// set default values
		dbc.setName_Positionen(idPositionen, "Noch frei");
		dbc.setValue_Positionen(idPositionen, "0.00");
		dbc.setColor_Positionen(idPositionen, "#FAF0E6");

		fillTablePositionen(); // fill TreeTable 'Positionen'
	}

	@FXML
	public void btnCalcStatsAction(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void btnReprintJobAction(ActionEvent event)
	{

	}

	@FXML
	public void btnCancelJobAction(ActionEvent event)
	{
		System.out.println("Button!");
	}

	@FXML
	public void btnLockAction(ActionEvent event)
	{
		lockState = !lockState;

		blockUI(lockState);

		if (lockState) {
			btnLock.setText("Kasse entsperren");
		} else {
			btnLock.setText("Kasse sperren");
		}

	}

	@FXML
	public void btnSaveCatAction(ActionEvent event)
	{
		System.out.println("Cat´s speichern");

		dbc.setName_Category(1, tftKat01.getText());
		dbc.setName_Category(2, tftKat02.getText());
		dbc.setName_Category(3, tftKat03.getText());
		dbc.setName_Category(4, tftKat04.getText());
		dbc.setName_Category(5, tftKat05.getText());

		fillCategory();
		fillTablePositionen();
		getSelectedCat();

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
		System.out.println("Test Button!");

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
	public void fillTablePositionen()
	{ // loads the table in the TreeTableView

		rootPositionen.getChildren().remove(0,
				rootPositionen.getChildren().size());

		for (int i = 0; i < dbc.ladeTabellePositionen().size(); i++) {
			tableDataPositionen helpTableData = new tableDataPositionen(
					dbc.ladeTabellePositionen().get(i).getID(),
					dbc.ladeTabellePositionen().get(i).getName(),
					dbc.ladeTabellePositionen().get(i).getValue() + " €",

					// dbc.ladeTabellePositionen().get(i).getCat(),
					// dbc.getCategoryName(dbc.ladeTabellePositionen().get(i).getCat()))
					dbc.getCategoryNameFromPositionen(i + 1),

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

	public int getSelectedCat()
	{

		ObservableList<String> cats = FXCollections.observableArrayList();

		cats.add(dbc.getName_Category(1));
		cats.add(dbc.getName_Category(2));
		cats.add(dbc.getName_Category(3));
		cats.add(dbc.getName_Category(4));
		cats.add(dbc.getName_Category(5));

		catChoise.setItems(cats);
		catChoise.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> ov,
							Number value, Number new_value)
					{
						selectedCatName = catChoise.getItems().get((int) new_value)
								.toString();

						System.out.println("Ausgewählte Cat: " + selectedCatName);
					}
				});

		for (int i = 1; i < 5; i++) {
			if (selectedCatName == dbc.getName_Category(i)) {
				return i;
			}
		}

		return -1;
	}

	public void fillCategory()
	{
		tftKat01.setText(dbc.getName_Category(1));
		tftKat02.setText(dbc.getName_Category(2));
		tftKat03.setText(dbc.getName_Category(3));
		tftKat04.setText(dbc.getName_Category(4));
		tftKat05.setText(dbc.getName_Category(5));
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

		columnCat.setCellValueFactory(
				cellData -> cellData.getValue().getValue().catProperty());

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

							tftNewPosition
									.setText(dbc.getName_Positionen(idPositionen));
							tftNewValue.setText(dbc.getValue_Positionen(idPositionen));
							colorChoise.getSelectionModel().select(
									getColorID(dbc.getColor_Positionen(idPositionen)));

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});

		columnPosnumber.setStyle("-fx-alignment: CENTER;");
		columnPositionsEdit.setStyle("-fx-alignment: CENTER;");
		columnPrize.setStyle("-fx-alignment: CENTER;");
		columnCat.setStyle("-fx-alignment: CENTER;");
		columnColor.setStyle("-fx-alignment: CENTER;");

		tftNewValue.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue)
			{
				if (!newValue.matches("\\d{0,7}([\\.]\\d{0,2})?")) {
					tftNewValue.setText(oldValue);
				}
			}
		});

		final Tooltip tooltipName = new Tooltip();
		tooltipName.setText("Name der neuen Position");
		tooltipName.setStyle("-fx-font: normal bold 20 Cantarell; "
				+ "-fx-base: #AE3522; " + "-fx-text-fill: orange;");
		tftNewPosition.setTooltip(tooltipName);

		final Tooltip tooltipValue = new Tooltip();
		tooltipValue
				.setText("Preis der neuen Position.\nPunkt als Trennzeichen!");
		tooltipValue.setStyle("-fx-font: normal bold 20 Cantarell; "
				+ "-fx-base: #AE3522; " + "-fx-text-fill: orange;");
		tftNewValue.setTooltip(tooltipValue);
		labelNewValue.setTooltip(tooltipValue);

	}

	public void setMain(Main main, DBController dbc)
	{
		this.main = main;
		this.dbc = dbc;
	}

	public String getSystemTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Date date = new Date();
		String heutigesDatum = dateFormat.format(date);
		return heutigesDatum;
	}

	public void saveSettings(String databasename) throws Exception
	{ // Save settings to config.xml
		OutputStream outputStream;
		try {
			props.setProperty("databasename", databasename);
			outputStream = new FileOutputStream(filepathXMLLinux);
			props.storeToXML(outputStream, "jFxKasse settings");
			outputStream.close();
		} catch (IOException e) {
		}
	}

	public boolean loadSettings() throws Exception
	{ // reads the settings from config.xml
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(filepathXMLLinux);
			props.loadFromXML(inputStream);
			setDatabaseName(props.getProperty("databasename"));
			inputStream.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
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
			return "#ad0000";
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
		case "#ad0000":
			return "Rot";
		case "#FF4500":
			return "Orange";
		case "#8B4513":
			return "Braun";
		case "#FAF0E6":
			return "Weiß";
		case "#FFD700":
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
		case "#ad0000":
			return 0;
		case "#FF4500":
			return 1;
		case "#8B4513":
			return 2;
		case "#FAF0E6":
			return 3;
		case "#FFD700":
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

		for (int i = 0; i < 25; i++) {
			getButtonByID(i).setDisable(pState);
		}

		tftNewPosition.setDisable(pState);
		tftNewValue.setDisable(pState);
		colorChoise.setDisable(pState);

		tableCurrentOrder.setDisable(pState);
		jobsTreeTable.setDisable(pState);
		entryTreeTable.setDisable(pState);

		labelAllPrize.setVisible(!pState);
		labelJobCounter.setVisible(!pState);

		titlePaneStats.setVisible(!pState);

		titlePaneCat.setDisable(pState);

	}

	public void loadGridButtons()
	{

		for (int i = 0; i < 25; i++) {

			getButtonByID(i).setText(dbc.getName_Positionen(i + 1));

			if ((getColorID(dbc.getColor_Positionen(i + 1)) == 0)
					|| (getColorID(dbc.getColor_Positionen(i + 1)) == 7)) {
				getButtonByID(i).setStyle("-fx-background-color: "
						+ dbc.getColor_Positionen(i + 1) + "; -fx-text-fill: white;");
			} else {
				getButtonByID(i).setStyle("-fx-background-color: "
						+ dbc.getColor_Positionen(i + 1) + "; -fx-text-fill: black;");
			}

		}

		for (int i = 0; i < 25; i++) {

			if (dbc.getName_Positionen(i + 1).equals("Noch frei")) {
				getButtonByID(i).setVisible(false);
			} else {
				getButtonByID(i).setVisible(true);
			}

		}

	}

	public Button getButtonByID(int pID)
	{
		switch (pID) {
		case 0:
			return gridButton01;
		case 1:
			return gridButton02;
		case 2:
			return gridButton03;
		case 3:
			return gridButton04;
		case 4:
			return gridButton05;
		case 5:
			return gridButton06;
		case 6:
			return gridButton07;
		case 7:
			return gridButton08;
		case 8:
			return gridButton09;
		case 9:
			return gridButton10;
		case 10:
			return gridButton11;
		case 11:
			return gridButton12;
		case 12:
			return gridButton13;
		case 13:
			return gridButton14;
		case 14:
			return gridButton15;
		case 15:
			return gridButton16;
		case 16:
			return gridButton17;
		case 17:
			return gridButton18;
		case 18:
			return gridButton19;
		case 19:
			return gridButton20;
		case 20:
			return gridButton21;
		case 21:
			return gridButton22;
		case 22:
			return gridButton23;
		case 23:
			return gridButton24;
		case 24:
			return gridButton25;
		default:
			return gridButton01;
		}
	}

	public void updateTimeLabel()
	{
		// System.out.println(getSystemTime());
		labelTime.setText("Uhrzeit: " + getSystemTime());
	}

}
