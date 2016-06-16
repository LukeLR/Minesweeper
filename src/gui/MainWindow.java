package gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow extends Application {

	public static void main(String args){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Minesweeper w/ JavaFX");
		AnchorPane aP = new AnchorPane();
		GridPane root = new GridPane();
		Scene mainMenu = new Scene(aP, 500, 500);
		
		root.setAlignment(Pos.CENTER);
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(25, 25, 25, 25));
		
		Text title = new Text("Willkommen bei Minesweeper!");
		title.setFont(Font.font("Verdana", 20));
		root.add(title, 0, 0, 2, 1);
		
		Label level = new Label("Level:");
		root.add(level, 0, 1, 1, 1);
		
		ToggleGroup levelGroup = new ToggleGroup();
		RadioButton levelEasy = new RadioButton("Easy");
		RadioButton levelMedium = new RadioButton("Intermediate");
		RadioButton levelHard = new RadioButton("Hard");
		RadioButton levelCustom = new RadioButton("Custom");
		
		levelEasy.setToggleGroup(levelGroup);
		levelMedium.setToggleGroup(levelGroup);
		levelHard.setToggleGroup(levelGroup);
		levelCustom.setToggleGroup(levelGroup);
		
		Button levelCustomSettings = new Button ("Settings...");
		
		CustomSettingsStage csw = new CustomSettingsStage();
		
		levelCustom.selectedProperty().addListener((observable, old, now) -> {
			if (now && !old) csw.show();
		});
		
		levelCustomSettings.setOnAction((event) -> {
			csw.show();
			levelCustom.setSelected(true);
		});
		
		levelEasy.setSelected(true);
		
		root.add(levelEasy, 1, 1, 1, 1);
		root.add(levelMedium, 1, 2, 1, 1);
		root.add(levelHard, 1, 3, 1, 1);
		root.add(levelCustom, 1, 4, 1, 1);
		root.add(levelCustomSettings, 2, 4, 1, 1);
		
		Button start = new Button("Start!");
		
		aP.getChildren().add(root);
		aP.getChildren().add(start);
		
		aP.setRightAnchor(root, 0.0);
		aP.setLeftAnchor(root, 0.0);
		aP.setTopAnchor(root, 0.0);
		aP.setBottomAnchor(root, 0.0);
		
		aP.setBottomAnchor(start, 10d);
		aP.setRightAnchor(start, 10d);
		
		primaryStage.setScene(mainMenu);
		primaryStage.show();
	}
	
	public static void showCustomControls(){
		
	}

}
