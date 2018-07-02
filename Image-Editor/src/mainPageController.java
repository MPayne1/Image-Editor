import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Window;

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
	MenuItem saveAsMenuItem;
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
	@FXML
	Button eraserBtn;
	
	private double fromX;
	private double fromY;
	private double toX;
	private double toY;
	private double strokeSize;
	private WritableImage image;
	
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
		
		
		//straight line event handler
		this.straightLineBtn.setOnAction(btn -> {
			this.canvasMain.setOnMousePressed(press -> {
				this.fromX = press.getX();
				this.fromY = press.getY();
			});
			this.canvasMain.setOnMouseDragged(drag -> {
			});
			this.canvasMain.setOnMouseReleased(event -> {
				handleStraightLineBtn(event);
			});

		});
		
		// saveAs btn event handler
		this.saveAsMenuItem.setOnAction(save -> {
			try {
				handleSaveAs();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		// eraser btn event handler
		this.eraserBtn.setOnAction(erase -> {
			this.colourPicker.setValue(Color.WHITE);

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
	
	/**
	 * Draw a straight line
	 */
	private void handleStraightLineBtn(MouseEvent to) {
		GraphicsContext gc = canvasMain.getGraphicsContext2D();
		gc.setStroke(colourPicker.getValue());
		gc.setLineWidth(this.strokeSize);
		this.toX = to.getX();
		this.toY = to.getY();
		gc.strokeLine(this.fromX, this.fromY, this.toX, this.toY);
	}
	
	/**
	 * Draw a free line
	 */
	private void handleFreeLineBtn() {
		GraphicsContext gc = this.canvasMain.getGraphicsContext2D();
		gc.setStroke(this.colourPicker.getValue());
		gc.setFill(this.colourPicker.getValue());
		//if (this.strokeSize < 3) {
			//gc.fillOval(this.fromX, this.fromY, this.strokeSize + 2, this.strokeSize + 2);
		//} else {
			gc.fillOval(this.fromX, this.fromY, this.strokeSize, this.strokeSize);
		//}

	}
	
	/**
	 * Handle size slider
	 */
	private void handleSizeSlider() {
		this.strokeSize = this.sizeSlider.getValue();
	}
	
	/**
	 * Handle saveAs btn
	 * @throws IOException 
	 */
	private void handleSaveAs() throws IOException {
		// Capture what's on the canvas.
		SnapshotParameters spa = new SnapshotParameters();
		this.image = this.canvasMain.snapshot(spa, this.image);

		FileChooser fileChooser = new FileChooser();

		// Save to a file and set the image path.
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showSaveDialog(null);

		if (file != null) {
			RenderedImage renderedImage = SwingFXUtils.fromFXImage(this.image, null);
			ImageIO.write(renderedImage, "png", file);
		}
	}
	
}
