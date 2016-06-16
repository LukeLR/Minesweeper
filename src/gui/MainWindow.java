package gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
import meta.Data;

public class MainWindow extends Application {
	BorderPane root;
	GridPane menues, mainMenu, difficultyMenu;
	Scene mainScene;
	GamePane gp;
	private Label difficultyLabel;
	private Label nameLabel;
	private ComboBox difficulty;
	private TextField name;
	private Button newGame;
	private CheckBox proportional;
	private Label yTilesLabel;
	private Label xTilesLabel;
	private Label minesLabel;
	private Spinner<Integer> xTilesSpinner;
	private Spinner<Integer> yTilesSpinner;
	private Spinner<Integer> minesSpinner;

	public static void main(String args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Minesweeper w/ JavaFX");
		// AnchorPane aP = new AnchorPane();
		root = new BorderPane();
		menues = new GridPane();
		mainMenu = new GridPane();
		difficultyMenu = new GridPane();
		mainScene = new Scene(root, Data.width, Data.height);

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

		difficultyLabel = new Label("Difficulty:");
		nameLabel = new Label("Your name:");

		difficulty = new ComboBox();
		difficulty.getItems().addAll("Easy", "Intermediate", "Hard", "Custom");
		difficulty.setValue("Easy");

		name = new TextField("Max Mustermann");

		newGame = new Button("New Game");

		proportional = new CheckBox("Proportional");

		xTilesLabel = new Label("Width:");
		yTilesLabel = new Label("Height:");
		minesLabel = new Label("Mines:");

		xTilesSpinner = new Spinner<Integer>(1, 100, Data.xFields);
		yTilesSpinner = new Spinner<Integer>(1, 100, Data.yFields);
		minesSpinner = new Spinner<Integer>(1, 100, Data.mines);

		xTilesSpinner.setEditable(true);
		yTilesSpinner.setEditable(true);
		minesSpinner.setEditable(true);

		proportional.selectedProperty().addListener((ov, old, current) -> {
			if (current) {
				yTilesSpinner.getValueFactory().setValue(Data.xFields);
			}
		});

		xTilesSpinner.valueProperty().addListener((ov, old, current) -> {
			Data.xFields = xTilesSpinner.getValueFactory().getValue();
			if (proportional.isSelected()) {
				yTilesSpinner.getValueFactory().setValue(Data.xFields);
			}
		});

		yTilesSpinner.valueProperty().addListener((ov, old, current) -> {
			Data.yFields = yTilesSpinner.getValueFactory().getValue();
			if (proportional.isSelected()) {
				xTilesSpinner.getValueFactory().setValue(Data.yFields);
			}
		});

		minesSpinner.valueProperty().addListener((ov, old, current) -> {
			Data.mines = minesSpinner.getValueFactory().getValue();
		});

		difficultyMenu.add(proportional, 0, 0);
		difficultyMenu.add(xTilesLabel, 1, 0);
		difficultyMenu.add(xTilesSpinner, 2, 0);
		difficultyMenu.add(yTilesLabel, 3, 0);
		difficultyMenu.add(yTilesSpinner, 4, 0);
		difficultyMenu.add(minesLabel, 5, 0);
		difficultyMenu.add(minesSpinner, 6, 0);

		difficultyMenu.setVisible(false);

		difficulty.valueProperty().addListener((ov, old, current) -> {
			if (current.equals("Custom")) {
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

		newGame.setOnAction((event) -> {
			newGame();
		});

		newGame();

		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	public void newGame() {
		if (gp == null) {
			gp = new GamePane(Data.xFields, Data.yFields, Data.mines);
		} else {
			gp.newGame(Data.xFields, Data.yFields, Data.mines);
		}
		root.setCenter(gp);
	}
}
