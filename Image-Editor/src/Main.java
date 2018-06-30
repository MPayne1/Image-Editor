import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author Matthew Payne
 *
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		// sets the root to the log in page.
		Parent root = FXMLLoader.load(getClass().getResource("Main page.fxml"));
		// creates the log in page scene.
		Scene scene = new Scene(root);
		// sets and displays the scene to the user.
		primaryStage.setScene(scene);
		primaryStage.show();
	
	}

	/**
	 *  Launches the program.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
