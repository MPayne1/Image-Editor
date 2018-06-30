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
		Parent mainBorderPane = FXMLLoader.load(getClass().getClassLoader().getResource("mainPage.fxml"));
		Scene scene = new Scene(mainBorderPane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Image Editor");
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
