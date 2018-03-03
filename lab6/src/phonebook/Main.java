package phonebook;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	private PhoneBook phoneBook;
	private Stage primaryStage;
	
	/**
	 * The entry point for the Java program.
	 * @param args
	 */
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
		this.primaryStage = primaryStage;
		// Here you can place any action to be done when the application is opened, i.e. read phone book from file.
		phoneBook = new PhoneBook();
//		File open = new File("C://Users/Fredde laptop/workspace/edaa01-workspace/lab6//phonebook.pb");
		try {
	        FileChooser fileChooser = new FileChooser();
	        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileChooser.showOpenDialog(primaryStage)));
	        phoneBook = (PhoneBook) in.readObject();
	        in.close();
	        } catch (Exception e) {
	        e.printStackTrace();
	        System.exit(1);
	        }
		
		
		
		
		PhoneBookGUI gui = new PhoneBookGUI(phoneBook);

		Scene scene = new Scene(gui.getRoot());

		primaryStage.setTitle("PhoneBook");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void stop() {
		
		try {
			FileChooser fileChooser = new FileChooser();
			ObjectOutputStream out =
			new ObjectOutputStream(new FileOutputStream(fileChooser.showSaveDialog(primaryStage)));
			out.writeObject(phoneBook);
			out.close();
			} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
			}
		// Here you can place any action to be done when the application is closed, i.e. save phone book to file.
	}

}
