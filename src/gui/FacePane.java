package gui;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import meta.Data;
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
		this.setHalignment(happyFace, HPos.CENTER);
		this.setValignment(happyFace, VPos.CENTER);
		this.setVgrow(happyFace, Priority.ALWAYS);
		this.setHgrow(happyFace, Priority.ALWAYS);
		this.add(happyFace, 0, 0);
		
		sadFace = new ImageView(new Image(getClass().getResourceAsStream("sad.png")));
		sadFace.preserveRatioProperty().set(true);
		sadFace.setFitWidth(DataManager.getData().getWidth()*0.1);
		this.setHalignment(sadFace, HPos.CENTER);
		this.setValignment(sadFace, VPos.CENTER);
		this.setVgrow(sadFace, Priority.ALWAYS);
		this.setHgrow(sadFace, Priority.ALWAYS);
		this.add(sadFace, 0, 0);
		
		sleepyFace = new ImageView(new Image(getClass().getResourceAsStream("sleepy.png")));
		sleepyFace.preserveRatioProperty().set(true);
		sleepyFace.setFitWidth(DataManager.getData().getWidth()*0.1);
		this.setHalignment(sleepyFace, HPos.CENTER);
		this.setValignment(sleepyFace, VPos.CENTER);
		this.setVgrow(sleepyFace, Priority.ALWAYS);
		this.setHgrow(sleepyFace, Priority.ALWAYS);
		this.add(sleepyFace, 0, 0);
		
		lovingFace = new ImageView(new Image(getClass().getResourceAsStream("loving.png")));
		lovingFace.preserveRatioProperty().set(true);
		lovingFace.setFitWidth(DataManager.getData().getWidth()*0.1);
		this.setHalignment(lovingFace, HPos.CENTER);
		this.setValignment(lovingFace, VPos.CENTER);
		this.setVgrow(lovingFace, Priority.ALWAYS);
		this.setHgrow(lovingFace, Priority.ALWAYS);
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
