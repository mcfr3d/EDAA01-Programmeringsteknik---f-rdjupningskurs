package phonebook;

import java.util.Optional;
import java.util.Set;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class PhoneBookGUI {
	private PhoneBook phoneBook;
	private BorderPane root;
	private TextArea textArea;

	public PhoneBookGUI(PhoneBook pb) {
		phoneBook = pb;

		textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setWrapText(true);
		textArea.setPrefColumnCount(50);
		textArea.setPrefRowCount(20);

		root = new BorderPane();
		root.setTop(makeMenu());
		root.setCenter(textArea);
		root.setBottom(makeQuit());

	}

	public Parent getRoot() {
		return root;
	}

	private Button makeQuit() {

		Button quitButton = new Button("Quit");
		quitButton.setOnAction(e -> quitPrg());
		return quitButton;

	}

	private void quitPrg() {
		Platform.exit();
	}

	private MenuBar makeMenu() {
		final Menu menuEdit = new Menu("Edit");
		final MenuItem menuAdd = new MenuItem("Add");
		menuAdd.setOnAction(e -> add());
		final MenuItem menuRemove = new MenuItem("Remove Person");
		menuRemove.setOnAction(e -> remove());
		final MenuItem menuRemoveNbr = new MenuItem("Remove Number");
		menuRemoveNbr.setOnAction(e -> removeNumber());
		menuEdit.getItems().addAll(menuAdd, menuRemove, menuRemoveNbr);

		final Menu menuFind = new Menu("Find");
		final MenuItem menuFindNbr = new MenuItem("Find number(s)");
		menuFindNbr.setOnAction(e -> findNbr());
		final MenuItem menuFindName = new MenuItem("Find name(s)");
		menuFindName.setOnAction(e -> findName());
		menuFind.getItems().addAll(menuFindNbr, menuFindName);
		final Menu menuView = new Menu("View");
		final MenuItem menuShowAll = new MenuItem("Show all");
		menuShowAll.setOnAction(e -> showAll());
		menuView.getItems().addAll(menuShowAll);

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(menuEdit, menuFind, menuView);
		// menuBar.setUseSystemMenuBar(true); // if you want operating system
		// rendered menus, uncomment this line
		return menuBar;
	}

	private void showAll() {
		textArea.setText(phoneBook.toString());
	}

	private void findName() {
		Optional<String> result = oneInputDialog("Find names(s)", "Find names with associated with the number",
				" enter telephone number");
		if (result.isPresent()) {
			String number = result.get();
			Set<String> names = phoneBook.findNames(number);
			StringBuilder sb = new StringBuilder();
			for(String s: names){
				sb.append(s + "\n");
			}
			if (sb.length() == 0){
				sb.append("No names with this number");
			}
			textArea.setText("The number " + number + " belongs to:\n"+ sb.toString());
	
		}}

	private void findNbr() {
		Optional<String> result = oneInputDialog("Find number(s)", "Search for a persons number",
				" enter name of person");
		if (result.isPresent()) {
			String nameOfPerson = result.get();
			Set<String> s = phoneBook.findNumber(nameOfPerson);
			StringBuilder sb = new StringBuilder();
			for (String numbr : s) {
				sb.append(numbr + "\n");
			}
			if (sb.length() == 0) {
				sb.append("No numbers for this contact");
			}
			textArea.setText(nameOfPerson + "'s phone numbers:\n " + sb.toString());
		}

	}

	private void add() {
		String[] s = { "Name", "Telephone number" };
		Optional<String[]> result = twoInputsDialog("Add contact", " Add contact information", s);
		if (result.isPresent()) {
			if (result.get().length == 2) {
				String name = result.get()[0];
				String number = result.get()[1];

				boolean worked = phoneBook.put(name, number);
				if (worked) {
					textArea.setText("Number : " + number +" added to contact : " + name + " in the phonebook");
				} else
					textArea.setText("Could not add number to contact: number already exists");
			}
		}

	}

	private void remove() {
		Optional<String> result = oneInputDialog("Remove Name", "Removes the contact and all associated numbers from the phonebook",
				" enter name of person");
		if (result.isPresent()) {
			String nameOfPerson = result.get();
			boolean removed= phoneBook.remove(nameOfPerson);
			if(removed){
				textArea.setText(nameOfPerson + " has been removed from contacts");
			}
			else textArea.setText("Error:\n Could not remove " + nameOfPerson + " name not found");
		}

	}

	private void removeNumber() {
		String[] s = { "Name", "Number" };
		Optional<String[]> result = twoInputsDialog("Remove Name",
				"Removes the contact and all associated numbers from the phonebook", s);
		if (result.isPresent()) {
			if (result.get().length == 2) {
				String nameOfPerson = result.get()[0];
				String numberToRemove = result.get()[1];
				boolean removed = phoneBook.removeNumber(nameOfPerson, numberToRemove);
				if (removed) {
					textArea.setText(
							"The number: " + numberToRemove + " has been removed from the contact " + nameOfPerson);
				} else
					textArea.setText("Error:\n Could not remove the number ");
			}
		}

	}

	private Optional<String> oneInputDialog(String title, String headerText, String label) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(title);
		dialog.setHeaderText(headerText);
		dialog.setContentText(label);

		// add content to dialog
		return dialog.showAndWait();
	}

	private Optional<String[]> twoInputsDialog(String title, String headerText, String[] labels) {
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle(title);
		dialog.setHeaderText(headerText);
		dialog.setResizable(true);
		Label label1 = new Label(labels[0] + ':');
		Label label2 = new Label(labels[1] + ':');
		TextField tf1 = new TextField();
		TextField tf2 = new TextField();
		GridPane grid = new GridPane();
		grid.add(label1, 1, 1);
		grid.add(tf1, 2, 1);
		grid.add(label2, 1, 2);
		grid.add(tf2, 2, 2);
		dialog.getDialogPane().setContent(grid);
		ButtonType buttonTypeOk = new ButtonType("Ok", ButtonData.OK_DONE);
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeCancel, buttonTypeOk);
		dialog.setResultConverter(new Callback<ButtonType, String>() {
			@Override
			public String call(ButtonType b) {
				String inputs = null;
				if (b == buttonTypeOk) {
					inputs = tf1.getText() + ":" + tf2.getText();

				}
				return inputs;
			}
		});
		tf1.requestFocus();

		Optional<String> result = dialog.showAndWait();
		String[] input = null;
		if (result.isPresent()) {
			input = result.get().split(":");
		}
		return Optional.ofNullable(input);
	}
}
