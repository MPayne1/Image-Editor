import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * 
 * @author Matthew Payne
 * Launches program, handles closing
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent mainBorderPane = FXMLLoader.load(getClass().getClassLoader().getResource("mainPage.fxml"));
		Scene scene = new Scene(mainBorderPane);
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Image Editor");
		primaryStage.show();
		
		// handle exit without saving
		Platform.setImplicitExit(false);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				handleWindowClose(event);
		    }
		});
		
	}

	/**
	 *  Launches the program.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	private void handleWindowClose(WindowEvent event) {
		 Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Exit without saving?");
	    	alert.setHeaderText("Do you want to exit without saving?");
	    	alert.setContentText("Are you sure?");
	    	
	    	ButtonType cancel = new ButtonType("Cancel");
	    	ButtonType exit = new ButtonType("Exit without Saving");
	    	alert.getButtonTypes().setAll(exit, cancel);
	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == cancel){
	    	    event.consume(); 
	    	}  
	    	if(result.get() == exit) {
	    	   Platform.exit();
	    	}
	}
}
