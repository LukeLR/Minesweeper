package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HighscoreStage extends Stage {
	
	public HighscoreStage(){
		super.setTitle("Highscore");
		AnchorPane aP = new AnchorPane();
		GridPane gP = new GridPane();
		
		gP.setAlignment(Pos.CENTER);
		gP.setHgap(10d);
		gP.setVgap(10d);
		aP.setPadding(new Insets(25, 25, 25, 25));
		
		Text name = new Text("Name");
		name.setFont(Font.font("Verdana", 20));
		
		Text startTime = new Text("Start time");
		startTime.setFont(Font.font("Verdana", 20));
		
		Text duration = new Text("Duration");
		duration.setFont(Font.font("Verdana", 20));
		
		Text nameList = new Text();
		
	}
}
