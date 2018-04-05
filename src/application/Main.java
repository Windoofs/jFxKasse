package application;

import java.io.File;
import java.io.FileInputStream;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.util.Duration;
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

	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
		mainWindow();
	}

	private void mainWindow()
	{
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("MainWindow.fxml"));
			AnchorPane pane = loader.load();
			primaryStage.setTitle("jFxKasse"); // Title of window

			mwc = loader.getController();
			mwc.setMain(this, dbc);

			firstStart();

			Scene scene = new Scene(pane);
			scene.getStylesheets()
					.add(Main.class.getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show(); // shows the stage

			Timeline timeline = new Timeline(
					new KeyFrame(Duration.seconds(1), ev -> {
						mwc.updateTimeLabel(); //update time
					}));
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	/**
	 * Checks if the config.xml is preset.
	 * @author hendrik
	 */
	private void firstStart() throws Exception
	{
		if (mwc.loadSettings()) {
			// config.xml found, app starting normal
			System.out.println("XML gefunden!");
			mwc.initUI(); // Starting the UI elements
			mwc.setDBLabel(); // Set databese labels
			dbc.dbname = mwc.getDatabaseName(); // handover database name
			dbc.connectDatabase(); // estabishing DB conection
			mwc.fillTablePositionen(); // fill TreeTable 'Positionen'
			mwc.loadGridButtons();
		} else {
			// config.xml NOT found, first start of app
			System.out.println("keine XML gefunden!");
			mwc.blockUI(true); // disable UI elements that need DB
			File dir = new File(System.getProperty("user.home") + "/bin/jFxKasse");
			dir.mkdir(); // Create new Subfolder
		}
	}
}
