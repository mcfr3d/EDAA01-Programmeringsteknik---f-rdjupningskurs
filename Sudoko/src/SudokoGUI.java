
import java.util.ArrayList;
import java.util.Iterator;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;

public class SudokoGUI {
	private BorderPane root;
	private Group group;
	private SudukoSolver ss;
	private TilePane tile;
	private ArrayList<OneLetterTextField> textFieldBoard;

	/**
	 * Makes a new SudokoGUI
	 */

	public SudokoGUI() {
		ss = new SudukoSolver();
		root = new BorderPane();
		group = makeTilePane();
		tile = makeBottomTilePane();
		root.setCenter(group);
		root.setBottom(tile);
	}

	/**
	 * Private method for making the bottom panel
	 * 
	 * @return bottom panel
	 */

	
	
	private TilePane makeBottomTilePane() {
		TilePane tile = new TilePane();
		tile.setHgap(3);
		tile.setVgap(1);
		tile.setAlignment(Pos.CENTER);
		tile.getChildren().add(makeQuit());
		tile.getChildren().add(makeSolveButton());
		tile.getChildren().add(makeClearButton());
		return tile;
	}

	/**
	 * Makes the clear button and returns it *
	 * 
	 * @return the clearbutton
	 */

	private Button makeClearButton() {
		Button clearButton = new Button("Clear");
		clearButton.setPrefSize(60, 30);
		clearButton.setOnAction(e -> clearEvent());
		return clearButton;
	}

	/**
	 * the event function for the clear button
	 */
	private void clearEvent() {
		Iterator<OneLetterTextField> itr = textFieldBoard.iterator();
		while (itr.hasNext()) {
			itr.next().clear();
		}
	}

	/**
	 * makes the sovle button and returns it
	 * 
	 * @return the solve button
	 */
	private Button makeSolveButton() {
		Button solveButton = new Button("Solve");
		solveButton.setPrefSize(60, 30);
		solveButton.setOnAction(e -> solveEvent());
		return solveButton;
	}

	/**
	 * The event function for the solve button
	 */
	private void solveEvent() {
		Iterator<OneLetterTextField> itr = textFieldBoard.iterator();
		int row = 0;
		int col = 0;
		int[][] gameBoard = new int[9][9];
		while (itr.hasNext()) {
			String num = itr.next().getText();
			if (num.length() == 0) {
				gameBoard[row][col] = 0;
			} else {
				gameBoard[row][col] = Integer.parseInt(num);
			}
			col++;
			if (col > 8) {
				row++;
				col = 0;
			}
		}
		ss.setBoard(gameBoard);
		boolean solved = ss.solve();
		if (solved) {
			Iterator<OneLetterTextField> itr2 = textFieldBoard.iterator();
			for (int[] bRow : gameBoard) {
				for (int n : bRow) {
					itr2.next().setText(Integer.toString(n));
				}
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Solution");
			alert.setHeaderText(null);
			alert.setContentText(
					"There is no solution for the Sudoku you have given, please check that the numbers are correct and try again");
			alert.showAndWait();
		}
	}

	/**
	 * returns the root of the GUI
	 * 
	 * @return GUI root
	 */

	public Parent getRoot() {
		return root;
	}

	/**
	 * Makes the sudoku grid
	 * 
	 * @return the sudoku gird
	 */
	private Group makeTilePane() {
		TilePane tilePane = new TilePane();
		tilePane.setPrefRows(9);
		tilePane.setPrefColumns(9);
		tilePane.setVgap(5);
		tilePane.setHgap(5);
		textFieldBoard = new ArrayList<OneLetterTextField>();
		final int SIZE = 40;
		for (int blockRow = 1; blockRow <= 3; blockRow++) {
			for (int row = 1; row <= 3; row++) {
				for (int blockColumn = 1; blockColumn <= 3; blockColumn++) {
					for (int col = 1; col <= 3; col++) {
						OneLetterTextField textField = new OneLetterTextField();
						textField.setPrefSize(SIZE, SIZE);
						textField.setStyle("-fx-font-size: 18px;" + "-fx-font-weight: bold;");
						if (blockRow != 2 && blockColumn != 2 || blockRow == 2 && blockColumn == 2) {
							textField.setStyle("-fx-font-size: 18px;" + "-fx-font-weight: bold;"
									+ "-fx-background-color: #00FF00;");
						} else {
							textField.setStyle("-fx-font-size: 18px;" + "-fx-font-weight: bold;"
									+ "-fx-background-color: #FF3399;");
						}
						textFieldBoard.add(textField);
						tilePane.getChildren().add(textField);
					}
				}
			}
		}
		Group group = new Group(tilePane);
		return group;
	}

	/**
	 * Makes the quit button and returns it
	 * 
	 * @return quit button
	 */
	private Button makeQuit() {
		Button quitButton = new Button("Quit");
		quitButton.setPrefSize(60, 30);
		quitButton.setOnAction(e -> Platform.exit());
		return quitButton;
	}

}
