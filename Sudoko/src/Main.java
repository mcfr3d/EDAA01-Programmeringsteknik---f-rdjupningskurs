
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		// launch() do the following:
		// - creates an instance of the Main class
		// - calls Main.init()
		// - create and start the javaFX application thread
		// - waits for the javaFX application to finish (close all windows)
		// the javaFX application thread do:
		// - calls Main.start(Stage s)
		// - runs the event handling loop
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		SudokoGUI gui = new SudokoGUI();
		Scene scene = new Scene(gui.getRoot());
		primaryStage.setTitle("Sudoko");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
