package gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import meta.Constraints;

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

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Minesweeper w/ JavaFX");
		// AnchorPane aP = new AnchorPane();
		root = new BorderPane();
		menues = new GridPane();
		mainMenu = new GridPane();
		difficultyMenu = new GridPane();
		mainScene = new Scene(root, 1000, 1000);

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

		xTilesSpinner = new Spinner<Integer>(1, 100, Constraints.xFields);
		yTilesSpinner = new Spinner<Integer>(1, 100, Constraints.yFields);
		minesSpinner = new Spinner<Integer>(1, 100, Constraints.mines);

		xTilesSpinner.setEditable(true);
		yTilesSpinner.setEditable(true);
		minesSpinner.setEditable(true);

		proportional.selectedProperty().addListener((ov, old, current) -> {
			if (current) {
				yTilesSpinner.getValueFactory().setValue(Constraints.xFields);
			}
		});

		xTilesSpinner.valueProperty().addListener((ov, old, current) -> {
			Constraints.xFields = xTilesSpinner.getValueFactory().getValue();
			if (proportional.isSelected()) {
				yTilesSpinner.getValueFactory().setValue(Constraints.xFields);
			}
		});

		yTilesSpinner.valueProperty().addListener((ov, old, current) -> {
			Constraints.yFields = yTilesSpinner.getValueFactory().getValue();
			if (proportional.isSelected()) {
				xTilesSpinner.getValueFactory().setValue(Constraints.yFields);
			}
		});

		minesSpinner.valueProperty().addListener((ov, old, current) -> {
			Constraints.mines = minesSpinner.getValueFactory().getValue();
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
			gp = new GamePane(Constraints.xFields, Constraints.yFields, Constraints.mines);
		} else {
			gp.newGame(Constraints.xFields, Constraints.yFields, Constraints.mines);
		}
		root.setCenter(gp);
	}

	public int getXTilesValue(){
		return xTilesFa
	}
}
