package gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import meta.Constraints;

public class MainWindow extends Application {

	public static void main(String args){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Minesweeper w/ JavaFX");
//		AnchorPane aP = new AnchorPane();
		BorderPane root = new BorderPane();
		GridPane menues = new GridPane();
		GridPane mainMenu = new GridPane();
		GridPane difficultyMenu = new GridPane();
		Scene mainScene = new Scene(root, 800, 800);
		
		menues.setAlignment(Pos.CENTER);
		menues.setHgap(10d);
		menues.setVgap(10d);
		mainMenu.setAlignment(Pos.CENTER);
		mainMenu.setHgap(10d);
		mainMenu.setVgap(10d);
		difficultyMenu.setAlignment(Pos.CENTER);
		difficultyMenu.setHgap(10d);
		difficultyMenu.setVgap(10d);
		root.setPadding(new Insets(25, 25, 25, 25));
		
		Label difficultyLabel = new Label("Difficulty:");
		Label nameLabel = new Label("Your name:");
		
		ComboBox difficulty = new ComboBox();
		difficulty.getItems().addAll("Easy", "Intermediate", "Hard", "Custom");
		difficulty.setValue("Easy");
		
		TextField name = new TextField("Max Mustermann");
		
		Button newGame = new Button("New Game");
		
		Label xTilesLabel = new Label ("Width:");
		Label yTilesLabel = new Label ("Height:");
		Label minesLabel = new Label ("Mines:");
		
		Spinner<Integer> xTilesSpinner = new Spinner<Integer>(10, 100, Constraints.xFields);
		Spinner<Integer> yTilesSpinner = new Spinner<Integer>(10, 100, Constraints.yFields);
		Spinner<Integer> minesSpinner = new Spinner<Integer>(1, 100, Constraints.mines);
		
		difficultyMenu.add(xTilesLabel, 0, 0);
		difficultyMenu.add(xTilesSpinner, 1, 0);
		difficultyMenu.add(yTilesLabel, 2, 0);
		difficultyMenu.add(yTilesSpinner, 3, 0);
		difficultyMenu.add(minesLabel, 4, 0);
		difficultyMenu.add(minesSpinner, 5, 0);
		
		difficultyMenu.setVisible(false);
		
		difficulty.valueProperty().addListener((ov, old, current) -> {
			if (current.equals("Custom")){
				difficultyMenu.setVisible(true);
			} else {
				difficultyMenu.setVisible(false);
			}
		});
		
		mainMenu.add(difficultyLabel, 0, 0);
		mainMenu.add(difficulty, 1, 0);
		mainMenu.add(nameLabel, 2, 0);
		mainMenu.add(name, 3, 0);
		mainMenu.add(newGame, 4, 0);
		
		menues.add(mainMenu, 0, 0, 1, 1);
		menues.add(difficultyMenu, 0, 1, 1, 1);
		root.setTop(menues);
		
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}
}
