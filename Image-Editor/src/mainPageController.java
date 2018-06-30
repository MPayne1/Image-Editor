import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Matthew Payne
 *
 */
public class mainPageController {
	@FXML 
	MenuItem closeFile;
	@FXML 
	MenuItem insertImageEdit;
	@FXML 
	Canvas canvasMain;
	@FXML 
	Button straightLineBtn;
	@FXML 
	MenuItem saveAsFile;
	@FXML 
	MenuItem newFile;
	@FXML 
	Button freeLineBtn;
	@FXML 
	VBox vBoxMain;
	@FXML 
	MenuBar menuBarMain;
	@FXML 
	Menu editMain;
	@FXML 
	Slider sizeSlider;
	@FXML 
	ColorPicker colourPicker;
	
	private double fromX;
	private double fromY;
	private double toX;
	private double toY;
	private double strokeSize;
	
	
	public void initialize() {
		
		
		//Size slider event handler
		this.sizeSlider.valueProperty().addListener(e -> {
			handleSizeSlider();
		});
		
		//free line Btn event handler
		this.freeLineBtn.setOnAction(freeBtn -> {
			this.canvasMain.setOnMousePressed(press -> {
				this.fromX = press.getX();
				this.fromY = press.getY();
			});
			this.canvasMain.setOnMouseDragged(drag -> {
				this.fromX = drag.getX();
				this.fromY = drag.getY();
				handleFreeLineBtn();
			});
			this.canvasMain.setOnMouseReleased(release -> {
			});
		});
		
		
		
	}
	
	private void handleStraightLineBtn() {
		
	}
	
	/**
	 * Draw a free line
	 */
	private void handleFreeLineBtn() {
		GraphicsContext gc = this.canvasMain.getGraphicsContext2D();
		gc.setStroke(this.colourPicker.getValue());
		gc.setFill(this.colourPicker.getValue());
		if (this.strokeSize < 3) {
			gc.fillOval(this.fromX, this.fromY, this.strokeSize + 2, this.strokeSize + 2);
		} else {
			gc.fillOval(this.fromX, this.fromY, this.strokeSize, this.strokeSize);
		}

	}
	
	/**
	 * Handle size slider
	 */
	private void handleSizeSlider() {
		this.strokeSize = this.sizeSlider.getValue();
	}
	
	
}
