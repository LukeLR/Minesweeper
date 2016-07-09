package gui;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import meta.DataManager;

public class FacePane extends GridPane {
	public static final int HAPPY_FACE = 0;
	public static final int SAD_FACE = 1;
	public static final int SLEEPY_FACE = 2;
	public static final int LOVING_FACE = 3;
	
	private ImageView happyFace, sadFace, sleepyFace, lovingFace;
	
	public FacePane(){
		happyFace = new ImageView(new Image(getClass().getResourceAsStream("happy.png")));
		happyFace.preserveRatioProperty().set(true);
		happyFace.setFitWidth(DataManager.getData().getWidth()*0.1);
		GridPane.setHalignment(happyFace, HPos.CENTER);
		GridPane.setValignment(happyFace, VPos.CENTER);
		GridPane.setVgrow(happyFace, Priority.ALWAYS);
		GridPane.setHgrow(happyFace, Priority.ALWAYS);
		this.add(happyFace, 0, 0);
		
		sadFace = new ImageView(new Image(getClass().getResourceAsStream("sad.png")));
		sadFace.preserveRatioProperty().set(true);
		sadFace.setFitWidth(DataManager.getData().getWidth()*0.1);
		GridPane.setHalignment(sadFace, HPos.CENTER);
		GridPane.setValignment(sadFace, VPos.CENTER);
		GridPane.setVgrow(sadFace, Priority.ALWAYS);
		GridPane.setHgrow(sadFace, Priority.ALWAYS);
		this.add(sadFace, 0, 0);
		
		sleepyFace = new ImageView(new Image(getClass().getResourceAsStream("sleepy.png")));
		sleepyFace.preserveRatioProperty().set(true);
		sleepyFace.setFitWidth(DataManager.getData().getWidth()*0.1);
		GridPane.setHalignment(sleepyFace, HPos.CENTER);
		GridPane.setValignment(sleepyFace, VPos.CENTER);
		GridPane.setVgrow(sleepyFace, Priority.ALWAYS);
		GridPane.setHgrow(sleepyFace, Priority.ALWAYS);
		this.add(sleepyFace, 0, 0);
		
		lovingFace = new ImageView(new Image(getClass().getResourceAsStream("loving.png")));
		lovingFace.preserveRatioProperty().set(true);
		lovingFace.setFitWidth(DataManager.getData().getWidth()*0.1);
		GridPane.setHalignment(lovingFace, HPos.CENTER);
		GridPane.setValignment(lovingFace, VPos.CENTER);
		GridPane.setVgrow(lovingFace, Priority.ALWAYS);
		GridPane.setHgrow(lovingFace, Priority.ALWAYS);
		this.add(lovingFace, 0, 0);
		
		happyFace.setOnMouseClicked((event) -> {
			DataManager.getData().mainWindow().getGamePane().lost();
		});
		
		sleepyFace.setOnMouseClicked((event) -> {
			DataManager.getData().mainWindow().getGamePane().lost();
		});
	}
	
	public void setFace(int face){
		switch (face){
		case HAPPY_FACE:
			happyFace.setVisible(true);
			sadFace.setVisible(false);
			sleepyFace.setVisible(false);
			lovingFace.setVisible(false);
			break;
		case SAD_FACE:
			happyFace.setVisible(false);
			sadFace.setVisible(true);
			sleepyFace.setVisible(false);
			lovingFace.setVisible(false);
			break;
		case SLEEPY_FACE:
			happyFace.setVisible(false);
			sadFace.setVisible(false);
			sleepyFace.setVisible(true);
			lovingFace.setVisible(false);
			break;
		case LOVING_FACE:
			happyFace.setVisible(false);
			sadFace.setVisible(false);
			sleepyFace.setVisible(false);
			lovingFace.setVisible(true);
			break;
		default: break;
		}
	}
}
