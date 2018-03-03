import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.*;

public class myApp extends Application {
	
	public void start(Stage stage) {
         

       
        TilePane tile = new TilePane();
        tile.setPrefColumns(9);
		tile.setPrefRows(9);
		 final int SIZE = 40;
		  for (int i = 1; i <= 9; i++) {
		      for (int k = 1; k <= 9; k++) {
		          Label label = new Label();
		          label.setPrefSize(SIZE, SIZE);
		          tile.setPadding(new Insets(5, 5, 5, 5));
		          tile.getChildren().add(label);
		      }
		  }
	              

	

		  Group root = new Group(tile);
		  Scene scene = new Scene(root, 600, 600);
	        stage.setScene(scene);
	        stage.setTitle("Suduko");
	        stage.show();
	}
	public static void main(String[] args){
		launch(args);
	}

	

}
