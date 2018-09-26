package application;

import java.io.File;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


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
						mwc.updateTimeLabel(); // update time
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
			mwc.fillCategory();
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
